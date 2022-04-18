# Text to Image Generation
## Links
- [Paper](https://arxiv.org/pdf/2102.12092.pdf)
- [Official](https://openai.com/blog/dall-e/)
- [GitHub](https://github.com/openai/DALL-E)

## Limitations
- 从文本（text）到图像而不是标签（label）
  - 有两种方案
    - 直接让用户输入句子
    - 将句子拆分为label，只显示部分label
- 效果不明确
  - 可能局限在有限的数据集上
  - 不过OpenAI做出来的东西效果还是很好的，说不定后面看看也可以

## Implementation
- 后端直接调用pre-trained model或者查看有无API
- 前端web框架
- 把1+3也能糅合进来倒是


# Music Generation
## Links
- [https://github.com/magenta/magenta](https://github.com/magenta/magenta)
- [https://github.com/salu133445/musegan](https://github.com/salu133445/musegan)
  - 或许可以指定乐器
- [https://github.com/microsoft/muzic](https://github.com/microsoft/muzic)
  - 这个倒是不仅能生成旋律，而且可以生成各种歌词、声音等等
  - 也可以根据歌词生成旋律，这个倒是可以拿来用一下
  - [demo](https://ai-muzic.github.io/)
  - [demo](https://ai-muzic.github.io/muzic_logo/)