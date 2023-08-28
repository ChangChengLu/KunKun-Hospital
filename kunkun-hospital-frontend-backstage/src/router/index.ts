import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import UserLogin from "../pages/login/UserLogin.vue";
import UserRegister from "../pages/register/UserRegister.vue";
import UserAdd from "../pages/userManager/UserAdd.vue";
import AdminLayout from "../layouts/AdminLayout.vue";

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
    path: "/",
    name: "adminLayout",
    component: AdminLayout,
    meta: {
      isLogin: true,
    },
  },
  {
    path: "/user/manage/add",
    name: "userAdd",
    component: UserAdd,
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
