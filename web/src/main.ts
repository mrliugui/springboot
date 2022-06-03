import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import * as Icons from '@ant-design/icons-vue'

import axios from 'axios'

//axios拦截器
// Add a request interceptor
axios.interceptors.request.use(function (config) {
    // Do something before request is sent
    console.log("请求",config)
    return config;
}, function (error) {
    // Do something with request error
    console.log("请求错误",error)
    return Promise.reject(error);
});

// Add a common interceptor
axios.interceptors.response.use(function (response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with common data
    console.log("返回值",response)
    return response;
}, function (error) {
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    // Do something with common error
    console.log("返回错误",error)
    return Promise.reject(error);
});

//axios的基础配置根路径
axios.defaults.baseURL = process.env.VUE_APP_SERVER


const app = createApp(App)
app.use(store).use(router).use(Antd).mount('#app')

//全局使用该iconfont组件
const icons: any = Icons
for ( const i in icons){
    app.component(i,icons[i])
}
