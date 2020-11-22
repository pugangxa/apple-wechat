import Vue from "vue";
import VueRouter from "vue-router";
import Layout from "@/views/layout";
import AdminLayout from "@/adminviews/layout";

Vue.use(VueRouter);

//resolve the 重复点击错误
//https://www.cnblogs.com/xinheng/p/13019818.html
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err);
};

const originalReplace = VueRouter.prototype.replace;
VueRouter.prototype.replace = function replace(location) {
  return originalReplace.call(this, location).catch(err => err);
};

const routes = [
  {
    path: "/wxerror",
    name: "WxError",
    component: () => import("@/views/error/wxerror"),
    meta: { title: "出错啦", bodyBackground: "#fbfbfb" }
  },
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
    redirect: "/dashboard/index",
    children: [
      {
        path: "index",
        component: () => import("@/views/dashboard/index"),
        name: "",
        meta: { title: "首页" }
      }
    ]
  },
  {
    path: "/dashboard",
    component: Layout,
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
    path: "/action/labor",
    name: "laborAction",
    component: () => import("@/views/action/components/labor"),
    meta: { title: "劳务信息发布", bodyBackground: "#fbfbfb" }
  },
  {
    path: "/action/supply",
    name: "supplyAction",
    component: () => import("@/views/action/components/supply"),
    meta: { title: "供需信息发布", bodyBackground: "#fbfbfb" }
  },
  {
    path: "/action/farmer",
    name: "farmerAction",
    component: () => import("@/views/action/components/farmer"),
    meta: { title: "果农信息发布", bodyBackground: "#fbfbfb" }
  },
  {
    path: "/action/merchant",
    name: "merchantAction",
    component: () => import("@/views/action/components/merchant"),
    meta: { title: "果商信息发布", bodyBackground: "#fbfbfb" }
  },
  {
    path: "/action/expert",
    name: "expertAction",
    component: () => import("@/views/action/components/expert"),
    meta: { title: "专家信息发布", bodyBackground: "#fbfbfb" }
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
  },
  {
    path: "/user/profile",
    name: "profile",
    component: () => import("@/views/user-info/profile"),
    meta: { title: "修改资料", bodyBackground: "#fbfbfb" }
  },
  {
    path: "/user/publish",
    name: "publish",
    component: () => import("@/views/user-info/publish"),
    meta: { title: "我的发布", bodyBackground: "#fbfbfb" }
  },
  {
    path: "/user/about",
    name: "about",
    component: () => import("@/views/user-info/about"),
    meta: { title: "关于", bodyBackground: "#fbfbfb" }
  },

  //admin routes
  {
    path: "/admin/login",
    name: "adminLogin",
    component: () => import("@/adminviews/login/index"),
    meta: { title: "登录", bodyBackground: "#fbfbfb" }
  },
  {
    path: "/admin",
    component: AdminLayout,
    redirect: "/admin/manage/index",
    children: [
      {
        path: "index",
        component: () => import("@/adminviews/manage/index"),
        name: "",
        meta: { title: "用户管理" }
      }
    ]
  },
  {
    path: "/admin/audit",
    component: AdminLayout,
    children: [
      {
        path: "index",
        component: () => import("@/adminviews/audit/index"),
        name: "audit",
        meta: { title: "信息审核" }
      }
    ]
  },
  {
    path: "/admin/manage",
    component: AdminLayout,
    children: [
      {
        path: "index",
        component: () => import("@/adminviews/manage/index"),
        name: "manage",
        meta: { title: "用户管理" }
      }
    ]
  },
  {
    path: "/admin/user",
    component: AdminLayout,
    children: [
      {
        path: "index",
        component: () => import("@/adminviews/user-info/index"),
        name: "adminInfo",
        meta: { title: "个人中心" }
      }
    ]
  },
  {
    path: "/admin/user/profile",
    name: "AdminProfile",
    component: () => import("@/adminviews/user-info/profile"),
    meta: { title: "修改资料", bodyBackground: "#fbfbfb" }
  },
  {
    path: "/admin/user/about",
    name: "AdminAbout",
    component: () => import("@/adminviews/user-info/about"),
    meta: { title: "关于", bodyBackground: "#fbfbfb" }
  }
];

const router = new VueRouter({
  routes
});

export default router;
