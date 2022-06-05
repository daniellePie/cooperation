<template>
    <v-card class="mx-auto"
            width="600">
        <v-toolbar color="blue-grey lighten-1"
                   dark>
            <v-app-bar-nav-icon></v-app-bar-nav-icon>

            <v-toolbar-title><h4>新通知</h4></v-toolbar-title>

            <v-spacer></v-spacer>

            <v-btn icon>
                <v-icon>mdi-magnify</v-icon>
            </v-btn>
        </v-toolbar>
        <v-list subheader>
            <v-subheader>最近的通知</v-subheader>
                <v-list-item v-for="chat in recent"
                             :key="chat.title">
                    <v-list-item-avatar>
                        <v-img :alt="`${chat.title} avatar`"
                               :src="chat.avatar"></v-img>
                    </v-list-item-avatar>

                    <v-list-item-content>
                        <v-list-item-title v-text="chat.title"></v-list-item-title>
                    </v-list-item-content>

                    <v-list-item-icon>
                        <v-icon :color="chat.active ? 'deep-purple accent-4' : 'grey'">
                            mdi-message-outline
                        </v-icon>
                    </v-list-item-icon>
                </v-list-item>
        </v-list>

            <v-divider></v-divider>

            <v-list subheader>
                <v-subheader>之前的通知</v-subheader>

                <v-list-item v-for="chat in previous"
                             :key="chat.title">
                    <v-list-item-avatar>
                        <v-img :alt="`${chat.title} avatar`"
                               :src="chat.avatar"></v-img>
                    </v-list-item-avatar>

                    <v-list-item-content>
                        <v-list-item-title v-text="chat.title"></v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
    </v-card>
</template>
<script>
  export default {
    data: () => ({
      recent: [
        {
          active: true,
          avatar: 'https://y.qq.com/music/photo_new/T001R300x300M000004AlfUb0cVkN1.jpg?max_age=2592000',
          title: 'BIGBANG',
        },
        {
          active: true,
            avatar: 'https://y.qq.com/music/photo_new/T001R300x300M000002FdBt63fTbla.jpg?max_age=2592000',
          title: 'P1Harmony',
        },
        {
            avatar: 'https://y.qq.com/music/photo_new/T001R300x300M000001p0vF44ewiwB.jpg?max_age=2592000',
          title: 'TWICE',
        },
        {
            avatar: 'https://y.qq.com/music/photo_new/T001R300x300M000000OHLll10AvFZ.jpg?max_age=2592000',
          title: 'STAYC',
        },
      ],
          previous: [
         {
        title: 'SEVENTEEN',
        avatar: 'https://y.qq.com/music/photo_new/T001R300x300M00000039PXm1tClwo.jpg?max_age=2592000',
              },
              {
                  title: 'YENA',
                  avatar:'https://y.qq.com/music/photo_new/T001R300x300M000003FM90q1Fpglv.jpg?max_age=2592000'
              },
          ],
    }),
  }
</script>