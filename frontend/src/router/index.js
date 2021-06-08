import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import CriarTreino from '../views/CriarTreino.vue'
import NotFound from '../views/NotFound.vue'
import Perfil from '../views/User/Perfil.vue'
import Agenda from '../views/User/Agenda.vue'
import Exercicios from '../views/User/Exercicios.vue'
import Treino from '../views/User/Treino.vue'
import IniciarTreino from '../views/User/IniciarTreino.vue'
import Treinos from '../views/User/Treinos.vue'
import Treinadores from '../views/User/Treinadores.vue'

import Treinador_Perfil from '../views/Treinador/Treinador_Perfil.vue'
import Treinador_Agenda from '../views/Treinador/Treinador_Agenda.vue'
import Treinador_Exercicio from '../views/Treinador/Treinador_Exercicios.vue'
import Treinador_Treinos from '../views/Treinador/Treinador_Treinos.vue'
import Treinador_Treino from '../views/Treinador/Treinador_Treino.vue'
import Treinador_Alunos from '../views/Treinador/Treinador_Alunos.vue'

import Administrador_Perfil from '../views/Administrador/Administrador_Perfil.vue'
import Administrador_Exercicios from '../views/Administrador/Administrador_Exercicios.vue'
import Administrador_Treinos from '../views/Administrador/Administrador_Treinos.vue'
import Administrador_Alunos from '../views/Administrador/Administrador_Alunos.vue'
import Administrador_Treinadores from '../views/Administrador/Administrador_Treinadores.vue'
import Administrador_Administradores from '../views/Administrador/Administrador_Administradores.vue'

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
    path: '/treinador/agenda',
    name: 'Treinador_Agenda',
    component: Treinador_Agenda
  },
  {
    path: '/treinador/exercicios',
    name: 'Treinador_Exercicios',
    component: Treinador_Exercicio
  },
  {
    path: '/treinador/treinos',
    name: 'Treinos_Treinador',
    component: Treinador_Treinos
  },
  {
    path: '/treinador/treino/:codigo',
    name: 'Treinador_Treino',
    component: Treinador_Treino
  },
  {
    path: '/treinador/alunos',
    name: 'Treinador_Alunos',
    component: Treinador_Alunos
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