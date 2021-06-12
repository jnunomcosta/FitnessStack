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
import Treinador_Exercicio from '../views/Treinador/Treinador_Exercicios.vue'
import Treinador_Treinos from '../views/Treinador/Treinador_Treinos.vue'
import Treinador_Treino from '../views/Treinador/Treinador_Treino.vue'
import Treinador_Alunos from '../views/Treinador/Treinador_Alunos.vue'

import Administrador_Login from '../views/Administrador/Administrador_Login.vue'
import Administrador_Perfil from '../views/Administrador/Administrador_Perfil.vue'
import Administrador_Exercicios from '../views/Administrador/Administrador_Exercicios.vue'
import Administrador_Treinos from '../views/Administrador/Administrador_Treinos.vue'
import Administrador_Alunos from '../views/Administrador/Administrador_Alunos.vue'
import Administrador_Treinadores from '../views/Administrador/Administrador_Treinadores.vue'
import Administrador_Administradores from '../views/Administrador/Administrador_Administradores.vue'

// import { AuthService } from './token'

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

// // Auth behavior
// router.beforeEach((to, from, next) => {

//   const isAuthenticated = AuthService.getToken();
//   console.log(isAuthenticated)

//   if (isAuthenticated == null) {
//     if (to.name == 'Perfil') next('/login') // Go to index
//     if (to.name == 'Agenda') next('/login') // Go to index
//     if (to.name == 'Exercicios') next('/login') // Go to index
//     if (to.name == 'Treino') next('/login') // Go to index
//     if (to.name == 'Treinos') next('/login') // Go to index
//     if (to.name == 'IniciarTreino') next('/login') // Go to index
//     if (to.name == 'CriarTreino') next('/login') // Go to index
//     if (to.name == 'Treinadores') next('/login') // Go to index
//     if (to.name == 'Treinador_Perfil') next('/login') // Go to index
//     if (to.name == 'Treinador_Exercicios') next('/login') // Go to index
//     if (to.name == 'Treinos_Treinador') next('/login') // Go to index
//     if (to.name == 'Treinador_Treino') next('/login') // Go to index
//     if (to.name == 'Treinador_Alunos') next('/login') // Go to index
//     if (to.name == 'Administrador_Alunos') next('/login') // Go to index
//     if (to.name == 'Administrador_Treinadores') next('/login') // Go to index
//     if (to.name == 'Administrador_Treinos') next('/login') // Go to index
//     if (to.name == 'Administrador_Exercicios') next('/login') // Go to index
//     if (to.name == 'Administrador_Administradores') next('/login') // Go to index
//     if (to.name == 'Administrador_Perfil') next('/login') // Go to index
//     else next() // Not logged, it's free
//   }
  
//   //const hasAuth = AuthService.getUsertype();

//   /*const isAuthenticated = AuthService.getToken();

//   if (isAuthenticated == '{}') {
//     if (to.name == 'Account') next('/login') // Go to index
//     if (to.name == 'Agenda') next('/login') // Go to index
//     if (to.name == 'Medicines') next('/login') // Go to index
//     if (to.name == 'Family') next('/login') // Go to index
//     else next() // Not logged, it's free
//   }

//   const hasAuth = AuthService.authorization(isAuthenticated);

//   // Login & Register flow
//   if (to.name == 'Login' && hasAuth) next('/agenda') // Go to index
//   if (to.name == 'Home' && hasAuth) next('/agenda') // Go to index
//   if (to.name == 'About' && hasAuth) next('/agenda') // Go to index
//   //else if (to.name !== 'Login' && !hasAuth) next({ name: 'Login' }) // Go to login
//   else next()*/
// })


export default router