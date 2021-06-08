<template>
  <v-app>
    <v-main>
    <router-view />
    </v-main>
    <div class="ma-0 pa-0" v-if="$route.path == '/' || $route.path == '/about' || $route.path == '/login' || $route.path == '/administrador/login'">
      <Footer />
    </div>
  </v-app>
</template>

<script>
import Footer from "@/components/Footer.vue";

export default {
  name: "App",
  components: {
    Footer
  },
  data: () => ({
    //
  }),
};
</script>

<style>
#nav {
  padding: 30px;
}
#nav a {
  font-weight: bold;
  color: #2c3e50;
}
#nav a.router-link-exact-active {
  color: #2196F3;
}
</style>
