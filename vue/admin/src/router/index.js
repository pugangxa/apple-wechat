import Vue from "vue";
import VueRouter from "vue-router";
import Layout from "@/views/layout";

Vue.use(VueRouter);

const routes = [
  {
    path: "/login",
    name: "Login",
    component: () => import("@/views/login/index"),
    meta: { title: "登录", bodyBackground: "#fbfbfb" }
  },
  {
    path: "/register",
    name: "Register",
    component: () => import("@/views/register/index"),
    meta: { title: "注册", bodyBackground: "#fbfbfb" }
  },
  {
    path: "/",
    component: Layout,
    redirect: "/index",
    children: [
      {
        path: "index",
        component: () => import("@/views/dashboard/index"),
        name: "Dashboard",
        meta: { title: "首页" }
      }
    ]
  },
  {
    path: "/action",
    component: Layout,
    children: [
      {
        path: "index",
        component: () => import("@/views/action/index"),
        name: "Actions",
        meta: { title: "发布" }
      }
    ]
  },
  {
    path: "/user",
    component: Layout,
    children: [
      {
        path: "index",
        component: () => import("@/views/user-info/index"),
        name: "UserInfo",
        meta: { title: "个人中心" }
      }
    ]
  }
];

const router = new VueRouter({
  routes
});

export default router;
