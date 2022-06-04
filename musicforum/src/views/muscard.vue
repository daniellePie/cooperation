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
                  <v-col cols="10">
                    <v-btn icon outlined large color="indigo" @click="playmusic(item)">
                      <v-icon>{{ item.play? 'mdi-pause':'mdi-play'}}</v-icon>
                    </v-btn>

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
                    <v-card-text>
                       发表你的评论
                    </v-card-text>
                    <!--还没加其他人发表的评论-->
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
                 <v-img :src="item.src"></v-img>
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
          src: 'https://y.qq.com/music/photo_new/T002R300x300M000002xxKCD31H35a_1.jpg?max_age=2592000',
          title: 'Thunderous',
          artist: 'Stray Kids',
          demo: './assets/demo.mp3',
          likecnt: 0,
          likestatus: false,
          play:false,
          comment:false,
          star: false,
        },
        {
          src: 'https://cdn.vuetifyjs.com/images/cards/halcyon.png',
          title: 'Halcyon Days',
          artist: 'Ellie Goulding',
          demo: './assets/demo.mp3',
          likecnt: 0,
          likestatus: false,
          play:false,
          comment:false,
          star: false,
        },
        {
          src: 'https://y.qq.com/music/photo_new/T002R150x150M000000cGypg4Ij15R.jpg?max_age=2592000',
          title: 'Rollin',
          artist: 'Brave Girls',
          demo: '../assets/demo.mp3',
          likecnt: 0,
          likestatus: false,
          play:false,
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
        person(){
          //还没写，跳转到发布者的主页
        },    
        playmusic(item){
          item.play=!item.play
          console.log(play)
          if(item.play){
            let audio = new Audio()
            audio.src = item.demo
            audio.play();
          }
          else audio.pause();
        }   
    }
  }
</script>