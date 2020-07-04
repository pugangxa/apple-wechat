import axios from "axios";
import vue from "vue";
import router from "../router";

let path = "/apple";
let url = window.location.origin + path; //path为上下文路径，如果后端有配置就添加
axios.defaults.baseURL = url;

const request = function(query) {
  return axios
    .request(query)
    .then(res => {
      if (res.data.code === 401) {
        if (window.location.href.split("#/")[1].startsWith("admin")) {
          router.push({ path: "/admin/login" });
        } else {
          router.push({ path: "/login" });
        }
        return Promise.reject(res.data);
      } else if (res.data.code === 500) {
        return Promise.reject(res.data);
      } else if (res.data.code === 501) {
        return Promise.reject(res.data);
      } else if (res.data.code === 502) {
        if (window.location.href.split("#/")[1].startsWith("admin")) {
          router.push({ path: "/admin/login" });
        } else {
          router.push({ path: "/login" });
        }
        return Promise.reject(res.data);
      } else {
        return Promise.resolve(res.data);
      }
    })
    .catch(e => {
      vue.prototype.$notify(e.message);
      return Promise.reject(e.message);
    });
};

const post = function(url, params) {
  const query = {
    url: url,
    method: "post",
    withCredentials: true,
    timeout: 30000,
    data: params,
    headers: { "Content-Type": "application/json", "request-ajax": true }
  };
  return request(query);
};

const get = function(url, params) {
  const query = {
    url: url,
    method: "get",
    withCredentials: true,
    timeout: 30000,
    params: params,
    headers: { "request-ajax": true }
  };
  return request(query);
};

const _delete = function(url, params) {
  const query = {
    url: url,
    method: "delete",
    withCredentials: true,
    timeout: 30000,
    params: params,
    headers: { "Content-Type": "application/json", "request-ajax": true }
  };
  return request(query);
};

const form = function(url, params) {
  const query = {
    url: url,
    method: "post",
    withCredentials: true,
    timeout: 30000,
    data: params,
    headers: { "Content-Type": "multipart/form-data", "request-ajax": true }
  };
  return request(query);
};

export { post, get, _delete, form };
