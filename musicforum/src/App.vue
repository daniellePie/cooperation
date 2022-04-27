<template>
  <v-app>
  	 <!-- <v-card class="overflow-hidden">
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
  	  
  	        <v-btn icon>
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
  	    </v-card> -->
		
		<!-- 原版蓝色bar -->
		  <!-- <v-app-bar
			dense
			max-height="40pt"
		    color="primary"
		    dark
		  >
		    <div class="d-flex align-center">
		      <v-img
		        alt="Vuetify Logo"
		        class="shrink mr-2"
		        contain
		        src="./assets/bee.jpg"
		        transition="scale-transition"
		        width="40"
		      />
		  
		      <v-img
		        alt="Vuetify Name"
		        class="shrink mt-1 hidden-sm-and-down"
		        contain
		        min-width="100"
		        src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png"
		        width="100"
		      />
		    </div>
		  
		    <v-spacer></v-spacer>
		  
		    <v-btn
		      href="https://github.com/vuetifyjs/vuetify/releases/latest"
		      target="_blank"
		      text
		    >
		      <span class="mr-2">退出</span>
		      <v-icon>mdi-open-in-new</v-icon>
		    </v-btn>
		  </v-app-bar> -->
		  <v-main>
		    <router-view/>
		  </v-main>
		</v-app>
</template>

<script>

export default {
  name: 'App',
	
  data () {
    },
};
</script>
