<template>
  <div class="navbar">
    <v-app-bar color="#000314" elevate-on-scroll dark fixed>
      <div class="d-flex align-center">
        <router-link to="/PaginaInicial_User">
          <v-img
            alt="Fitness Stack Logo"
            class="shrink mr-2 ml-8"
            contain
            src="@/assets/fitness_stack_logo.png"
            transition="scale-transition"
            width="180"
          />
        </router-link>
      </div>

      <v-spacer></v-spacer>

      <!-- Large screens -->
      <div class="hidden-sm-and-down mr-4">
        <v-btn
          v-for="item in menu"
          :key="item.icon"
          :to="item.link"
          tile
          text
          small
          class="mr-1 py-8"
        >
          {{ item.title }}
        </v-btn>
        
        <v-menu>
          <template v-slot:activator="{ on, attrs }">
            <v-btn tile text small class="mr-1 py-8" v-bind="attrs" v-on="on">
              <v-icon dense style="margin-right: 5px">mdi-account</v-icon>
              Perfil
              <v-icon dense>mdi-menu-down</v-icon>
            </v-btn>
          </template>
          <v-list>
            <v-list-item v-for="item in items" :key="item.icon" :to="item.link">
              <v-icon dense color="black" style="margin-right: 5px"
                >mdi-{{ item.icon }}</v-icon
              >
              <v-list-item-title>{{
                item.title.toUpperCase()
              }}</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </div>

      <!-- Small screens -->
      <v-menu left bottom>
        <template v-slot:activator="{ on, attrs }">
          <v-btn icon v-bind="attrs" v-on="on" class="hidden-lg-and-up">
            <v-app-bar-nav-icon></v-app-bar-nav-icon>
          </v-btn>
        </template>
        <v-list>
          <v-list-item v-for="item in menu" :key="item.icon" :to="item.link">
            <v-icon dense color="black" style="margin-right: 5px"
              >mdi-{{ item.icon }}</v-icon
            >
            <v-list-item-title>{{
              item.title.toUpperCase()
            }}</v-list-item-title>
          </v-list-item>
          <v-list-item v-for="item in items" :key="item.icon" :to="item.link">
            <v-icon dense color="black" style="margin-right: 5px"
              >mdi-{{ item.icon }}</v-icon
            >
            <v-list-item-title>{{
              item.title.toUpperCase()
            }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
  </div>
</template>


<script>
export default {
  data() {
    return {
      menu: [
        { icon: "calendar", title: "Agenda", link: "/agenda" },
        { icon: "weight-lifter", title: "Treinadores", link: "/treinadores" },
        { icon: "information", title: "Sobre", link: "/about" },
      ],
      items: [
        { icon: "chart-line", title: "Progresso", link: "/progresso" },
        { icon: "clipboard-account", title: "Informações", link: "/informacoes" },
        { icon: "logout", title: "Sair", link: "/" },
      ],
    };
  },
  methods: {
    menuItems() {
      return this.menu;
    },
  },
};
</script>
