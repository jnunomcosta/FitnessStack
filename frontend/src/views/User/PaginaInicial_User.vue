<template>
  <div class="home">
    <NavBar />
    <div>
      <h1 style="padding: 100px; text-align: center">Bem-vindo ao Fitness Stack!</h1>
      <v-container
      ><div style="padding: 100px" class="text-center">
        <v-row align="center" justify="space-around">
            <v-btn
              x-large
              color="#f95738"
              dark
            >
              Criar treino
            </v-btn>
                  <v-btn
                    x-large
                    color="#f95738"
                    dark
                  >
                    Procurar treino
                  </v-btn>
        </v-row>
      </div>
      </v-container>
    </div>
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