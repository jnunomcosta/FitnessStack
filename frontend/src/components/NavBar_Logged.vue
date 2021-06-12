<template>
  <div class="navbar">
    <v-app-bar color="#000314" elevate-on-scroll dark fixed>
      <div class="d-flex align-center">
        <router-link to="/">
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
        <v-btn tile v-on:click="logout" text small class="mr-1 py-8">
          Logout
          <v-icon class="ml-2">mdi-logout </v-icon>
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
          <v-list-item>
            <v-btn v-on:click="logout" tile text>
              <v-icon dense color="black" style="margin-right: 5px"
                >mdi-logout</v-icon
              >
              LOGOUT
            </v-btn>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
  </div>
</template>


<script>
export default {
  data() {
    return {};
  },
  methods: {
    menuItems() {
      return this.menu;
    },
    logout() {
      console.log("?");
      localStorage.clear();
      this.$router.push("/login");
    },
  },
};
</script>
