import torch
from utils.utils import to_wav, load_audio, get_block
from torch.utils.data import Dataset


class MyDataset(Dataset):
    def __init__(self,audio_path,sr,block_size,seq_length) -> None:
        super().__init__()
        to_wav(audio_path,sr)
        audio = load_audio()
        blocks = get_block(audio,block_size)

        X = blocks[0:-1]
        Y = blocks[1:]

        self.data = []
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
