import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
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
    path: "/layout",
    name: "Layout",
    component: () => import("@/views/layout/index"),
    meta: { title: "测试", bodyBackground: "#fbfbfb" }
  }
];

const router = new VueRouter({
  routes
});

export default router;
