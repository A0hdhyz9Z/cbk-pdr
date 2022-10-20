import axios from "axios";
import { ElNotification } from "element-plus";

// 创建axios实例
const service = axios.create({
  //baseURL: "http://124.70.62.142:8081/",
  baseURL: "/api",
  timeout: 10000, // 请求超时时间
});

// request拦截器
service.interceptors.request.use(
  (config) => {
    // config.headers["Content-Type"] = "application/json";
    config.headers["Content-Type"] = "multipart/form-data";
    return config;
  },
  (error) => {
    // Do something with request error
    console.log(error); // for debug
    Promise.reject(error);
  }
);

// response 拦截器
service.interceptors.response.use(
  (response) => {
    const code = response.status;
    if (code < 200 || code > 300) {
      ElNotification.error({
        title: response.message,
      });
      return Promise.reject("error");
    } else {
      return response.data;
    }
  },
  (error) => {
    ElNotification.error({
      title: "接口请求失败",
      duration: 5000,
    });
    return Promise.reject(error);
  }
);
export default service;
