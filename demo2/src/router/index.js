import { createRouter } from 'vue-router'
import { createWebHashHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import NotFound from '../views/NotFound.vue'
import ForgetPwd from '../views/ForgetPwd.vue'



const routes = [
  {path:'/',component:Home},
  {path:'/login',component:Login},
  {path:'/register',component:Register},
  {path:'/forgetPwd',component:ForgetPwd},
  {path: '/path(.*)',component:NotFound}

]

const router = createRouter({
  history:createWebHashHistory(),
  routes:routes
})

export default router