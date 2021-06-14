<template>
  <div>
    <NavBar />
    <div class="about text-center">
      <h1 style="padding-top: 150px; font-size: 60px" class="white--text mb-8">
        Sobre o Fitness Stack
      </h1>
      <div
        style="
          padding-left: 300px;
          padding-right: 300px;
          padding-bottom: 100px;
          font-size: 20px;
        "
        class="text-center"
      >
          O Fitness Stack é uma plataforma que incentiva a prática de exercício
          físico, disponibilizando aos seus utilizadores a capacidade de
          planear, criar e partilhar os seus treinos de uma forma simples, fácil
          e rápida.
      </div>
    </div>
      <div class="details">
        <v-row class="pt-4 py-16">
          <v-col class="pl-16" cols="12" md="2"> </v-col>
          <v-col class="pl-16 pt-8" cols="12" md="5">
            <v-list style="text-align: left; background-color: transparent">
              <v-list-item v-for="(item, i) in array" :key="i">
                <v-list-item-title style="font-size: 20px">
                  <v-icon x-large color="#7189FF"
                    >mdi-checkbox-marked-circle-outline</v-icon
                  >
                  {{ item }}</v-list-item-title
                >
                <v-divider> </v-divider>
              </v-list-item>
            </v-list>
          </v-col>
          <v-col class="text-center mt-12" cols="12" md="5">
            <div class="d-flex align-center">
              <v-img
                alt="Fitness Stack Logo"
                class="shrink mr-2 ml-8"
                contain
                src="@/assets/logo2.png"
                transition="scale-transition"
                width="400"
              />
            </div>
          </v-col>
        </v-row>
      </div>
    <div class="text-center" style="background-color:#7189FF">
      <v-row >
        <v-col cols="12" md="2"> </v-col>
        <v-col
          v-for="(item, i) in info"
          :key="i"
          class="text-center mt-8"
          cols="12"
          md="2"
        >
          <v-card style="text-align: center" class="mb-6">
            <div class="mx-auto text-center">
              <v-avatar class="mt-4" size="150">
                <v-img :src="item.foto"></v-img>
              </v-avatar>
            </div>
            <v-card-title class="justify-center">
              {{ item.nome }}
            </v-card-title>
            <v-card-subtitle> {{ item.numero }} </v-card-subtitle>
          </v-card>
        </v-col>
        <v-col cols="12" md="2"> </v-col>
      </v-row>
      <v-btn
        :href="'https://github.com/jnuno420/FitnessStack'"
        icon
        class="ma-2 mb-12"
      >
        <v-icon x-large color="black"> mdi-github </v-icon>
      </v-btn>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";

export default {
  name: "About",
  components: {
    NavBar,
  },
  created() {
    document.title = "Sobre o Fitness Stack";
  },

  data() {
    return {
      array: [
        "Cria os teus próprios treinos",
        "Acede a exercícios especializados",
        "Sê acompanhado por um treinador",
        "Agenda os treus treinos",
        "Acompanha o teu progresso",
        "Guarda os teus treinos favoritos",
      ],
      info: [
        {
          nome: "Alexandra Reigada",
          numero: "A84584",
          foto: require("@/assets/xana.jpg"),
        },{
          nome: "Ana Margarida Ferreira",
          numero: "PG44412",
          foto: require("@/assets/margarida.jpg"),
        },
        
        {
          nome: "João Pedro Parente",
          numero: "A84197",
          foto: require("@/assets/84197.png"),
        },
        {
          nome: "José Nuno Costa",
          numero: "A84829",
          foto: require("@/assets/nuno.png"),
        },  
      ],
    };
  },
};
</script>


<style>
.about {
  background-color: #f95738;
}
.details {
  background-color: #d3d3d2;
}
</style>