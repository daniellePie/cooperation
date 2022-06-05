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
			<v-row class="fill-height" no-gutters
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
				

				<v-col class="justify-center">
					<!-- 开始内容，不在这个col外写东西 -->
						<v-div class= "ma-4" >
							
							<!-- title -->
							<v-row justify="center">
								<h1>创作中心</h1>
							</v-row>

							<!-- mainworkspace -->
								<!-- keyboard-->
							<v-row>
							<v-col cols="12" sm="6">
								<v-row class=" d-flex justify-center ma-2">
									<h1>音轨</h1>
								</v-row>
								

								<v-row class=" d-flex justify-center ma-2">
									<v-card>
									<v-col cols="12" sm="12" class="d-flex justify-center ma-1"><h3 >修改音轨属性信息</h3></v-col>
									<v-row class=" d-flex justify-center ma-2">
										<v-col cols="12" sm="3" class="d-flex justify-center ma-2">
											<v-tooltip bottom>
												<template v-slot:activator="{ on, attrs }">
													<v-btn
														v-bind="attrs"
														v-on="on"
														@click="add_track()"
													>
														添加音轨
													</v-btn>
												</template>
												<span>{{info_add_track}}</span>
											</v-tooltip>
										</v-col>
										<v-col cols="12" sm="3" class="d-flex justify-center ma-2">
											<v-tooltip bottom>
												<template v-slot:activator="{ on, attrs }">
													<v-btn
														v-bind="attrs"
														v-on="on"
														@click="redu_track()"
													>
														删除音轨
													</v-btn>
												</template>
												<span>{{info_redu_track}}</span>
											</v-tooltip>
										</v-col>
										
									</v-row >
									<v-row class=" d-flex justify-center ma-2">
										<v-col cols="12" sm="3" class="d-flex justify-center ma-2">
											<v-text-field
												label="时间片单位长度/s"
												placeholder="每个时间片代表的时间长度，默认为1s"
												@change="track_clip_length_change($event)"
												:value="track_clip_length"
											></v-text-field>
										</v-col>

										<v-col cols="12" sm="3" class="d-flex justify-center ma-2	">
											<v-text-field
												label="时间片数量"
												placeholder="每个音轨包含的时间片数量，默认为32"
												@change="track_clip_num_change($event)"
												:value="track_clip_num"
											></v-text-field>
										</v-col>

										<v-col cols="12" sm="3" class="d-flex justify-center ma-2	" >
											<v-list-item  two-line >
												<v-list-item-content>
													<v-list-item-title>注意：</v-list-item-title>
													<v-list-item-subtitle>修改音轨属性会清空音轨内容</v-list-item-subtitle>
												</v-list-item-content>
											</v-list-item>
										</v-col>
									</v-row>
									<!-- <h3>{{select_clip}}</h3>
									<h3>{{track}}</h3>
									<h3>len {{sel_value_len}} lens {{sel_value_len_s}}</h3> -->
									</v-card>
								</v-row>

								<v-row 
									class="7 d-flex justify-center ma-2" 
									justify="center"
									>
									<v-col cols="12" sm="3" class="d-flex justify-center ma-2">
										<v-tooltip bottom>
											<template v-slot:activator="{ on, attrs }">
												<v-btn
													v-bind="attrs"
													v-on="on"
													@click="generate_music()"
												>
													生成
												</v-btn>
											</template>
											<span>{{info_gen}}</span>
										</v-tooltip>
									</v-col>
									<v-col cols=auto>
										<div class="audio_con">
										<audio ref='audio' src="" controls loop class="myaudio" @click="playmusic(item)"> </audio>
										</div>
									</v-col>
								</v-row>

								<v-row 
									class="7 d-flex justify-center ma-2" 
									justify="center"
									>
									<v-card 
										class="justify-center ma-2"
										justify="center">
										<canvas
											class="d-flex justify-center ma-2"
											v-for="i in this.track_num"
											:id="get_canvas_id(i-1)"
											height="50"
											width="600"
											style="background:#c1c1c1;"
											@click="getSelect($event)"
											>
										</canvas>
									</v-card>
								</v-row>
									<!-- <h1>{{mouse_x}} {{mouse_y}}</h1> -->
								<!-- <h2>{{post_list}}</h2> -->
							</v-col>

							<v-col cols="12" sm="6">
								<v-row class="d-flex justify-center ma-5">
									<v-card width="100%">
										<v-row
											class="d-flex justify-center ma-2"
											cols="12"
											sm="12"
											justify="center"
										>
											<h1>修改音乐片段属性</h1>
										</v-row>
										
										<v-row justify="center">
											<v-col
												class="d-flex justify-center ma-2"
												cols="12"
												sm="10"
											>
												<v-select
													:items="clip_id_list"
													label="音乐片段id"
													@change="clip_id_change($event)"
													v-model="sel_value_id"
												></v-select>
											</v-col>
											
											<v-col
												class="d-flex justify-center ma-2"
												cols="12"
												sm="10"
											>
												<!-- 由于功能修改，进行替换 -->
												<!-- <v-text-field
													label="时长/s"
													placeholder="音乐片段的插入时长"
													@change="clip_len_change($event)"
													v-model="sel_value_len_s"
												></v-text-field> -->
												<v-text-field
													label="时长/s"
													placeholder="音乐片段的插入时长"
													disabled
													v-model="sel_value_len_s"
												></v-text-field>
											</v-col>

											<v-col
												class="d-flex justify-center ma-2"
												cols="12"
												sm="3"
											>
												<v-tooltip top>
													<template v-slot:activator="{ on, attrs }">
														<v-btn
															v-bind="attrs"
															v-on="on"
															@click="add_clips()"
														>
															添加
														</v-btn>
													</template>
													<span>{{info_add}}</span>
												</v-tooltip>
											</v-col>

											<v-col
												class="d-flex justify-center ma-2"
												cols="12"
												sm="3"
											>
												<v-tooltip top>
													<template v-slot:activator="{ on, attrs }">
														<v-btn
															v-bind="attrs"
															v-on="on"
															@click="edit_clips()"
														>
															修改
														</v-btn>
													</template>
													<span>{{info_edit}}</span>
												</v-tooltip>
											</v-col>

											<v-col
												class="d-flex justify-center ma-2"
												cols="12"
												sm="3"
											>
												<v-tooltip top>
													<template v-slot:activator="{ on, attrs }">
														<v-btn
															v-bind="attrs"
															v-on="on"
															@click="redu_clips()"
														>
															删除
														</v-btn>
													</template>
													<span>{{info_redu}}</span>
												</v-tooltip>
											</v-col>

										</v-row>
									</v-card>
								</v-row>

								<!-- current music piece infomation-->
								<v-row class="d-flex justify-center ma-5">
									<v-card width="100%">

										
										<v-row
											class="d-flex justify-center ma-2"
											cols="12"
											sm="12"
											justify="center"
										>
											<h1>音乐素材查询</h1>
										</v-row>
										
										<v-row justify="center">
											<v-col
												class="d-flex justify-center ma-2"
												cols="12"
												sm="10"
											>
												<v-select
													:items="clip_id_list"
													label="音乐素材id"
													@change="cur_id_change($event)"
													:value="cur_id"
												></v-select>
											</v-col>
											
											<v-col cols=auto>
												<div class="audio_con">
												<audio ref='audio_2' src="" controls loop class="myaudio" @click="playmusic(item)"> </audio>
												</div>
											</v-col>

											<v-col
												class="d-flex justify-center ma-5"
												cols="12"
												sm="10"
											>
												<v-list-item  two-line>
													<v-list-item-content>
														<v-list-item-title>音乐素材id</v-list-item-title>
														<v-list-item-subtitle>{{cur_id}}</v-list-item-subtitle>
													</v-list-item-content>
												</v-list-item>

												<v-list-item  two-line>
													<v-list-item-content>
														<v-list-item-title>音乐作品名称</v-list-item-title>
														<v-list-item-subtitle>{{cur_audioName}}</v-list-item-subtitle>
													</v-list-item-content>
												</v-list-item>

												<v-list-item  two-line>
													<v-list-item-content>
														<v-list-item-title>音乐作品时长/s</v-list-item-title>
														<v-list-item-subtitle>{{cur_duration}}</v-list-item-subtitle>
													</v-list-item-content>
												</v-list-item>

											</v-col>
											<v-col
												class="d-flex justify-center ma-5"
												cols="12"
												sm="10"
											>
												<v-list-item  two-line>
													<v-list-item-content>
														<v-list-item-title>音乐作品描述</v-list-item-title>
														<v-list-item-subtitle>{{cur_description}}</v-list-item-subtitle>
													</v-list-item-content>
												</v-list-item>

											</v-col>
											
										</v-row>
									</v-card>
								</v-row>

								<!-- <h1>{{music_list}}</h1> -->

							</v-col>

							</v-row>

							

						</v-div>
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
		// console.log(this.track)
		// 音轨初始化
		for (var i=0; i<this.track_num; i++){
			this.track.push({});
			this.track[i]["id"] = "myCanvas_"+i;
			this.track[i]["clips"] = [];
			this.draw(this.track[i]["id"]);

		}

		axios
      .get('http://47.103.149.25:8081/music/musicInfo/infoForGenerate')
      .then(response=>{
				this.music_list = response.data.data;
				// console.log(response)
				for (var i=0; i<response.data.data.length; i++){
					this.clip_id_list.push(response.data.data[i]["id"])
				}
			})
			.catch(function (error) {
				console.log(error)
			})
	},

	// info {name:'ymym', img:'https://i.ibb.co/H7vjrLr/3.jpg'}

  data () {
    return {
      drawer: true,
			id:1,
			info:{name:'ymym', img:'https://i.ibb.co/H7vjrLr/3.jpg'},
				items: [
					{ title: '首页', icon: 'mdi-home-city' },
					{ title: '我的关注', icon: 'mdi-account' },
					{ title: '热门音频', icon: 'mdi-account-group-outline' },
					{ title: '个人中心', icon: 'mdi-account-group-outline' },
					{ title: '音频创作', icon: 'mdi-account' },
					{ title: 'ai音频续写', icon: 'mdi-account' },
					{ title: '设置', icon: 'mdi-account-group-outline' },
				],
				mini: true,
				
			// 音轨设置
			track_clip_length:1,	// 音乐片单位长度
			track_clip_num:32,	//	音乐片的数量
			track_num:3,	// 音轨数量
			track: [],		// 音轨内容
			/*
			track[i]["clips"]={
				"id":	// 乐曲id
				"len":	// 音乐片长度
				"loc":	// 音乐片起始位置
				"len_s":	// 音乐片长度
			}
			*/

			// 当前选择的音乐片
			select_clip:{
				"canvas_id":"myCanvas_0",	// 所在的音轨
				"clips_index":-1,	// clip位于自己所在的音轨音乐片列表的index
				"id":"",	// 乐曲 id
				"loc":0,	// 位置
				"len":0,	// 长度
				"len_s":0,	// 长度，单位s
			},

			// 从数据库获取的音乐片id
			clip_id_list:[],
			// 从数据库获得的音乐片段的全部信息
			music_list:null,

			// 修改clip属性的变量
			sel_value_len_s:1,	// 默认时间长度 s
			sel_value_len:1,	// 默认长度
			sel_value_id:"",	// 默认乐曲id

			// 鼠标点击位置
			mouse_x:0,
			mouse_y:0,

			// 查询乐曲的信息
			cur_id:null,
			cur_savePath:null,
			cur_duration:null,
			cur_authorName:null,
			cur_audioName:null,
			cur_description:null,


			// 提交list
			post_list:[],

			// 音乐地址
			music_url:"",

			info_gen:"生成音乐",
			info_add_track:"添加音轨",
			info_redu_track:"删除音轨",
			info_add:"添加音乐片段",
			info_redu:"删除音乐片段",
			info_edit:"修改音乐片段",
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
<<<<<<< HEAD
	  } else if (title == '音频创作') {
		this.$router.push({path:'/workspace',query:{'id':this.id}})
	  } else if (title == 'ai音频续写') {
		this.$router.push({path:'/create_ai',query:{'id':this.id}})
=======
		} else if (title == '创作中心') {
  	this.$router.push({path:'/workspace',query:{'id':this.id}})
>>>>>>> c6b79d7332c28b2ee6f98290eb9c8937d67c8221
	  } else {
		this.$router.push({path:'/settings',query:{'id':this.id}})
	  };
  	},



		// 添加音轨
		async add_track(){
			await this.add_track_foo()
			this.info_redu_track = "删除音轨";
			this.draw_a()
		},
		add_track_foo(){
			this.track.push({})
			this.track[this.track_num]["id"] = "myCanvas_"+this.track_num
			this.track[this.track_num]["clips"] = []
			this.track_num = this.track_num+1
		},

		// 删除音轨
		async redu_track(){
			await this.redu_track_foo()
			// console.log(this.track_num)
			if (this.track_num == 0){
				this.info_redu_track =  "当前无音轨，无法删除";
			}else{
				this.info_redu_track = "删除音轨";
			}
			this.draw_a()
		},
		redu_track_foo(){
			if(this.track_num >0){
				this.track.pop()
				this.track_num = this.track_num-1
			}
		},


		// 处理位于音轨上的点击，主要用来选择音乐片
		async getSelect(e){
			await this.getSelect_foo(e)
			this.sel_value_len=this.select_clip["len"]
			this.sel_value_len_s=this.select_clip["len_s"]
			this.sel_value_id=this.select_clip["id"]
		},
		// @click="getMouseXY($event)"
		getSelect_foo(e){
			this.mouse_x = e.x //获取鼠标的X坐标（鼠标与屏幕左侧的距离，单位为px
			this.mouse_y = e.y //获取鼠标的Y坐标（鼠标与屏幕顶部的距离，单位为px
			
			// 获取音轨
			var canvas_id = e.target.id
			this.select_clip["canvas_id"] = canvas_id

			// 获取音乐片位置
			const canvas = document.getElementById(canvas_id)
			var loc = Math.floor((this.mouse_x-canvas.getBoundingClientRect().x)/(canvas.width/this.track_clip_num))
			this.select_clip["loc"] = loc

			// 一点初始化
			this.select_clip["id"]=""
			this.select_clip["len"] = 1
			this.select_clip["len_s"] = 1
			this.select_clip["clips_index"]=-1
			// this.select
			var index = this.get_index_from_id(canvas_id)
			var clips  = this.track[index]["clips"]
			for (var i=0; i<clips.length; i++){
				if (loc-clips[i]["loc"] < clips[i]["len"] && loc-clips[i]["loc"] >= 0){
					this.select_clip["clips_index"]=i
					this.select_clip["id"] = clips[i]["id"]
					this.select_clip["loc"] = clips[i]["loc"]
					this.select_clip["len"] = clips[i]["len"]
					this.select_clip["len_s"] = clips[i]["len_s"]
					break
				}
			}


			this.draw_a()

		},

		// 添加音乐片
		async add_clips(){
			await this.add_clips_foo();
			// console.log(this.track)
			this.draw_a();
		},
		// 添加音乐片
		add_clips_foo(){
			var canvas_index = this.get_index_from_id(this.select_clip["canvas_id"]);
			var exist = this.get_clip_exist(canvas_index);
			if(exist == -1){
				// console.log("checking",this.sel_value_id,this.sel_value_len)
				if (this.sel_value_id != ""){
					if (this.select_clip["loc"] + this.sel_value_len <= this.track_clip_num){
						var check = 0;
						for (var i=0;i<this.track[canvas_index]["clips"].length;i++){
							// console.log(i, this.select_clip["loc"]+this.sel_value_len, this.track[canvas_index]["clips"][i]["loc"]+1);
							if ( this.select_clip["loc"] < this.track[canvas_index]["clips"][i]["loc"] &&
								this.select_clip["loc"]+this.sel_value_len >= this.track[canvas_index]["clips"][i]["loc"]+1) {
								// return 
								check = 1;
							}
						}
						if(check == 0){	
							this.select_clip["id"] = this.sel_value_id;
							this.select_clip["len"] = this.sel_value_len;
							this.select_clip["len_s"] = this.sel_value_len_s;
							this.track[canvas_index]["clips"].push(
								{"id":this.sel_value_id, "len":this.sel_value_len, "len_s":this.sel_value_len_s, "loc":this.select_clip["loc"] }
								);
						}else{}
					}else{}
				}else{
				}
			}else{
				// console.log("nooooo")
			}
		},

		// 删除选中的音乐片
		async redu_clips(){
			await this.redu_clips_foo();
			// console.log(this.track)
			this.draw_a();
		},
		// 删除选中音乐片
		redu_clips_foo(){
			var canvas_index = this.get_index_from_id(this.select_clip["canvas_id"]);
			var exist = this.get_clip_exist(canvas_index);
			if(exist != -1){
				console.log(exist);
				this.track[canvas_index]["clips"].splice(exist, 1);
				this.sel_value_id="";
				this.sel_value_len=1;
				this.sel_value_len_s=1;
				this.select_clip["id"]="";
				this.select_clip["len"]=1;
				this.select_clip["len_s"]=1;
			}else{
				// console.log("nooooo")
			}
		},

		// 修改音乐片
		async edit_clips(){
			await this.edit_clips_foo();
			// console.log(this.track)
			this.draw_a();
		},
		// 修改音乐片
		edit_clips_foo(){
			var canvas_index = this.get_index_from_id(this.select_clip["canvas_id"]);
			var exist = this.get_clip_exist(canvas_index);
			if(exist != -1){
				// console.log("checking",this.sel_value_id,this.sel_value_len)
				if (this.sel_value_id != ""){
					if (this.select_clip["loc"] + this.sel_value_len <= this.track_clip_num){
						var check = 0;
						for (var i=0;i<this.track[canvas_index]["clips"].length;i++){
							// console.log(i, this.select_clip["loc"]+this.sel_value_len, this.track[canvas_index]["clips"][i]["loc"]+1);
							if ( this.select_clip["loc"] < this.track[canvas_index]["clips"][i]["loc"] &&
								this.select_clip["loc"]+this.sel_value_len >= this.track[canvas_index]["clips"][i]["loc"]+1) {
								// return 
								check = 1;
							}
						}
						if(check == 0){	
							this.select_clip["id"] = this.sel_value_id;
							this.select_clip["len"] = this.sel_value_len;
							this.select_clip["len_s"] = this.sel_value_len_s;
							this.track[canvas_index]["clips"][exist] = {"id":this.sel_value_id, "len":this.sel_value_len, "len_s":this.sel_value_len_s, "loc":this.select_clip["loc"] };
						}else{}
					}else{
					}
				}else{
				}
			}else{
				// console.log("nooooo")
			}
		},



		basic_line(canvas_id){
			const canvas = document.getElementById(canvas_id)
			// console.log(canvas)
			var context = canvas.getContext('2d')
			// console.log(context)

			// console.log(canvas.width)
			
			context.globalAlpha = 1
			
			for (var i=1; i<=this.track_clip_num-1; i++){
				context.lineWidth=2
				context.strokeStyle = "black"
				context.beginPath()
				context.moveTo(i/this.track_clip_num*canvas.width, 0)
				context.lineTo(i/this.track_clip_num*canvas.width, 50)
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
			context.fillStyle="#6dcbff"
			context.rect(loc*(canvas.width/this.track_clip_num), 0, length*(canvas.width/this.track_clip_num), 50)
			context.fill()
		},

		

		basic_sel(){
			const canvas = document.getElementById(this.select_clip["canvas_id"])
			// console.log(canvas)
			var context = canvas.getContext('2d')

			context.lineWidth=3;
			context.strokeStyle = "blue";
			context.beginPath()
			context.rect(this.select_clip["loc"]*(canvas.width/this.track_clip_num), 0, this.select_clip["len"]*(canvas.width/this.track_clip_num), 50)
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
			for (var i=0; i<this.track_num; i++){
				this.draw(this.track[i]["id"])
			}
			this.basic_sel()
		},

		// 通过索引获得 canvas id
		get_canvas_id(index){
			return "myCanvas_" +index
		},

		// canvas id 转换为 索引
		get_index_from_id(canvas_id){
			for (var i=0;i <this.track_num;i++){
				if (this.track[i]["id"] == canvas_id)
					return i
			}
			return -1
		},

		// 获取选中位置在track的clip列表中是否存在clip，返回索引
		get_clip_exist(canvas_index){
			var clips = this.track[canvas_index]["clips"]
			for (var i=0;i <clips.length;i++){
				if (clips[i]["loc"] == this.select_clip["loc"])
					return i
			}
			return -1
		},

		// clip id改变
		clip_id_change(e){
			console.log(this.music_list);
			this.sel_value_id = e;
			// 可变音乐片长度功能
			// this.sel_value_len=Math.ceil(this.sel_value_len_s/this.track_clip_length);
			
			// 固定音乐片段长度功能
			if (e == ""){
				this.sel_value_len_s = 1;
				this.sel_value_len = 1;
				return
			}
			for (var i=0;i<this.music_list.length;i++){
				if (e == this.music_list[i]["id"]){
					this.sel_value_len_s = this.music_list[i]["duration"];
					this.sel_value_len = Math.ceil(this.music_list[i]["duration"]/this.track_clip_length);
				}
			}
		},

		// clip 音乐长度改变
		clip_len_change(e){
			// 可变音乐片段长度功能
			this.sel_value_len_s = e;
			this.sel_value_len=Math.ceil(e/this.track_clip_length);
		},

		// 查询窗口选择id
		cur_id_change(e){
			this.cur_id = e;
			for (var i=0; i<this.music_list.length;i++){
				if (this.music_list[i]["id"] == e){
					this.cur_savePath = this.music_list[i]["savePath"];
					this.cur_duration = this.music_list[i]["duration"];
					this.cur_authorName = this.music_list[i]["authorName"];
					this.cur_audioName = this.music_list[i]["audioName"];
					this.cur_description = this.music_list[i]["description"];
					break;
				}
			}
			// 
			axios
			.get('http://47.103.149.25:8081/music/musicInfo/detail/'+e)
			.then(response=>{
				// console.log(response.data.data["audioUrl"]);
				this.$refs.audio_2.src = response.data.data["audioUrl"];
			})
			.catch(function (error) {
				this.$refs.audio.src = "";
				console.log(error);
			})
		},

		// 处理音轨单位长度
		track_clip_length_change(e){
			this.track_clip_length = e;
			for (var i=0;i<this.track_num;i++){
				this.track.pop();
			}
			for (var i=0; i<3; i++){
				this.track.push({});
				this.track[i]["id"] = "myCanvas_"+i;
				this.track[i]["clips"] = [];
				this.draw(this.track[i]["id"]);
			}
			this.track_num = 3;
			this.select_clip["canvas_id"]="myCanvas_0";
			this.select_clip["clips_index"]=-1;
			this.select_clip["id"]="";
			this.select_clip["loc"]=0;
			this.select_clip["len"]=1;
			this.select_clip["len_s"]=1;
			this.sel_value_id="";
			this.sel_value_len=1;
			this.sel_value_len_s=1;
		},

		// 处理音轨clip数量
		track_clip_num_change(e){
			this.track_clip_num = e;
			for (var i=0;i<this.track_num;i++){
				this.track.pop();
			}
			for (var i=0; i<3; i++){
				this.track.push({});
				this.track[i]["id"] = "myCanvas_"+i;
				this.track[i]["clips"] = [];
				this.draw(this.track[i]["id"]);
			}
			this.track_num = 3;
			this.select_clip["canvas_id"]="myCanvas_0";
			this.select_clip["clips_index"]=-1;
			this.select_clip["id"]="";
			this.select_clip["loc"]=0;
			this.select_clip["len"]=1;
			this.select_clip["len_s"]=1;
			this.sel_value_id="";
			this.sel_value_len=1;
			this.sel_value_len_s=1.0;
		},

		// 生成音乐 
		async generate_music(){
			await this.generate_music_foo();
			// console.log(this.post_list.length);
			if (this.post_list.length > 0){
				axios.post('http://47.103.149.25:8081/music/musicUpdate/generateMusic',this.post_list)
				// axios.get('http://47.103.149.25:8081/music/musicInfo/detail/1')
				.then((response)=> {
					console.log(response.data);
					this.info_gen = "生成成功，点击重新生成";
					this.music_lsit= response.data.data["url"];
					this.$refs.audio.src = response.data.data["url"];
					// console.log(response.data);.data
				})
				.catch((error) => {
					// console.log(error);
					this.info_gen = "服务器错误，生成失败";
					this.music_list = "";
					this.$refs.audio.src = "";
				});
			}else{
				// console.log(this.post_list);
				this.info_gen = "请在音轨插入内容后重新生成"
				this.music_list = "";
				this.$refs.audio.src = "";
				// console.log(this.music_list);
			}
		},
		// 生成请求列表
		generate_music_foo(){

			this.post_list = [];
			var num = 0;

			for (var i=0; i<this.track_num; i++){
				this.post_list.push([]);
				for (var j=0; j<this.track[i]["clips"].length; j++){
					
					this.post_list[i].push(
						{
							"id":this.track[i]["clips"][j]["id"],
							"savePath":this.get_savePath_from_id(this.track[i]["clips"][j]["id"]),
							"startTime":this.track[i]["clips"][j]["loc"]*this.track_clip_length,
							"duration":this.track[i]["clips"][j]["len_s"],
						}
					)
					num ++;
				}
			}
			if (num == 0){
				console.log("no clips");
				this.post_list = [];
				return 1;
			}
			console.log(this.post_list);
			return 0;
		},


		// 通过id 查找savePath
		get_savePath_from_id(id){
			for(var t=0; t<this.music_list.length; t++){
				if (id == this.music_list[t]["id"]){
					return this.music_list[t]["savePath"];
				}
			}
			return "";
		},

		playmusic(item){
			item.playing=!item.playing;
			console.log(playing);
			if(item.playing){
				this.play();
			}
			else this.pause();
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