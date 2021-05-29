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
          <!-- <v-img src="https://randomuser.me/api/portraits/men/85.jpg"></v-img> -->
          <v-img :src="'data:image/jpeg;base64,'+variavelRecebidaDaAPI"></v-img>
        </v-list-item-avatar>
        <div id='imagem-sidebar'>
          {{username}}
        </div>
        <v-list-item-content class="ma-0 pa-0" >
          <v-list-item-title>John Leider</v-list-item-title>
          <v-list-item-subtitle>username</v-list-item-subtitle>
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
  props: ['username'],
  data() {
    return {
      variavelRecebidaDaAPI: "",
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
      .get('http://localhost:4576/rest/utilizadores/getImagem?username='+localStorage.getItem('username'))
      .then(response => {
        this.variavelRecebidaDaAPI = response.data.imagem
      })
      .finally(() => this.loading = false)
  }
};
</script>