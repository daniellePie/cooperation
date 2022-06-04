<template>
    <v-card width="600"
            class="mx-auto">
        <v-toolbar color="blue-grey lighten-1"
                   dark>
            <v-app-bar-nav-icon></v-app-bar-nav-icon>

            <v-toolbar-title><h4>关注列表</h4></v-toolbar-title>

            <v-spacer></v-spacer>

            <v-btn icon>
                <v-icon>mdi-magnify</v-icon>
            </v-btn>

            <v-btn icon>
                <v-icon>mdi-dots-vertical</v-icon>
            </v-btn>
        </v-toolbar>
        
        <v-list>
            <v-virtual-scroll 
                              :items="items"
                              :item-height="610"
                              height="700">
                <template v-slot:default="{ item }">
                    <v-list-item v-for="item in items"
                                 :key="item.title">
                        <v-list-item-icon>
                            <v-icon v-if="item.icon"
                                    color="pink">
                                mdi-star
                            </v-icon>
                        </v-list-item-icon>
                        <v-list-item-content>
                            <v-list-item-title class="text-h7" v-text="item.title"></v-list-item-title>
                        </v-list-item-content>
                        <v-list-item-avatar>
                            <v-img :src="item.avatar"></v-img>
                        </v-list-item-avatar>
                    </v-list-item>
                    </template>
            </v-virtual-scroll>
        </v-list>

    </v-card>
</template>

<script>
    export default {
        data: () => ({
            items: [
                {
                    title: 'TWICE',
                    avatar: 'https://y.qq.com/music/photo_new/T001R300x300M000001p0vF44ewiwB.jpg?max_age=2592000',
                },
                {
                    title: 'Stray Kids',
                    avatar:'https://y.qq.com/music/photo_new/T001R300x300M000004e4jcl35BfQ0.jpg?max_age=2592000',
                },
                {
                    title: 'BIGBANG',
                    avatar:'https://y.qq.com/music/photo_new/T001R300x300M000004AlfUb0cVkN1.jpg?max_age=2592000',
                },
                {
                    title: 'STAYC',
                    avatar: 'https://y.qq.com/music/photo_new/T001R300x300M000000OHLll10AvFZ.jpg?max_age=2592000',
                },
                {
                    title: 'YENA',
                    avatar: 'https://y.qq.com/music/photo_new/T001R300x300M000003FM90q1Fpglv.jpg?max_age=2592000',
                },
                {
                    title: 'SEVENTEEN',
                    avatar: 'https://y.qq.com/music/photo_new/T001R300x300M00000039PXm1tClwo.jpg?max_age=2592000',
                },
                {
                    title: 'ITZY',
                    avatar: 'https://y.qq.com/music/photo_new/T001R300x300M000004ZmRDC1dVL8T.jpg?max_age=2592000',
                },
                {
                    title: 'P1Harmony',
                    avatar: 'https://y.qq.com/music/photo_new/T001R300x300M000002FdBt63fTbla.jpg?max_age=2592000',
                },
                {
                    title: 'Red Velvet',
                    avatar: 'https://y.qq.com/music/photo_new/T001R300x300M000004NfJdF0gn6Z1.jpg?max_age=2592000',
                },
                {
                    title: 'MONSTA X',
                    avatar: 'https://y.qq.com/music/photo_new/T001R300x300M000003fJxgn36jULV.jpg?max_age=2592000',
                },
                {
                    title: 'THE BOYZ',
                    avatar: 'https://y.qq.com/music/photo_new/T001R300x300M000002szyoq2L4pCw.jpg?max_age=2592000',
                },
            ]
        }
        )
    }
</script>