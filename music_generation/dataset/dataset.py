from os.path import join
import torch
from utils.utils import to_wav, load_audio, get_block
from torch.utils.data import Dataset
import os
from glob import glob

class MyDataset(Dataset):
    def __init__(self,audio_dir,sr,block_size,seq_length) -> None:
        super().__init__()
        self.data = []
        for audio_path in glob(join(audio_dir,'*')):
            to_wav(audio_path,sr)
            audio = load_audio()
            blocks = get_block(audio,block_size)

            X = torch.from_numpy(blocks[0:-1]).to(torch.float32)
            Y = torch.from_numpy(blocks[1:]).to(torch.float32)

            i = 0
            while i + seq_length < X.shape[0]:
                self.data.append(
                    (
                        X[i:i+seq_length],
                        Y[i:i+seq_length]
                    )
                )
                i += 1
        self.length = len(self.data)
    
    def __len__(self):
        return self.length
    
    def __getitem__(self, index):
        return self.data[index][0], self.data[index][1]
