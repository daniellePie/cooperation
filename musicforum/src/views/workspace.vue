<template class="basil">
	<v-card height="100%" >
	<!-- <v-container> -->
		<v-card class=" overflow-hidden " >
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

			
		<v-card height="100%" >
			<v-row
				class="fill-height "
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

				

				<v-col>
					<!-- 开始内容，不在这个col外写东西 -->
						<v-container class="flex-column" justify="center">
							
							<!-- title -->
							<v-row justify="center">
								<h1>创作中心</h1>
							</v-row>

							<!-- mainworkspace -->
								<!-- keyboard-->
							<v-row class=" d-flex justify-center mb-6">
								<v-col cols="12" sm="4" class="d-flex justify-center mb-6"><v-btn>play</v-btn></v-col>
								<v-col cols="12" sm="4" class="d-flex justify-center mb-6"><v-btn @click="add_track">+</v-btn></v-col>
								<v-col cols="12" sm="4" class="d-flex justify-center mb-6"><v-btn @click="redu_track">-</v-btn></v-col>
							</v-row>

							<v-row 
								class="7 d-flex justify-center mb-6" 
								justify="center"
								>
								<v-card 
									class="justify-center mb-6"
									justify="center">
									<canvas
										class="d-flex justify-center mb-6"
										v-for="i in this.num"
										:id="get_canvas_id(i-1)"
										height="50"
										width="800"
										style="background:#c1c1c1;"
										@click="getSelect($event)"
										>
									</canvas>
								</v-card>
							</v-row>
									<!-- <h1>{{mouse_x}} {{mouse_y}}</h1> -->
							


							<v-row class="d-flex justify-center mb-6">
								<v-card >
									<v-row
										class="d-flex justify-center mb-6"
										cols="12"
										sm="12"
										justify="center"
									>
										<h1>修改音乐片段属性</h1>
									</v-row>
									
									<v-row justify="center">
									
										

										<v-col
											class="d-flex justify-center mb-6"
											cols="12"
											sm="10"
										>
											<v-select
												:items="clip_id_list"
												label="音乐片段id"
												@change="clip_id_change($event)"
												:value="sel_value_id"
											></v-select>
										</v-col>
										
										<v-col
											class="d-flex justify-center mb-6"
											cols="12"
											sm="10"
										>
											<v-text-field
												label="时长"
												placeholder="音乐片段的时长片数"
												@change="clip_len_change($event)"
												:value="sel_value_len"
											></v-text-field>
										</v-col>

										<v-col
											class="d-flex justify-center mb-6"
											cols="12"
											sm="4"
										>
											<v-btn @click="add_clips()">添加</v-btn>
										</v-col>

										<v-col
											class="d-flex justify-center mb-6"
											cols="12"
											sm="4"
										>
											<v-btn @click="redu_clips()">删除</v-btn>
										</v-col>

									</v-row>
								</v-card>
							</v-row>

							<v-row class="d-flex">
								<h1>clip 属性列表</h1>
									
								<h1>sel={{sel}}</h1>

								<h1>{{track}}</h1>

								<h1>{{num}}</h1>
							</v-row>

							

						</v-container>
					<!-- 结束内容，不在这个col外写东西 -->
				</v-col >
				
			

			</v-row>
		</v-card>
	</v-card>
	<!-- </v-container> -->

</template>




<script>

export default {
	mounted() {
		console.log(this.track)
		for (var i=0; i<this.num; i++){
			this.track.push({})
			this.track[i]["id"] = "myCanvas_"+i
			this.track[i]["clips"] = []
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

			clip_id_list:[
				"do",
				"re",
				"mi"
			],

			sel_value_len:1,
			sel_value_id:"",

			mouse_x:0,
			mouse_y:0,
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

		async add_track(){
			await this.add_track_foo()
			console.log(this.num)
			this.draw_a()
		},

		add_track_foo(){
			this.track.push({})
			this.track[this.num]["id"] = "myCanvas_"+this.num
			this.track[this.num]["clips"] = []
			this.num = this.num+1
		},

		async redu_track(){
			await this.redu_track_foo()
			console.log(this.num)
			this.draw_a()
		},

		redu_track_foo(){
			this.track.pop()
			this.num = this.num-1
		},

		async getSelect(e){
			await this.getSelect_foo(e)
			this.sel_value_len=this.sel["len"]
			this.sel_value_id=this.sel["id"]
		},

		// @click="getMouseXY($event)"
		getSelect_foo(e){
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
				if (loc-clips[i]["loc"] < clips[i]["len"] && loc-clips[i]["loc"] >= 0){
					this.sel["clips_index"]=i
					this.sel["id"] = clips[i]["id"]
					this.sel["loc"] = clips[i]["loc"]
					this.sel["len"] = clips[i]["len"]
					break
				}
			}


			this.draw_a()

		},


		async add_clips(){
			await this.add_clips_foo()
			console.log(this.track)
			this.draw_a()
		},

		add_clips_foo(){
			var canvas_index = this.get_index_from_id(this.sel["canvas_id"])
			var exist = this.get_clip_exist(canvas_index)
			if(exist == -1){
				console.log("checking",this.sel_value_id,this.sel_value_len)
				this.sel["id"] = this.sel_value_id
				this.sel["len"] = this.sel_value_len
				this.track[canvas_index]["clips"].push(
					{"id":this.sel_value_id, "len":this.sel_value_len, "loc":this.sel["loc"]}
					)
			}else{
				console.log("nooooo")
			}
		},

		async redu_clips(){
			await this.redu_clips_foo()
			console.log(this.track)
			this.draw_a()
		},

		redu_clips_foo(){
			var canvas_index = this.get_index_from_id(this.sel["canvas_id"])
			var exist = this.get_clip_exist(canvas_index)
			if(exist != -1){
				this.track[canvas_index]["clips"].splice(exist)
				this.sel_value_id=""
				this.sel_value_len=1
				this.sel["id"]=""
				this.sel["len"]=1
			}else{
				console.log("nooooo")
			}
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
				context.strokeStyle = "black"
				context.beginPath()
				context.moveTo(i/32*canvas.width, 0)
				context.lineTo(i/32*canvas.width, 50)
				context.stroke()
			}
			// context.beginPath()
			// context.rect(0,0,canvas.width,50)
			// context.stroke()

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

		get_clip_exist(canvas_index){
			var clips = this.track[canvas_index]["clips"]
			for (var i=0;i <clips.length;i++){
				if (clips[i]["loc"] == this.sel["loc"])
					return i
			}
			return -1
		},

		clip_id_change(e){
			this.sel_value_id = e
		},

		clip_len_change(e){
			this.sel_value_len=e
		}



  },
};
</script>

<style scoped>
	.basil {
	  height: 100%;
	  background-color: #F5F5F5 !important;
	}

</style>