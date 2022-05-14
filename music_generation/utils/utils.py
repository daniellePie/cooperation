import numpy as np
import scipy.io.wavfile as wav
import os
from os.path import join, isfile, isdir
import datetime
import pickle
import json
import torch
import random


def mkdir(dir):
    if isdir(dir):
        pass
    else:
        os.makedirs(dir)
    return True


def to_wav(audio_path,sr):
    mkdir('cache')
    if isfile('cache/tmp.wav'):
        os.remove('cache/tmp.wav')
    os.system(f'ffmpeg -i "{audio_path}" -acodec pcm_s16le -ac 1 -ar {sr} cache/tmp.wav')

def load_audio(audio_path='cache/tmp.wav'):
    sr,audio = wav.read(audio_path)
    audio = audio.astype('float32') / 32767.0
    return audio

def get_block(audio,block_size):
    i = 0
    ans = []
    total_length = audio.shape[0]
    while i + block_size <= total_length:
        b = audio[i:i+block_size]
        fft = np.fft.fft(b)
        real = np.real(fft)
        imag = np.imag(fft)
        # block_size * 2
        ans.append(np.concatenate([real,imag],axis=0))
        i += block_size
    ans = np.stack(ans,axis=0)
    return ans


def get_datetime():
    time1 = datetime.datetime.now()
    time2 = datetime.datetime.strftime(time1,'%Y-%m-%d %H:%M:%S')
    return str(time2)

class Logger():
    def __init__(self,log_file_path) -> None:
        self.path = log_file_path
        with open(self.path,'w') as f:
            f.write(get_datetime() + "\n")
            print(get_datetime())
        return
    
    def log(self,content):
        with open(self.path,'a') as f:
            f.write(content + "\n")
            print(content)
        return

def load_pkl(path):
  with open(path, 'rb') as f:
    res = pickle.load(f)
  return res

def save_pkl(obj, path):
  with open(path, 'wb') as f:
    pickle.dump(obj, f)

def load_json(path):
  with open(path, 'r') as f:
    res = json.load(f)
  return res

def save_json(obj, path, indent=4):
  with open(path, 'w') as f:
    json.dump(obj, f, indent=indent)

def setup_seed(seed = 3407):
    torch.manual_seed(seed)
    np.random.seed(seed)
    random.seed(seed)