<template>
  <div class="criarTreino">
    <NavBar />
    <h1 style="padding: 100px; text-align: center">Criar Treino page!</h1>
    <Footer />
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_User.vue";
import Footer from "@/components/Footer.vue";

export default {
  name: "Criar_Treino",
  components: {
    NavBar,
    Footer,
  },
  created() {
    document.title = "Fitness Stack";
  },
};
</script>
