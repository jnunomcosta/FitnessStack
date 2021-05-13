import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import NotFound from '../views/NotFound.vue'
import PaginaInicial_User from '../views/User/PaginaInicial_User.vue'
import ProcurarTreino from '../views/User/ProcurarTreino.vue'
import CriarTreino from '../views/User/CriarTreino.vue'
import Perfil from '../views/User/Perfil.vue'
import Treinadores from '../views/User/Treinadores.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: function () {
      return import(/* webpackChunkName: "about" */ '../views/About.vue')
    },
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/PaginaInicial_User',
    name: 'PaginaInicial_User',
    component: PaginaInicial_User
  },
  {
    path: '/treinos',
    name: 'ProcurarTreino',
    component: ProcurarTreino
  },
  {
    path: '/treinos/criar',
    name: 'CriarTreino',
    component: CriarTreino
  },
  {
    path: '/perfil',
    name: 'Perfil',
    component: Perfil
  },
  {
    path: '/treinadores',
    name: 'Treinadores',
    component: Treinadores
  },
  {
    // path: "*",
    path: "/:catchAll(.*)",
    name: "NotFound",
    component: NotFound,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router