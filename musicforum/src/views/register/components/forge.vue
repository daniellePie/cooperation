<template>
	<v-card ref="form" flat class="overflow-hidden">
		<v-col >
	        <v-card-text>
				<v-row >
					<v-text-field
					ref="newnew"
					type="number"
					v-model="newnew.phone"
					:rules="[
					() => !!newnew.phone || '需要填写用户手机号',
					() => !!newnew.phone && newnew.name.length <= 11 || '手机号格式不正确',
					]"
					label="手机号"
					prepend-icon="mdi-calendar"
					required
					>
					</v-text-field>
					<v-btn color="primary" :disabled="!canClick" @click="code">{{ getCode }}
					</v-btn>
					
				</v-row>	  
				<h3 class="text-center justify-center font-weight-medium text-subtitle-1"> 请输入验证码</h3>
				<v-otp-input
				 type="number"
				length="6"
				v-model="newnew.code"
				></v-otp-input>
			  <v-text-field
				 v-model="newnew.password"
				 prepend-icon="mdi-calendar"
				 :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
				 :rules="[rules.required, rules.min]"
				 :type="show1 ? 'text' : 'password'"
				 name="input-10-1"
				 label="请输入密码"
				 hint="至少8位密码"
				 counter
				 @click:append="show1 = !show1"
			  ></v-text-field>	   
				<v-text-field
				v-model="newnew.password1"
				prepend-icon="mdi-calendar"
				:append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
				:rules="[rules.eqb]"
				:type="show2 ? 'text' : 'password'"
				name="input-10-1"
				label="请重复输入密码"
				counter
				@click:append="show2 = !show2"
			  ></v-text-field>
			</v-card-text>
	</v-col>
			<v-card-actions>
					<v-btn
					depressed
					block
					  color="primary"
					  @click="hh"
					>
					  提交
					</v-btn>
			</v-card-actions> 
	  </v-card>
</template>

<script>
	export default {
	  data: () => ({
		  show1: false,
		  show2: false,
		  rules: {
		    required: value => !!value || '请输入密码',
		    min: v => v.length >= 8 || '至少8位密码',
		    eqb: () => !!checkpasswd(password, password1) || '两次密码不匹配',
		  },
		newnew : {name:'', password:'', password1:'', code:'', phone:''},
		status: '0',
		id: 11,
		canClick:true, 
		totalTime:60,
		getCode:'获取验证码',
	  }),
	
	  methods: {
	    checkpasswd(password, password1){
	    	if (password === password1) return true
	    	else return false
	    },
		hh(){
			axios.post('http://localhost:8081/forget', {
				msg:'forget',       
				newpassword: this.newnew.password,
				phone: this.newnew.phone,
			  })
			 .then(res=>{
			   this.status = res.data.status;
			   this.id = res.data.id;
			 })
			  .catch(error => console.log(error));
			if (this.status == '0'){
				alert('修改成功，请前往登录')
				this.$router.push({path:'/registerorlogin'})
			} else {
				alert("该用户尚未注册，请前往注册");
			};
		},
		verificationCode () {
		   if (!this.canClick) return  
		   this.canClick = false
		   this.getCode = this.totalTime + 's后重新发送'
		   let clock = window.setInterval(() => {
		     this.totalTime--
		     this.getCode = this.totalTime + 's后重新发送'
		     if (this.totalTime < 0) {
		       window.clearInterval(clock)
		       this.getCode = '重新发送验证码'
		       this.totalTime = 60
		       this.canClick = true  
		     }
		   },1000)
		 },
	  },
	}
</script>

<style>
	.basil {
	  background-color: #FFFBE6 !important;
	}
	.basil--text {
	  color: #90A4AE !important;
	}
</style>
