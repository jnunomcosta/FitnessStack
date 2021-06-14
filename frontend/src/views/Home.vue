<template>
  <div class="home">
    <NavBar />

    <v-carousel :show-arrows="false" cycle delimiter-icon="mdi-minus">
      <v-carousel-item v-for="(item, i) in items" :key="i" :src="item.src">
        <v-row class="fill-height" align="center" justify="center">
          <v-list two-line style="background-color: transparent">
            <h1 class="titulo">Bem-vindo ao Fitness Stack</h1>
            <p class="text-center"><i>A tua plataforma de fitness</i></p>
          </v-list>
        </v-row>
      </v-carousel-item>
    </v-carousel>


    <div class="py-5" style="background-color: #f95738">
        <v-row class="mx-16 py-10">
        <v-col class="text-center" cols="12" md="3">
          <div class="my-6 mx-16" style="color: #f4f5f5">
            <v-icon dark style="font-size: 5rem" class="mb-10"> 
              mdi-calendar-text
            </v-icon>
            <p>Cria os teus próprios treinos</p>
          </div>
        </v-col>
        <v-col class="text-center" cols="12" md="3">
          <div class="my-6 mx-16" style="color: #f4f5f5">
            <v-icon dark style="font-size: 5rem" class="mb-10"> 
              mdi-dumbbell
            </v-icon>
            <p>Acede a treinos especializados</p>
          </div>
        </v-col>
        <v-col class="text-center" cols="12" md="3">
          <div class="my-6 mx-16" style="color: #f4f5f5">
            <v-icon dark style="font-size: 5rem" class="mb-10"> 
              mdi-weight-lifter
            </v-icon>
            <p>Sê acompanhado por um treinador certificado</p>
          </div>
        </v-col>
        <v-col class="text-center" cols="12" md="3">
          <div class="my-6 mx-16" style="color: #f4f5f5">
            <v-icon dark style="font-size: 5rem" class="mb-10"> 
              mdi-chart-line
            </v-icon>
            <p>Monitoriza os teu progresso</p>
          </div>
        </v-col>
      </v-row>
    </div>
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
  data: () => ({
    items: [
      {
        src: require("@/assets/menina.png"),
      },
      {
        src: require("@/assets/menino.png"),
      },
      {
        src: require("@/assets/menino2.png"),
      },
    ],
  }),
};
</script>


<style>
.home {
  background-color: #d3d3d2;
}
</style>