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
			
							<v-list-item-content >
								<v-list-item-title class="font-weight-medium text-h6">{{ item.title }}</v-list-item-title>
							</v-list-item-content>
						</v-list-item>
					</v-list>
				</v-navigation-drawer>

				

				<v-col >
					<!-- 开始内容，不在这个col外写东西 -->
						<v-container>
							
							<!-- title -->
							<v-row justify="center">
								<h1>创作中心</h1>
							</v-row>

							<!-- mainworkspace -->
							<v-row justify="center">
								<!-- keyboard-->
								<v-col 
									aligh="center" justify="space-around"
									>
									<v-card ref="trace_card">
										<canvas 
											v-for="i in this.num"
											:id="get_canvas_id(i-1)"
											height="50"
											width="800"
											style="background:#c1c1c1;"
											@click="getSelect($event)"
											>
										</canvas>

										<h1>{{mouse_x}} {{mouse_y}}</h1>
										
										<v-btn>+</v-btn>
									</v-card>
								</v-col>
							</v-row>
							
							<v-row>
								<v-app-bar
									elevation="4"
									>
									<h1>clip 属性列表</h1>
									
									<h1>sel={{sel}}</h1>
								</v-app-bar> 
							</v-row>


						</v-container>
					<!-- 结束内容，不在这个col外写东西 -->
				</v-col >
				
			

			</v-row>
		</v-card>
	</v-card>

</template>




<script>

export default {
	mounted() {
		console.log(this.track)
		for (var i=0; i<this.num; i++){
			this.track.push({})
			this.track[i]["id"] = "myCanvas_"+i
			this.track[i]["clips"] = [{"id":"123","loc":i,"len":i+1}]
			this.draw(this.track[i]["id"])

		}
		// console.log(this.track)
	},

  data () {
    return {
      drawer: true,
			id:1,
			info:{ name:'ymym', img:'https://i.ibb.co/H7vjrLr/3.jpg'},
				items: [
					{ title: '首页', icon: 'mdi-home-city' },
					{ title: '我的关注', icon: 'mdi-account' },
					{ title: '热门音频', icon: 'mdi-account-group-outline' },
					{ title: '个人中心', icon: 'mdi-account-group-outline' },
					{ title: '创作中心', icon:'mdi-account-group-outline'},
					{ title: '设置', icon: 'mdi-account-group-outline' },
				],
				mini: true,
				
			// music track 
			// mow only 4 track
			num:3,
			track: [],
			sel:{
				"canvas_id":"",
				"clips_index":-1,
				"id":"",
				"loc":0,
				"len":0,
			},


			mouse_x:0,
			mouse_y:0,
			ctx:{}
    }
  },

  methods:{
		hh(){
			this.$router.push({path:'/registerorlogin'})
		},

  	det(title){
	  if (title == '首页') {
	    this.$router.push({path:'/',query:{'id':this.id}})
	  } else if (title == '我的关注') {
	    this.$router.push({path:'/myfollowing',query:{'id':this.id}})
	  } else if (title == '热门音频') {
		this.$router.push({path:'/hotaudio',query:{'id':this.id}})
	  } else if (title == '个人中心') {
		this.$router.push({path:'/myself',query:{'id':this.id}})
	  } else {
		this.$router.push({path:'/settings',query:{'id':this.id}})
	  };
  	},

		print_btn_toggle(){
			console.log(this.toggle_exclusive)
		},

		// @click="getMouseXY($event)"
		getSelect(e){
			this.mouse_x = e.x //获取鼠标的X坐标（鼠标与屏幕左侧的距离，单位为px
			this.mouse_y = e.y //获取鼠标的Y坐标（鼠标与屏幕顶部的距离，单位为px

			var canvas_id = e.target.id
			this.sel["canvas_id"] = canvas_id

			const canvas = document.getElementById(canvas_id)
			var loc = Math.floor((this.mouse_x-canvas.getBoundingClientRect().x)/(canvas.width/32))
			this.sel["loc"] = loc

			this.sel["id"]=""
			this.sel["len"] = 1
			this.sel["clips_index"]=-1
			var index = this.get_index_from_id(canvas_id)
			var clips  = this.track[index]["clips"]
			for (var i=0; i<clips.length; i++){
				if (loc-clips[i]["loc"] < clips[i]["len"]){
					this.sel["clips_index"]=i
					this.sel["id"] = clips[i]["id"]
					this.sel["loc"] = clips[i]["loc"]
					this.sel["len"] = clips[i]["len"]
					break
				}
			}

			this.draw_a()

		},

		

		basic_line(canvas_id){
			const canvas = document.getElementById(canvas_id)
			// console.log(canvas)
			var context = canvas.getContext('2d')
			// console.log(context)

			// console.log(canvas.width)
			
			context.globalAlpha = 1
			
			for (var i=1; i<=31; i++){
				context.lineWidth=2
				context.strokeStyle = "red"
				context.beginPath()
				context.moveTo(i/32*canvas.width, 0)
				context.lineTo(i/32*canvas.width, 50)
				context.stroke()
			}
		},


		basic_rect(canvas_id, loc, length){
			const canvas = document.getElementById(canvas_id)
			// console.log(canvas)
			var context = canvas.getContext('2d')
			// console.log(context)

			context.beginPath()
			context.fillStyle="yellow"
			context.rect(loc*(canvas.width/32), 0, length*(canvas.width/32), 50)
			context.fill()
		},

		

		basic_sel(){
			const canvas = document.getElementById(this.sel["canvas_id"])
			// console.log(canvas)
			var context = canvas.getContext('2d')

			context.lineWidth=3;
			context.strokeStyle = "blue";
			context.beginPath()
			context.rect(this.sel["loc"]*(canvas.width/32), 0, this.sel["len"]*(canvas.width/32), 50)
			context.stroke()
		},

		draw(canvas_id){
			const canvas = document.getElementById(canvas_id)
			// console.log(canvas)
			var context = canvas.getContext('2d')
			// console.log(context)

			context.clearRect(0, 0, canvas.width, 50);
			this.basic_line(canvas_id)
			
			var index = this.get_index_from_id(canvas_id)
			var clips = this.track[index]["clips"]
			for (var i=0; i<clips.length; i++){
				this.basic_rect(canvas_id, clips[i]["loc"], clips[i]["len"])
			}
			// console.log()
		},

		draw_a(){
			for (var i=0; i<this.num; i++){
				this.draw(this.track[i]["id"])
			}
			this.basic_sel()
		},


		get_canvas_id(index){
			return "myCanvas_" +index
		},

		get_index_from_id(canvas_id){
			for (var i=0;i <this.num;i++){
				if (this.track[i]["id"] == canvas_id)
					return i
			}
			return -1
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