<template>
	<v-card ref="form" flat class="mx-auto">
		<v-col>
	        <v-card-text>
			  <v-text-field
			    ref="login"
			    v-model="login.sponsor"
			    :rules="[
			      () => !!login.sponsor || '需要填写用户昵称',
			      () => !!login.sponsor && login.sponsor.length <= 20 || '用户昵称不能为空',
			    ]"
			    label="用户昵称"
			  	prepend-icon="mdi-calendar"
			    counter="20"
			    required
			  ></v-text-field>
		
			  <v-text-field
				 v-model="login.password"
				 prepend-icon="mdi-calendar"
				 :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
				 :rules="[rules.required, rules.min]"
				 :type="show1 ? 'text' : 'password'"
				 name="input-10-1"
				 label="请输入密码"
				 counter
				 @click:append="show1 = !show1"
			  ></v-text-field>
	        </v-card-text>
			
			<v-card-action>
				<v-row aligh="center" justify="space-around">	
					<v-btn
					  depressed
					  color="primary"
					  width="30%"
					  @click="det1"
					>
					  登录
					</v-btn>  
					<v-btn
					  depressed
					  color="primary"
					  width="30%"
					  @click="det2"
					>
					  忘记密码
					</v-btn>  
				</v-row>
			</v-card-action>
		</v-col>
	</v-card>
</template>

<script>
	export default {
	  data: () => ({
		  show1: false,
		  id:1,
		  rules: {
		    required: value => !!value || '请输入密码',
		    min: v => v.length >= 8 || '最少8位密码',
		  },
		login : {name:'', password:''}
	  }),
	
	  methods: {
		det1(){
		  this.$router.push({path:'/'});
		  alert("登录成功")
		},
		det2(){
		  this.$router.push({path:'/forget'});
		},
	  },
	}
</script>

<style>
</style>
