import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/home/home.vue'
import Myfollowing from '../views/myfollowing.vue'
import Settings from '../views/settings.vue'
import Hotaudio from '../views/hotaudio.vue'
import Myself from '../views/myself.vue'
import Register from '../views/register/regis.vue'
import Create_ai from '../views/create_ai.vue'
import Forget from '../views/register/forget.vue'
import Workspace from '../views/workspace.vue'
import Mymessage from '../views/mymessage'
import Mycollecting from'../views/mycollecting'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/create_ai',
    name: 'create_ai',
    component: Create_ai
  },
  {
    path: '/registerorlogin',
    name: 'register',
    component: Register
  },
  {
    path: '/hotaudio',
    name: 'hotaudio',
    component: Hotaudio
  },
  {
    path: '/forget',
    name: 'forget',
    component: Forget
  },
  {
    path: '/settings',
    name: 'settings',
    component: Settings
  },
  {
    path: '/myself',
    name: 'myself',
    component: Myself
  },
  {
    path: '/myfollowing',
    name: 'myfollowing',
    component: Myfollowing
  },
  {
    path: '/workspace',
    name: 'workspace',
    component: Workspace
    },
    {
        path: '/mymessage',
        name: 'mymessage',
        component:Mymessage
    },
    {
        path: '/mycollecting',
        name: 'mycollecting',
        component:Mycollecting
    }
]

const router = new VueRouter({
  routes
})

export default router
