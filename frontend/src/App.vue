<template>
  <v-app>
    <div id="nav" class="text-center">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">Exemplo de Consumo da API</router-link>
    </div>
    <router-view />
  </v-app>
</template>

<script>
export default {
  name: "App",
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
