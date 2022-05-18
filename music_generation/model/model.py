import torch
from torch import nn

class LSTM0(nn.Module):
    def __init__(self,feature_dim,hidden_dim,num_layer,dropout=0.1):
        super().__init__()
        self.sr = feature_dim // 2
        self.fc1 = nn.Linear(feature_dim,hidden_dim)
        self.lstm = nn.LSTM(hidden_dim,hidden_dim,num_layer,dropout=dropout)
        self.fc2 = nn.Linear(hidden_dim,feature_dim)
    
    def forward(self,x):
        y = self.fc1(x)
        y,_ = self.lstm(y)
        y = self.fc2(y)
        return y