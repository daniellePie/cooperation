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
	  <div class="about">
	    <v-file-input
			v-model="files"
	        show-size
	        counter
	        multiple
	        label="上传文件"
	      ></v-file-input>

		  <h2 class="text-center font-weight-medium"> 点击试听 </h2>
		  <vuetify-audio flat :file="file" :ended="audioFinish"></vuetify-audio>
		  <v-row justify="center">
		    <v-dialog
		      v-model="dialog"
		      persistent
		      max-width="600px"
		    >
		      <template v-slot:activator="{ on, attrs }">
		        <v-btn
		          color="primary"
		          dark
		          v-bind="attrs"
		          v-on="on"
		        >
		          发布
		        </v-btn>
		      </template>
		      <v-card>
		        <v-card-title>
		          <span class="text-h5">发布音频</span>
		        </v-card-title>
		        <v-card-text>
		          <v-container>
		            <v-row>
		              <v-col
		                cols="12"
		              >
		                <v-text-field
		                  label="标题"
						  v-model="title"
		                  required
		                ></v-text-field>
		              </v-col>
					  <v-col cols="12">
					  <v-textarea
						v-model="bio"
						color="teal"
					  >
						<template v-slot:label>
						  <div>
							详情
						  </div>
						</template>
					  </v-textarea>
					</v-col>
					<v-col cols="12">
					<v-file-input
					v-model="image"
					    show-size
					    counter
					    multiple
					    label="上传图片"
					  ></v-file-input>
					  </v-col>
					<v-col cols="12">
					<v-checkbox
					  v-model="hhh"
					  color="green"
					>
					  <template v-slot:label>
						<div @click.stop="">
						  同意
						  <a
							href="#"
							@click.prevent="terms = true"
						  >隐私条款</a>
						  与
						  <a
							href="#"
							@click.prevent="terms = true"
						  >用户协议</a>。
						</div>
					  </template>
					</v-checkbox>
				  </v-col>
		            </v-row>
		          </v-container>
				  
		        </v-card-text>
		        <v-card-actions>
		          <v-spacer></v-spacer>
		          <v-btn
		            color="blue darken-1"
		            text
		            @click="dialog = false"
		          >
		            关闭
		          </v-btn>
		          <v-btn
		            color="blue darken-1"
		            text
		            @click="det1"
		          >
		            发布
		          </v-btn>
		        </v-card-actions>
		      </v-card>
		    </v-dialog>
		  </v-row>
	  </div>
	  </v-col >
	  </v-row>
    </v-card>
</v-card>
</template>

<script>
export default {
	components: {
	        VuetifyAudio: () => import('vuetify-audio'),
	    },
  data () {
    return {
	dialog: false,
      drawer: true,
	  files: [],
	  image: [],
	  bio:'',
	  terms:false,
	  title:'',
	  hhh:'',
	  id:11,
	  res: [],
	  status: '',
	  file: 'http://m10.music.126.net/20220605030405/b54336f31a10fc54a328178c45c3a2dc/ymusic/428b/11a8/1dc6/3de8ffa538c3ad6f3087a6905b14ca70.mp3',
	  info:{ name:'ymym', img:'https://i.ibb.co/H7vjrLr/3.jpg'},
      items: [
        { title: '首页', icon: 'mdi-home-city' },
        { title: '我的关注', icon: 'mdi-account' },
        { title: '热门音频', icon: 'mdi-account-group-outline' },
		{ title: '个人中心', icon: 'mdi-account-group-outline' },
		{ title: '设置', icon: 'mdi-account-group-outline' },
      ],
      mini: true,
    }
  },
  
  mounted(){
	  axios.post('http://localhost:8081/audiocontinuation', {
	  	msg:'audiocontinuation',       
	  	file: this.files,
	    })
	   .then(res=>{
	     this.file = res.data.newfile;
	   })
	    .catch(error => console.log(error));
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
	det1(){
		axios.post('http://localhost:8081/audiocnpost', {
			msg:'audiocnpost',       
			id: this.id,
			title: this.title,
			newnewfile: this.file,
			details: this.bio,
			image: this.image,
		  })
		 .then(res=>{
		   this.status = res.data.status;
		 })
		  .catch(error => console.log(error));
		
		if (this.status == '0'){
			alert('发布成功！')
		} else {
			alert('发布失败！')
		}
		
		this.$router.push({path:'/'})
		  
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



