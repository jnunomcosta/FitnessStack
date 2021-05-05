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
          <v-icon dense style="margin-right: 5px"
            >mdi-{{ icon_account[0].icon }}</v-icon
            >
             <v-menu offset-y>
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  color="#000314"
                  dark
                  v-bind="attrs"
                  v-on="on"
                >
                 Perfil
                </v-btn>
              </template>
              <v-list>
                <v-list-item
                  v-for="item2 in items"
                  :key="item2.title"
                  :to="item2.link"
                >
                  <v-list-item-title>{{ item2.title }}</v-list-item-title>
                </v-list-item>
              </v-list>
            </v-menu>
          <v-btn
            v-for="item in menu"
            :key="item.icon"
            :to="item.link"
            tile
            text
            small
            class="mr-1 py-8"
            >
            <v-icon v-if="item.icon == 'account'" dense style="margin-right: 5px"
              >mdi-{{ item.icon }}</v-icon
            >
            {{ item.title }}
          </v-btn>
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
        </v-list>
      </v-menu>
    </v-app-bar>
  </div>
</template>


<script>
export default {
  data() {
    return {
      icon_account:[ {icon:"account"}],
      menu: [
        //{ icon: "account", title: "Perfil", link: "/perfil" },
        { icon: "information", title: "Agenda", link: "/agenda" },
        { icon: "information", title: "Treinadores", link: "/treinadores" },
        { icon: "information", title: "Sobre", link: "/about" },
      ],
      items: [
        { title: "progresso", link: "/Progresso" },
        { title: "informacoes", link: "/informacoes" },
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
