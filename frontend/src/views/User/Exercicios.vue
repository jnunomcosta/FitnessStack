<template>
  <div class="procurarExercicio">
    <NavBar />
    <v-row>
      <v-col cols="12" md="1">
        <SideBar />
      </v-col>
      <v-col cols="12" md="10">
        <h1 style="padding-top: 100px; text-align: center; font-size: 40px">
          Procure um exercício
        </h1>
        <v-container class="pa-0 ma-0">
          <v-row align="center" justify="space-around">
            <v-col cols="12" md="12">
              <v-toolbar dense rounded class="mx-6 mt-6">
                <v-text-field
                  hide-details
                  prepend-icon="mdi-magnify"
                  label="Procurar exercício"
                  single-line
                  color="#f95738"
                ></v-text-field>
                <v-btn icon>
                  <v-icon>mdi-filter</v-icon>
                </v-btn>
              </v-toolbar>
            </v-col>
          </v-row>
          <Exercicio/>
        </v-container>
      </v-col>
    </v-row>
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_User.vue";
import Exercicio from "@/components/Exercicio.vue";

export default {
  name: "ProcurarExercicio",
  components: {
    NavBar,
    SideBar,
    Exercicio
  },
  created() {
    document.title = "Exercícios";
  },
};
</script>

<style>
.procurarExercicio {
  background-color: #f4f5f5;
}
</style>