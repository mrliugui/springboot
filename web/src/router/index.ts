import {createRouter, createWebHashHistory, RouteRecordRaw} from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AdminView from '../views/admin/Admin-Ebook.vue'
import AdminCategoryView from '../views/admin/Admin-Category.vue'
import AdminDocView from '../views/admin/Admin-Doc.vue'
import testVue from '../views/test-v.vue'
import DocDetailVue from '../views/doc/DocDetail.vue'
import AdminUser from '../views/admin/Admin-user.vue'
import store from '../store'
import {Tool} from "@/util/tool";


const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
    {
        path: '/admin/ebook',
        name: 'ebook',
        component: AdminView,
        meta:{
            loginBoolean:true
        }
    },
    {
        path: '/ebook/doc',
        name: 'DocDetailVue',
        component: DocDetailVue
    },
    {
        path: '/admin/user',
        name: 'AdminUser',
        component: AdminUser,
        meta:{
            loginBoolean:true
        }
    },
    {
        path: '/test',
        name: 'test',
        component: testVue
    },
    {
        path: '/admin/doc/',
        name: 'doc',
        component: AdminDocView,
        meta:{
            loginBoolean:true
        }
    },
    {
        path: '/admin/category',
        name: 'AdminCategoryView',
        component: AdminCategoryView,
        meta:{
            loginBoolean:true
        }
    },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})
router.beforeEach(
    (to,from,next)=>{
        //匹配自定义属性来判断是否进行登录效验
        if(to.matched.some(function (item) {
            console.log("是否需要登录效验",item.meta.loginBoolean)
            return item.meta.loginBoolean;
        })){
            const token = store.state.user.token
            if(Tool.isNotEmpty(token)){
               // 表示用户登录过了放行
                console.log("登陆过放行")
               next();
            }
            else{
                console.log("没有登录")
                next("/")
            }
        }else{
            next()
        }

            }
// 进入所有页面执行的逻辑
// router.beforeEach((to, from, next) => {
//     const { USER } = sessionStorage
//     const { name } = to
//     const isLogin = (name === 'login' || name === 'register');
//     (isLogin) ? next() : next({ name: 'login' })
// })
)

export default router
