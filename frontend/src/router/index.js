import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import CriarTreino from '../views/CriarTreino.vue'
import NotFound from '../views/NotFound.vue'
import Perfil from '../views/User/Perfil.vue'
import Agenda from '../views/User/Agenda.vue'
import Exercicios from '../views/Exercicios.vue'
import Treino from '../views/Treino.vue'
import IniciarTreino from '../views/User/IniciarTreino.vue'
import Treinos from '../views/Treinos.vue'
import Treinadores from '../views/User/Treinadores.vue'

import Treinador_Perfil from '../views/Treinador/Treinador_Perfil.vue'
import Treinador_Alunos from '../views/Treinador/Treinador_Alunos.vue'

import Administrador_Login from '../views/Administrador/Administrador_Login.vue'
import Administrador_Perfil from '../views/Administrador/Administrador_Perfil.vue'
import Administrador_Exercicios from '../views/Administrador/Administrador_Exercicios.vue'
import Administrador_Treinos from '../views/Administrador/Administrador_Treinos.vue'
import Administrador_Alunos from '../views/Administrador/Administrador_Alunos.vue'
import Administrador_Treinadores from '../views/Administrador/Administrador_Treinadores.vue'
import Administrador_Administradores from '../views/Administrador/Administrador_Administradores.vue'

import { AuthService } from './token'

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
    path: '/perfil',
    name: 'Perfil',
    component: Perfil
  },
  {
    path: '/agenda',
    name: 'Agenda',
    component: Agenda
  },
  {
    path: '/exercicios',
    name: 'Exercicios',
    component: Exercicios
  },
  {
    path: '/treino/:codigo',
    name: 'Treino',
    component: Treino
  },
  {
    path: '/treinos',
    name: 'Treinos',
    component: Treinos
  },
  {
    path: '/iniciarTreino/:codigo',
    name: 'IniciarTreino',
    component: IniciarTreino,
    props: true
  },
  {
    path: '/criartreino',
    name: 'CriarTreino',
    component: CriarTreino
  },
  {
    path: '/treinadores',
    name: 'Treinadores',
    component: Treinadores
  },
  {
    path: '/treinador/perfil',
    name: 'Treinador_Perfil',
    component: Treinador_Perfil
  },
  {
    path: '/treinador/alunos',
    name: 'Treinador_Alunos',
    component: Treinador_Alunos
  },
  {
    path: '/administrador/login',
    name: 'Administrador_Login',
    component: Administrador_Login
  },
  {
    path: '/administrador/alunos',
    name: 'Administrador_Alunos',
    component: Administrador_Alunos
  },
  {
    path: '/administrador/treinadores',
    name: 'Administrador_Treinadores',
    component: Administrador_Treinadores
  },
  {
    path: '/administrador/treinos',
    name: 'Administrador_Treinos',
    component: Administrador_Treinos
  },
  {
    path: '/administrador/exercicios',
    name: 'Administrador_Exercicios',
    component: Administrador_Exercicios
  },
  {
    path: '/administrador/Administradores',
    name: 'Administrador_Administradores',
    component: Administrador_Administradores
  },
  {
    path: '/administrador/Perfil',
    name: 'Administrador_Perfil',
    component: Administrador_Perfil
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

// Auth behavior
router.beforeEach((to, from, next) => {

  const isAuthenticated = AuthService.getToken();
  console.log(isAuthenticated)

  if (isAuthenticated == null) {
    if (to.name == 'Perfil') next('/login') // Go to index
    if (to.name == 'Agenda') next('/login') // Go to index
    if (to.name == 'Exercicios') next('/login') // Go to index
    if (to.name == 'Treino') next('/login') // Go to index
    if (to.name == 'Treinos') next('/login') // Go to index
    if (to.name == 'IniciarTreino') next('/login') // Go to index
    if (to.name == 'CriarTreino') next('/login') // Go to index
    if (to.name == 'Treinadores') next('/login') // Go to index
    if (to.name == 'Treinador_Perfil') next('/login') // Go to index
    if (to.name == 'Treinador_Alunos') next('/login') // Go to index
    if (to.name == 'Administrador_Alunos') next('/login') // Go to index
    if (to.name == 'Administrador_Treinadores') next('/login') // Go to index
    if (to.name == 'Administrador_Treinos') next('/login') // Go to index
    if (to.name == 'Administrador_Exercicios') next('/login') // Go to index
    if (to.name == 'Administrador_Administradores') next('/login') // Go to index
    if (to.name == 'Administrador_Perfil') next('/login') // Go to index
    else next() // Not logged, it's free
  }
  else {

    const hasAuth = AuthService.validateToken();

    // User
    if (hasAuth && AuthService.getUsertype() == 0) {
      if (to.name == 'Login') next('/perfil') // Go to index
      if (to.name == 'Home') next('/perfil') // Go to index
      if (to.name == 'About') next('/perfil') // Go to index
      if (to.name == 'Treinadores') next('/perfil') // Go to index
      if (to.name == 'Treinador_Perfil') next('/perfil') // Go to index
      if (to.name == 'Treinador_Alunos') next('/perfil') // Go to index
      if (to.name == 'Administrador_Alunos') next('/perfil') // Go to index
      if (to.name == 'Administrador_Treinadores') next('/perfil') // Go to index
      if (to.name == 'Administrador_Treinos') next('/perfil') // Go to index
      if (to.name == 'Administrador_Exercicios') next('/perfil') // Go to index
      if (to.name == 'Administrador_Administradores') next('/perfil') // Go to index
      if (to.name == 'Administrador_Perfil') next('/perfil') // Go to index
      if (to.name == 'Administrador_Login') next('/perfil') // Go to index
      else next()
    }

    // Treinador
    if (hasAuth && AuthService.getUsertype() == 1) {
      if (to.name == 'Login') next('/treinador/perfil') // Go to index
      if (to.name == 'Home') next('/treinador/perfil') // Go to index
      if (to.name == 'About') next('/treinador/perfil') // Go to index
      if (to.name == 'Perfil') next('/treinador/perfil') // Go to index
      if (to.name == 'Agenda') next('/treinador/perfil') // Go to index
      if (to.name == 'IniciarTreino') next('/treinador/perfil') // Go to index
      if (to.name == 'Administrador_Alunos') next('/treinador/perfil') // Go to index
      if (to.name == 'Administrador_Treinadores') next('/treinador/perfil') // Go to index
      if (to.name == 'Administrador_Treinos') next('/treinador/perfil') // Go to index
      if (to.name == 'Administrador_Exercicios') next('/treinador/perfil') // Go to index
      if (to.name == 'Administrador_Administradores') next('/treinador/perfil') // Go to index
      if (to.name == 'Administrador_Perfil') next('/treinador/perfil') // Go to index
      if (to.name == 'Administrador_Login') next('/treinador/perfil') // Go to index
      else next()
    }

    // Admin
    if (hasAuth && AuthService.getUsertype() == 2) {
      if (to.name == 'Login') next('/administrador/Perfil') // Go to index
      if (to.name == 'Home') next('/administrador/Perfil') // Go to index
      if (to.name == 'About') next('/administrador/Perfil') // Go to index
      if (to.name == 'Perfil') next('/administrador/Perfil') // Go to index
      if (to.name == 'Agenda') next('/administrador/Perfil') // Go to index
      if (to.name == 'Exercicios') next('/administrador/Perfil') // Go to index
      if (to.name == 'Treino') next('/administrador/Perfil') // Go to index
      if (to.name == 'Treinos') next('/administrador/Perfil') // Go to index
      if (to.name == 'IniciarTreino') next('/administrador/Perfil') // Go to index
      if (to.name == 'CriarTreino') next('/administrador/Perfil') // Go to index
      if (to.name == 'Treinadores') next('/administrador/Perfil') // Go to index
      if (to.name == 'Treinador_Perfil') next('/administrador/Perfil') // Go to index
      if (to.name == 'Treinador_Alunos') next('/administrador/Perfil') // Go to index
      else next()
    }

  }
})


export default router