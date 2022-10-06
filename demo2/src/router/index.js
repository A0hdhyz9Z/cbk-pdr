import { createRouter } from 'vue-router'
import { createWebHashHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import NotFound from '../views/NotFound.vue'


const routes = [
  {path:'/',component:Home},
  {path:'/Login',component:Login},
  {path:'/Register',component:Register},
  {path: '/path(.*)',component:NotFound}

]

const router = createRouter({
  history:createWebHashHistory(),
  routes:routes
})

export default router