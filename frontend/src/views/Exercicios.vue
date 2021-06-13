<template>
  <div class="procurarExercicio">
    <NavBar />
    <v-row>
      <v-col v-if="getUsertype() == 1" cols="12" md="1">
        <SideBarTreinador />
      </v-col>
      <v-col v-else cols="12" md="1">
        <SideBar />
      </v-col>
      <v-col cols="12" md="10" style="padding-top: 120px; padding-bottom: 100px">
        <h1 style="text-align: center; font-size: 40px">
          Procure um exercício
        </h1>
        <v-container class="pa-0 ma-0">
          <Exercicio/>
        </v-container>
      </v-col>
    </v-row>
    <div v-if="getUsertype() == 1">
    <CriarExercicio />
      </div>
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_User.vue";
import SideBarTreinador from "@/components/SideBar_Treinador.vue";
import Exercicio from "@/components/Exercicio.vue";
import CriarExercicio from "@/components/CriarExercicio.vue";


export default {
  name: "ProcurarExercicio",
  components: {
    NavBar,
    SideBar,
    SideBarTreinador,
    Exercicio,
    CriarExercicio
  },
  created() {
    document.title = "Exercícios";
  },
  methods: {
    getUsertype() {
      return localStorage.getItem("usertype");
    },
  }
};
</script>

<style>
.procurarExercicio {
  background-color: #f4f5f5;
}
</style>