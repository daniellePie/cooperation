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
      <div>
	  <v-card ref="form" flat class="mx-auto">
  <v-col
    cols="12"
    sm="6"
    offset-sm="3"
  >
  <div class = "teal">
  <v-subheader :inset="inset" >
        账号信息
      </v-subheader>
  </div>
  <div class = "blue">
  <v-divider></v-divider>
  </div>
  <v-card-text>
  <form>
    <v-text-field
      v-model="name"
      :error-messages="nameErrors"
      :counter="10"
      label="用户昵称"
      required
      @input="$v.name.$touch()"
      @blur="$v.name.$touch()"
    ></v-text-field>
    <v-text-field
      v-model="email"
      :error-messages="emailErrors"
      label="邮箱"
      required
      @input="$v.email.$touch()"
      @blur="$v.email.$touch()"
    ></v-text-field>
    <v-text-field
      v-model="myself"
      :error-messages="textErrors"
      :counter="30"
      label="个人简介"
      required
      @input="$v.text.$touch()"
      @blur="$v.text.$touch()"
    ></v-text-field>
    </form>
    </v-card-text>
    </v-col>
	</v-card>  
  <v-card ref="form" flat class="mx-auto">
  <v-col
    cols="12"
    sm="6"
    offset-sm="3"
  >
  <div class = "teal">
  <v-subheader :inset="inset" >
        个人信息
      </v-subheader>
  </div>
  <div class = "blue">
  <v-divider></v-divider>
  </div>
  <v-container fluid>
    <v-radio-group
      v-model="row"
      row
    >
      <v-radio
        label="男"
        value="radio-1"
      ></v-radio>
      <v-radio
        label="女"
        value="radio-2"
      ></v-radio>
    </v-radio-group>
  </v-container>
  <form>
    <v-menu
      ref="menu"
      v-model="menu"
      :close-on-content-click="false"
      transition="scale-transition"
      offset-y
      min-width="auto"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-text-field
          v-model="date"
          label="Birthday date"
          prepend-icon="mdi-calendar"
          readonly
          v-bind="attrs"
          v-on="on"
        ></v-text-field>
      </template>
      <v-date-picker
        v-model="date"
        :max="(new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10)"
        min="1950-01-01"
        @change="save"
      ></v-date-picker>
    </v-menu>
  </form>
  </v-card-text>
  </v-col>
	</v-card>
 <v-card ref="form" flat class="mx-auto">
  <v-col
    cols="12"
    sm="6"
    offset-sm="3"
  >
  <div class = "teal">
  <v-subheader :inset="inset" >
        访问权限
      </v-subheader>
  </div>
  <div class = "blue">
  <v-divider></v-divider>
  </div>
  <v-container fluid>
    <v-radio-group
      v-model="column"
      column
    >
      <v-radio
        label="允许所有人观看我的作品"
        value="radio-1"
      ></v-radio>
      <v-radio
        label="仅允许好友观看我的作品"
        value="radio-2"
      ></v-radio>
      <v-radio
        label="仅允许我观看我的作品"
        value="radio-3"
      ></v-radio>
    </v-radio-group>
  </v-container>
  <v-row aligh="center" justify="space-around">
  <v-checkbox
      v-model="checkbox"
      :error-messages="checkboxErrors"
      label="是否同意提交?"
      required
      @change="$v.checkbox.$touch()"
      @blur="$v.checkbox.$touch()"
    ></v-checkbox>
    </v-row>
    <v-row aligh="center" justify="space-around">
    <v-btn
      class="white"
      width="30%"
      @click="提交"
    >
      submit
    </v-btn>
    <v-btn 
      class="white"
      width="30%"
      @click="清除">
      clear
    </v-btn>
    </v-row>
  </v-col>
</v-card>
    </div>
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
	  id:11,
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
  },
};
</script>

<style scoped>
	.basil {
	  height: 100%;
	  background-color: #F5F5F5 !important;
	}



</style>
