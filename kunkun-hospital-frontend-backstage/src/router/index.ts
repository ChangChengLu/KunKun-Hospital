import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import UserLogin from "../pages/login/UserLogin.vue";
import UserRegister from "../pages/register/UserRegister.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/user/login",
    name: "userLogin",
    component: UserLogin,
    meta: {
      isLogin: false,
    },
  },
  {
    path: "/user/register",
    name: "userRegister",
    component: UserRegister,
    meta: {
      isLogin: false,
    },
  },
  {
    path: "/user/manage",
    name: "userManager",
    component: UserRegister,
    meta: {
      isLogin: true,
    },
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
