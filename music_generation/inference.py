from argparse import ArgumentParser
from os.path import isdir, join, isfile
import scipy.io.wavfile as wav
from dataset.dataset import MyDataset
import torch
from torch.utils.data import DataLoader
import numpy as np
from utils.utils import Logger, mkdir, save_json, setup_seed


def get_args():
    arg_parser = ArgumentParser()
    arg_parser.add_argument("--audio_dir", type=str, default='data_inference')
    arg_parser.add_argument("--num_prediction", type=int, default=60, help="How many seconds to generate")
    arg_parser.add_argument("--batch_size", type=int, default=16)
    arg_parser.add_argument("--seq_length", type=int, default=10, help="The length of start of music, unit:second(s)")
    arg_parser.add_argument("--num_generated", type=int, default=16)
    arg_parser.add_argument("--output_dir", type=str, default='result_inference/tmp')
    arg_parser.add_argument("--checkpoint_path", type=str, default='result_train/0513/checkpoint/final.pt')
    arg_parser.add_argument("--device", type=str, default='cuda:3')
    args = arg_parser.parse_args()
    return args


if __name__ == "__main__":
    setup_seed()
    args = get_args()

    generated_audio_dir = join(args.output_dir,'generated_audio')
    for d in [args.output_dir,generated_audio_dir]:
        mkdir(d)
    save_json(vars(args),join(args.output_dir,'args.json'))

    logger = Logger(join(args.output_dir,'log.txt'))
    if isfile(args.checkpoint_path):
        logger.log(f'Loading {args.checkpoint_path}...')
        model = torch.load(args.checkpoint_path)
    else:
        raise RuntimeError("Pretrained model needed")
    model.to(args.device).eval()
    logger.log(str(model))

    args.sr = model.sr
    args.block_size = args.sr


    logger.log(f'Loading {args.audio_dir}...')
    dataset = MyDataset(args.audio_dir,args.sr,args.block_size,args.seq_length)
    dataloader = DataLoader(dataset,args.batch_size,shuffle=True,drop_last=True)


    


    with torch.no_grad():
        count = 0
        for _,(x,_) in enumerate(dataloader):
            x = x.permute(1,0,2).to(args.device)
            res = [x]
            for step in range(args.num_prediction):
                y_ = model(x)
                res.append(y_[-1:,:,:])
                x = torch.cat((x[1:,:,:],y_[-1:,:,:]),dim=0)
            res = torch.cat(res,dim=0).detach().cpu().numpy()

            for b in range(res.shape[1]):
                ans = []
                series = res[:,b,:]
                reals = series[:,0:args.sr]
                imags = series[:,args.sr:]

                for t in range(reals.shape[0]):
                    real = reals[t]
                    imag = imags[t]
                    fft = real + 1j * imag
                    ans.append(np.fft.ifft(fft))
                ans = np.concatenate(ans)
                ans = 32767.0 * ans
                ans = ans.astype(np.int16)
                logger.log(f'Saving music {count+1}/{args.num_generated}...')
                wav.write(join(generated_audio_dir,'%06d.wav'%count),args.sr,ans)
                count += 1
                if count >= args.num_generated:
                    exit(0)

                



            
            

    

