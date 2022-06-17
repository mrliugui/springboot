import {createRouter, createWebHashHistory, RouteRecordRaw} from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AdminView from '../views/admin/Admin-Ebook.vue'
import AdminCategoryView from '../views/admin/Admin-Category.vue'
import AdminDocView from '../views/admin/Admin-Doc.vue'
import testVue from '../views/test-v.vue'
import DocDetailVue from '../views/doc/DocDetail.vue'
import AdminUser from '../views/admin/Admin-user.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
    {
        path: '/admin/ebook',
        name: 'ebook',
        component: AdminView
    },
    {
        path: '/ebook/doc',
        name: 'DocDetailVue',
        component: DocDetailVue
    },
    {
        path: '/admin/user',
        name: 'AdminUser',
        component: AdminUser
    },
    {
        path: '/test',
        name: 'test',
        component: testVue
    },
    {
        path: '/admin/doc/',
        name: 'doc',
        component: AdminDocView
    },
    {
        path: '/admin/category',
        name: 'AdminCategoryView',
        component: AdminCategoryView
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

export default router
