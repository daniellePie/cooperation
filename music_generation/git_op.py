import os


for item in os.listdir():
    if item.startswith('result'):
        continue
    else:
        os.system(f'git add {item}')