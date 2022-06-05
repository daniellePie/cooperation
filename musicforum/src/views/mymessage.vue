<template class="basil">
	<v-card height="100%">
		<v-card class="overflow-hidden">
			<v-app-bar 
					   absolute
					   color="#fcb69f"
					   dark
					   shrink-on-scroll
					   src="https://picsum.photos/1920/1080?random"
					   scroll-target="#scrolling-techniques-2"
					   >
				<template v-slot:img="{ props }">
					<v-img
						   v-bind="props"
						   gradient="to top right, rgba(19,84,122,.5), rgba(128,208,199,.8)"
						   ></v-img>
				</template>

				<v-spacer></v-spacer>

				<v-btn icon>
					<v-icon>mdi-magnify</v-icon>
				</v-btn>

				<v-btn @click="hh" icon>
					<v-icon>mdi-open-in-new</v-icon>
				</v-btn>
			</v-app-bar>
			<v-sheet
					 id="scrolling-techniques-2"
					 class="overflow-y-auto"
					 max-height="600"
					 >
				<v-container style="height: 50px;"></v-container>
			</v-sheet>
		</v-card>

		<v-card height="100%">
			<v-row
				   class="fill-height"
				   no-gutters
				   >
				<v-navigation-drawer
									 v-model="drawer"
									 :mini-variant.sync="mini"
									 permanent
									 >
					<v-list-item class="px-2">
						<v-list-item-avatar>
							<v-img :src="info.img"></v-img>
						</v-list-item-avatar>

						<v-list-item-title class="font-weight-medium text-h6">{{info.name}}</v-list-item-title>

						<v-btn
							   icon
							   @click.stop="mini = !mini"
							   >
							<v-icon>mdi-chevron-left</v-icon>
						</v-btn>
					</v-list-item>

					<v-divider></v-divider>

					<v-list flat>
						<v-list-item
									 v-for="item in items"
									 :key="item.title"
									 @click="det(item.title)"
									 >
							<v-list-item-icon>
								<v-icon>{{ item.icon }}</v-icon>
							</v-list-item-icon>

							<v-list-item-content>
								<v-list-item-title class="font-weight-medium text-h6">{{ item.title }}</v-list-item-title>
							</v-list-item-content>
						</v-list-item>
					</v-list>
				</v-navigation-drawer>

				<div style="margin:auto">
				<muscard> </muscard>
				</div>
			</v-row>
		</v-card>
	</v-card>
</template>

<script>
	import muscard from './messlist.vue'
	export default {
		components: {
			'muscard': muscard
		},
		data() {
			return {
				drawer: true,
				id: 1,
				info: {
					name: 'ymym', img: 'https://i.ibb.co/H7vjrLr/3.jpg'
				},
				items: [
					{ title: '我的关注', icon: 'mdi-account' },
					{ title: '我的收藏', icon: 'mdi-star-outline' },
					{ title: '我的点赞', icon: 'mdi-thumb-up-outline' },
					{ title: '我的评论', icon: 'mdi-message-outline' },
					{ title: '我的转发', icon: 'mdi-message-arrow-right-outline' },
					{ title: '我的发布', icon: 'mdi-pencil-outline' },
					{ title: '新通知', icon: 'mdi-email-outline' },
					{ title: '我的待审核', icon: 'mdi-clipboard-clock-outline' },
				],
				mini: true,
			}
		},
		methods: {
			hh() {
				this.$router.push({ path: '/registerorlogin' })
			},
			det(title) {
				if (title == '我的关注') {
					this.$router.push({ path: '/myfollowing', query: { 'id': this.id } })
				}
                else if (title == '新通知') {
					this.$router.push({ path: '/mymessage', query: { 'id': this.id } })
                }
                else if (title == '我的收藏') {
                    this.$router.push({ path: '/mycollecting', query: { 'id': this.id } })
				};
			},
		},
	};
</script>

<style scoped>
	.basil {
		height: 100%;
		background-color: #F5F5F5 !important;
	}
</style>