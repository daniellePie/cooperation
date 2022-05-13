import numpy as np
import scipy.io.wavfile as wav
import os
from os.path import join, isfile, isdir


def mkdir(dir):
    if isdir(dir):
        pass
    else:
        os.makedirs(dir)
    return True


def to_wav(audio_path='data/audio.mp3',sr=16000):
    mkdir('cache')
    os.remove('cache/tmp.wav')
    os.system(f'ffmpeg -i {audio_path} -acodec pcm_s16le -ac 1 -ar {sr} cache/tmp.wav')

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


