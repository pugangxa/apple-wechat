import axios from "axios";
import vue from "vue";
import router from "../router";

const request = function(query) {
  return axios
    .request(query)
    .then(res => {
      if (res.data.code === 401) {
        router.push({ path: "/login" });
        return Promise.reject(res.data);
      } else if (res.data.code === 500) {
        return Promise.reject(res.data);
      } else if (res.data.code === 501) {
        return Promise.reject(res.data);
      } else if (res.data.code === 502) {
        router.push({ path: "/login" });
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
  return request(false, query);
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
  return request(false, query);
};

export { post, get, form };
