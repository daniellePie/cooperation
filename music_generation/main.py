from argparse import ArgumentParser
from os.path import isdir, join, isfile

from dataset.dataset import MyDataset
import torch
from torch import linalg as LA
from torch.optim import Adam
from torch.utils.data import DataLoader
from tqdm import tqdm
import os
import pandas as pd
import numpy as np
from matplotlib import pyplot as plt

from utils.utils import to_wav


def get_args():
    arg_parser = ArgumentParser()
    arg_parser.add_argument("--audio_path", type=str, default='data/audio.mp3')
    arg_parser.add_argument("--sr", type=int, default=16000)
    arg_parser.add_argument("--block_size", type=int, default=16000)
    arg_parser.add_argument("--seq_length", type=int, default=10)
    arg_parser.add_argument("--batch_size", type=int, default=7)




    args = arg_parser.parse_args()
    

    return args


if __name__ == "__main__":
    args = get_args()

    dataset = MyDataset(args.audio_path,args.sr,args.block_size,args.seq_length)
    dataloader = DataLoader(dataset,args.batch_size,shuffle=True,drop_last=True)

    for x,y in dataloader:
        print(x.shape)
        print(y.shape)

