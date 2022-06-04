<template>
  <v-card max-width="800" class="mx-auto">
    <v-container>
      <v-row dense>
        <v-col v-for="(item, i) in items" :key="i" cols="12">
          <v-card>
            <div class="d-flex flex-no-wrap justify-space-between">
              <div>
                <v-card-title class="text-h5" v-text="item.title"></v-card-title>
                <v-card-subtitle v-text="item.artist"></v-card-subtitle>
                <v-card-actions>
                  <v-col cols=auto>
                    <div class="audio_con">
                    <audio ref='audio' src="../assets/demo.mp3" controls loop class="myaudio" @click="playmusic(item)"> </audio>
                    </div>
                  </v-col>
                  <v-col cols=auto>
                    <v-btn icon color="pink" @click="toggle_like(item)">
                      <v-icon>mdi-heart</v-icon>
                    </v-btn>
                     <v-text>点赞 {{item.likecnt}}</v-text>
                  </v-col>
                  <v-col cols=auto>
                    <v-btn @click="item.star=!item.star" icon color="blue">
                      <v-icon>mdi-star</v-icon>
                    </v-btn>
                     <v-text>收藏</v-text>
                  </v-col>
                   <v-col cols=auto>
                    <v-btn @click="item.comment=!item.comment" icon color="cyan">
                      <v-icon>mdi-comment</v-icon>
                    </v-btn>
                     <v-text>评论</v-text>
                  </v-col>
                 </v-card-actions>
                <v-expand-transition>
                  <div v-show="item.comment">
                     <v-divider></v-divider>
                    <!--其他人发表的评论-->
                    <v-vol v-for="(cmt, j) in item.comments" :key="j">
                    <v-list-item two-line>
                    <v-list-item-content>
                    <v-list-item-title>{{cmt.id}}</v-list-item-title>
                    <v-list-item-subtitle>{{cmt.content}}</v-list-item-subtitle>
                    </v-list-item-content>
                    </v-list-item>
                    </v-vol>
                    <v-card-text>
                       发表你的评论
                    </v-card-text>
                    <v-form>
                      <v-col>
                    <v-textarea clearable clear-icon="mdi-close-circle" label="Text"></v-textarea>
                      </v-col>
                      <v-col>
                    <v-btn depressed color="primary">确认</v-btn>
                      </v-col>
                    </v-form>
                  </div>
                </v-expand-transition>
              </div>
              <v-avatar class="ma-3" size="125" tile>
                 <v-img :src="item.img"></v-img>
              </v-avatar>
            </div>
           </v-card>
         </v-col>
      </v-row>
     </v-container>
  </v-card>
</template>


<script>
  export default {
    data: () => ({
      items: [
        {
          img: 'https://y.qq.com/music/photo_new/T002R300x300M000002xxKCD31H35a_1.jpg?max_age=2592000',
          title: 'Thunderous',
          artist: 'Stray Kids',
          src: "../assets/demo.mp3",
          likecnt: 10,
          likestatus: false,
          playing:false,
          comment:false,
          star: false,
          comments:[{
            id: 'Vocalnooo',
            content: '迷曲好听, 令人着迷'
          },
          {
            id: '3Racha',
            content: 'Good job'
          }
          ]
        },
        {
          img: 'https://cdn.vuetifyjs.com/images/cards/halcyon.png',
          title: 'Halcyon Days',
          artist: 'Ellie Goulding',
          src: '../assets/demo.mp3',
          likecnt: 5,
          likestatus: false,
          playing:false,
          comment:false,
          star: false,
          comments:[{
            id: 'o.o',
            content: 'It is a beautiful song.'
          },
          ]
        },
        {
          img: 'https://y.qq.com/music/photo_new/T002R150x150M000000cGypg4Ij15R.jpg?max_age=2592000',
          title: 'Rollin',
          artist: 'Brave Girls',
          src: '../assets/demo.mp3',
          likecnt: 2,
          likestatus: false,
          playing:false,
          comment:false,
          star: false,
        }
      ],
    }),
    methods:{
       toggle_like(item) {   
            if (!item.likestatus) {   
                item.likecnt++
            } else {    
                item.likecnt--
            }
            item.likestatus=!item.likestatus
        },   
        playmusic(item){
          item.playing=!item.playing
          console.log(playing)
          if(item.playing){
            this.play();
          }
          else this.pause();
        }  
    }
  }
</script>