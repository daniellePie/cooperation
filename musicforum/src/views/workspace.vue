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
					<h2>创作中心</h2>
				</div>

				<v-btn-toggle
						v-model="track[0]"
						multiple
					>
					<!-- 两个八度 -->
					<v-btn></v-btn>
					<v-btn></v-btn>
					<v-btn></v-btn>
					<v-btn></v-btn>
					<v-btn></v-btn>
					<v-btn></v-btn>
					<v-btn></v-btn>
					
					<v-btn></v-btn>
					<v-btn></v-btn>
					<v-btn></v-btn>
					<v-btn></v-btn>
					<v-btn></v-btn>
					<v-btn></v-btn>
					<v-btn></v-btn>

				</v-btn-toggle>

				

				<h1>{{track}}</h1>

				
			</v-col >


	  </v-row>
	</v-card>
	</v-card>
</template>

<script>

export default {
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
			track: [
				[],[],[],[]
			], 

			file: "/public/audio/bg-music.mp3"
			
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

		
  },
};
</script>

<style scoped>
	.basil {
	  height: 100%;
	  background-color: #F5F5F5 !important;
	}

</style>