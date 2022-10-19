import { createRouter } from "vue-router";
import { createWebHashHistory } from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
import NotFound from "../views/NotFound.vue";
import ForgetPwd from "../views/ForgetPwd.vue";
import AppLayout from "../views/AppLayout.vue";
import Order from "../views/Order.vue";
import History from "../views/History.vue";
import Show from "../views/Show.vue";


const routes = [
  { path: "/", component: Home },
  { path: "/login", component: Login },
  { path: "/register", component: Register },
  { path: "/forgetPwd", component: ForgetPwd },
  { path: "/path(.*)", component: NotFound },
  {
    path: "/AppLayout",
    component: AppLayout,
    children: [
      {
        path: "/Order",
        name: "/Order",
        component: Order,
      },
      {
        path: "/History",
        name: "/History",
        component: History,
      },
      {
        path: "/Show/:id/:name/:description/:dataSet/:alg",
        name: "/Show",
        component: Show,
      },
    ],
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes: routes,
});

export default router;
