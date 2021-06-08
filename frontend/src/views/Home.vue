<template>
  <div class="home">
    <NavBar />
    <h1 style="padding: 100px; text-align: center">Bem-vindo ao Fitness Stack!</h1>
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar.vue";

export default {
  name: "Home",
  components: {
    NavBar,
  },
  created() {
    document.title = "Fitness Stack";
  },
};
</script>


<style>
.home {
  background-color: #d3d3d2;
}
</style>