from argparse import ArgumentParser
from email.errors import HeaderParseError
from os.path import isdir, join, isfile
from dataset.dataset import MyDataset
import torch
from torch.optim import Adam
from torch.utils.data import DataLoader
from tqdm import tqdm
import os
import numpy as np
from model.model import LSTM0 as LSTM
from utils.utils import Logger, mkdir, save_json, setup_seed


def get_args():
    arg_parser = ArgumentParser()
    arg_parser.add_argument("--audio_dir", type=str, default='data_train', help=".wav files should be placed here")
    arg_parser.add_argument("--sr", type=int, default=16000, help="Sampling rate for audio")
    arg_parser.add_argument("--seq_length", type=int, default=10, help="The length of start of music, unit:second(s)")
    arg_parser.add_argument("--batch_size", type=int, default=128)
    arg_parser.add_argument("--output_dir", type=str, default='result_train/0513')
    arg_parser.add_argument("--checkpoint_path", type=str, default='')
    arg_parser.add_argument("--device", type=str, default='cuda:0')
    arg_parser.add_argument("--num_layer", type=int, default=6)
    arg_parser.add_argument("--num_epoch", type=int, default=5000)
    arg_parser.add_argument("--hidden_dim", type=int, default=2048)
    arg_parser.add_argument("--dropout", type=float, default=0.5)
    arg_parser.add_argument("--lr", type=float, default=1e-3)
    args = arg_parser.parse_args()
    args.block_size = args.sr
    return args


if __name__ == "__main__":
    setup_seed()
    args = get_args()
    ck_dir = join(args.output_dir,'checkpoint')
    for d in [args.output_dir,ck_dir]:
        mkdir(d)
    save_json(vars(args),join(args.output_dir,'args.json'))

    logger = Logger(join(args.output_dir,'log.txt'))
    logger.log(f'Loading {args.audio_dir}...')
    dataset = MyDataset(args.audio_dir,args.sr,args.block_size,args.seq_length)
    dataloader = DataLoader(dataset,args.batch_size,shuffle=True,drop_last=False)
    logger.log(f"Number of segment = {len(dataset)}")
    logger.log(f"Number of batch = {len(dataloader)}")


    if isfile(args.checkpoint_path):
        logger.log(f'Loading {args.checkpoint_path}...')
        model = torch.load(args.checkpoint_path)
    else:
        logger.log(f'Creating model...')
        model = LSTM(args.block_size*2,args.hidden_dim,args.num_layer,args.dropout)
    model.to(args.device).train()
    logger.log(str(model))

    optimizer = Adam(model.parameters(),lr=args.lr)

    loss_fn = torch.nn.MSELoss()


    logger.log('Start training...')
    # best_loss = 1e12
    for epoch in tqdm(range(args.num_epoch)):
        epoch_loss = 0
        for count,(x,y) in enumerate(dataloader):
            x = x.permute(1,0,2).to(args.device)
            y = y.permute(1,0,2).to(args.device)
            y_ = model(x)
            loss = loss_fn(y,y_)
            loss.backward()
            optimizer.step()
            optimizer.zero_grad()
            epoch_loss += loss.item()
        logger.log(f'Epoch={epoch}, loss={epoch_loss/(count+1)}')

        if (epoch+1) % 500 == 0:
            torch.save(model.cpu(), join(ck_dir,f'epoch_{epoch}.pt'))
            model.to(args.device)


    torch.save(model.cpu(), join(ck_dir,'final.pt'))

            
            

    

