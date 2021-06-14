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
          <v-img src="@/assets/favicon.png"></v-img>
        </v-list-item-avatar>
        <v-list-item-content class="ma-0 pa-0" >
          <v-list-item-title class="title">{{nome}}<v-icon dense color="#f95738" class="ml-2">mdi-shield-account</v-icon></v-list-item-title>
          <v-list-item-subtitle> {{username }} </v-list-item-subtitle>
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

import axios from "axios";

  
export default {
  data() {
    return {
      drawer: true,
      nome: "",
      username: "",
      items: [
        { title: "Perfil", icon: "mdi-account", link: "/administrador/Perfil"},
        { title: "Exercícios", icon: "mdi-dumbbell", link: "/administrador/Exercicios" },
        { title: "Treinos", icon: "mdi-timer", link: "/administrador/Treinos" },
        { title: "Alunos", icon: "mdi-account-group", link: "/administrador/Alunos"},
        { title: "Treinadores", icon: "mdi-weight-lifter", link: "/administrador/Treinadores"},
        { title: "Admins", icon: "mdi-shield-account", link: "/administrador/Administradores"}
      ],
      mini: true,
    };
  },
   mounted () {
    axios
      .get(process.env.VUE_APP_BASELINK+"/api/admin/getSideBarAdminInfo/"+localStorage.getItem("username"),{headers: {'token': localStorage.getItem("token")}})      
      .then(response => {
        this.nome = response.data.nome;
        this.username = response.data.username;
      })
      .finally(() => this.loading = false)
  },
  
  
};
</script>