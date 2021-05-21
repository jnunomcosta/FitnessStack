import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import NotFound from '../views/NotFound.vue'
import Perfil from '../views/User/Perfil.vue'
import Agenda from '../views/User/Agenda.vue'
import ProcurarExercicio from '../views/User/ProcurarExercicio.vue'
import ProcurarTreino from '../views/User/ProcurarTreino.vue'

import Treinadores from '../views/User/Treinadores.vue'
import Perfil_Treinador from '../views/Treinador/Perfil.vue'
import Alunos from '../views/Treinador/Alunos.vue'

import Administrador_Alunos from '../views/Administrador/Administrador_Alunos.vue'
import Administrador_Treinadores from '../views/Administrador/Administrador_Treinadores.vue'
import Administrador_Administradores from '../views/Administrador/Administrador_Administradores.vue'
import Administrador_Treinos from '../views/Administrador/Administrador_Treinos.vue'
import Administrador_Exercicios from '../views/Administrador/Administrador_Exercicios.vue'
import Administrador_Perfil from '../views/Administrador/Administrador_Perfil.vue'

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
    name: 'ProcurarExercicio',
    component: ProcurarExercicio
  },
  {
    path: '/treinos',
    name: 'ProcurarTreino',
    component: ProcurarTreino
  },
  {
    path: '/treinadores',
    name: 'Treinadores',
    component: Treinadores
  },
  {
    path: '/Perfil_Treinador',
    name: 'Perfil_Treinador',
    component: Perfil_Treinador
  },
  {
    path: '/treinador/alunos',
    name: 'Alunos',
    component: Alunos
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
    path: '/administrador',
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

export default router