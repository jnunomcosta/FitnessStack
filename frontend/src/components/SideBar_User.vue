<template>
    <div>
    <!-- Large screens -->
    <v-navigation-drawer
      permanent
      fixed
      v-model="drawer"
      :mini-variant.sync="mini"
      class="mt-16 mb-4 hidden-sm-and-down"
    >
      <v-list-item class="px-2 py-2">
        <v-list-item-avatar>
          <v-img :src="linkfoto() + foto_perfil"></v-img>
          <!-- <v-img :src="'data:image/jpeg;base64,'+variavelRecebidaDaAPI"></v-img> -->
        </v-list-item-avatar>
        <div id='imagem-sidebar'>
          {{n_sei_isto}}
        </div>
        <v-list-item-content class="ma-0 pa-0" >
          <v-list-item-title>{{ nome }}</v-list-item-title>
          <v-list-item-subtitle>{{ username }}</v-list-item-subtitle>
        </v-list-item-content>

        <v-btn icon @click.stop="mini = !mini">
          <v-icon>mdi-chevron-left</v-icon>
        </v-btn>
      </v-list-item>

      <v-divider></v-divider>

      <v-list dense hover="#f95738">
        <v-list-item v-for="item in items" :key="item.title" :href="item.link">
          <v-list-item-icon>
            <v-icon
              v-if="item.link == $router.currentRoute.path"
              color="#f95738"
              >{{ item.icon }}</v-icon
            >
            <v-icon v-else>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title
              v-if="item.link == $router.currentRoute.path"
              style="color: #f95738"
              >{{ item.title }}</v-list-item-title
            >
            <v-list-item-title v-else>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <!-- Small screens -->
    <v-card tile centered class="mt-14 hidden-lg-and-up">
      <v-tabs
        centered
        background-color="#000314"
        fixed-tabs
        dark
        icons-and-text
      >
        <v-tab v-for="item in items" :key="item.title" :to="item.link">
          {{ item.title }}
          <v-icon>{{item.icon}}</v-icon>
        </v-tab>
      </v-tabs>
    </v-card>
  </div>
</template>

<script>

import axios from 'axios';

export default {
  props: ['n_sei_isto'],
  data() {
    return {
      foto_perfil: "",
      nome: "",
      username: "",
      drawer: true,
      items: [
        {title: "Perfil", icon: "mdi-account", link: "/perfil"},
        { title: "Agenda", icon: "mdi-calendar", link: "/agenda" },
        { title: "Exercícios", icon: "mdi-dumbbell", link: "/exercicios" },
        { title: "Treinos", icon: "mdi-timer", link: "/treinos" },
        {
          title: "Treinadores",
          icon: "mdi-weight-lifter",
          link: "/treinadores",
        },
      ],
      mini: true,
    };
  },
  mounted () {
    axios
      .get(process.env.VUE_APP_BASELINK+"/api/user/getSideBarUserInfo",{headers: {'token': localStorage.getItem("token")}})      
      .then(response => {
        this.foto_perfil = response.data.foto_perfil;
        this.nome = response.data.nome;
        this.username = response.data.username;
      })
      .finally(() => this.loading = false)
  },
  methods:{
    linkfoto(){
      return  process.env.VUE_APP_BASELINK
    },
  }
};
</script>