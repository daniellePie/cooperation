<template>
    <v-card class="mx-auto"
            width="600">


        <v-toolbar color="blue-grey lighten-1"
                   dark>
            <v-app-bar-nav-icon></v-app-bar-nav-icon>

            <v-toolbar-title>我的收藏</v-toolbar-title>

            <v-spacer></v-spacer>

            <v-btn icon>
                <v-icon>mdi-magnify</v-icon>
            </v-btn>
        </v-toolbar>

        <v-container fluid>
            <v-row dense>
                <v-col v-for="card in cards"
                       :key="card.title"
                       :cols="card.flex">
                    <v-card>
                        <v-img :src="card.src"
                               class="white--text align-end"
                               gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                               height="200px">
                            <v-card-title v-text="card.title"></v-card-title>
                        </v-img>

                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-col cols=auto>
                                <v-btn @click="item.star=!item.star" icon color="blue">
                                   
                                        <v-icon>mdi-star</v-icon>
                                    </v-btn>
                            </v-col>
                            <v-col cols=auto>
                                <v-btn @click="item.comment=!item.comment" icon color="grey">              
                                        <v-icon>mdi-share-variant</v-icon>
                                    </v-btn>
                                    </v-col>
                        </v-card-actions>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>
    </v-card>
</template>
<script>
  export default {
    data: () => ({
      cards: [
            { title: 'Thunderous', src: 'https://y.qq.com/music/photo_new/T002R300x300M000002xxKCD31H35a_1.jpg?max_age=2592000', flex: 6 },
            { title: 'LOVE DIVE', src: 'https://y.qq.com/music/photo_new/T002R300x300M000000lZE0t1QDU7y_1.jpg?max_age=2592000', flex: 6 },
            { title: 'TT', src: 'https://y.qq.com/music/photo_new/T002R300x300M000002WJiIs2YH7aR_1.jpg?max_age=2592000', flex: 6 },
            { title: 'Psycho', src:'https://y.qq.com/music/photo_new/T002R300x300M000003A6HUX2I6hsY_1.jpg?max_age=2592000',flex:6},
      ],
    }),
  }
</script>