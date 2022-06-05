<template>
  <v-card height="100%" width="60%" flat class="mx-auto">
	<v-card-title class="text-center justify-center py-6">
	  <h2 class="font-weight-bold text-h3" >
		Music Forum
	  </h2>
	</v-card-title>

	<v-tabs
	  v-model="tab"
	  background-color="transparent"
	  color="primary"
	  grow
	>
	  <v-tab
		:key="hh1"
	  >
	  忘记密码
	  </v-tab>
	</v-tabs>
	<v-tabs-items  v-model="tab">
	    <v-tab-item
	  	:key= "hh1"
	    >
		<login></login>
	    </v-tab-item>
	  </v-tabs-items>

  </v-card>
</template>

<script>
	import forge from './components/forge.vue'
	export default {
		components: {
		  forge,
		},
	    data () {
	      return {
			tab: null,
			hh1: '1',
		  }
	    },
	  }

</script>

<style>
</style>
