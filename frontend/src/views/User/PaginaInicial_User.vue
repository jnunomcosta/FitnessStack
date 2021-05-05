<template>
  <div class="home">
    <NavBar />
    <h1 style="padding: 100px; text-align: center">Bem-vindo ao Fitness Stack!</h1>
    <Footer />
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_User.vue";
import Footer from "@/components/Footer.vue";

export default {
  name: "Home",
  components: {
    NavBar,
    Footer,
  },
  created() {
    document.title = "Fitness Stack";
  },
};
</script>