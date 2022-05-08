import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import VueAxios from 'vue-axios'

Vue.config.productionTip = false
Vue.prototype.$api = axios;

Vue.use(axios)
new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
