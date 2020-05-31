import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Login",
    component: () => import("@/views/login/index"),
    meta: { title: "登录", bodyBackground: "#fbfbfb" }
  }
];

const router = new VueRouter({
  routes
});

export default router;
