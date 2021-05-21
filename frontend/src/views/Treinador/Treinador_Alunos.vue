<template>
  <div class="treinadores">
    <NavBar />
    <v-row style="margin-top: 90px">
      <v-col cols="12" md="1">
        <SideBar />
      </v-col>
      <v-col cols="12" md="10" >
       <v-dialog
      v-model="dialog"
      fullscreen
      hide-overlay
      transition="dialog-bottom-transition"
    >
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        class="mx-6"
        v-bind="attrs"
        v-on="on"
        color="#f95738"
        dark
      >
        As minhas solicitações      </v-btn>
    </template>
    <template v-slot:default="dialog">
      <v-card class="pa-0 ma-0">

        <v-toolbar color="#f95738" dark
          ><h3>Solicitações</h3>
          <v-spacer></v-spacer>
          <v-btn icon @click="dialog.value = false"
            ><v-icon>mdi-close</v-icon></v-btn
          >
        </v-toolbar>
      
          <v-row align="center" justify="space-around">
            <v-col cols="12" md="12">
                <v-text-field
                  hide-details
                  prepend-icon="mdi-magnify"
                  label="Procurar solicitação"
                  single-line
                  color="#f95738"
                ></v-text-field>
                <v-btn icon>
                  <v-icon>mdi-filter</v-icon>
                </v-btn>
            </v-col>
          </v-row>
          <AlunoSolicitacao
        /></v-card>

    </template>
  </v-dialog>





        <v-container class="pa-0 ma-0">
          <v-row align="center" justify="space-around">
            <v-col cols="12" md="12">
              <v-toolbar dense rounded class="mx-6" style="margin-top: 40px">
                <v-text-field
                  hide-details
                  prepend-icon="mdi-magnify"
                  label="Procurar aluno"
                  single-line
                  color="#f95738"
                ></v-text-field>
                <v-btn icon>
                  <v-icon>mdi-filter</v-icon>
                </v-btn>
              </v-toolbar>
            </v-col>
          </v-row>

          <Aluno
        /></v-container>
      </v-col>
    </v-row>
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_Treinador.vue";
import Aluno from "@/components/Aluno.vue";
import AlunoSolicitacao from "@/components/AlunoSolicitacao.vue";

export default {
  name: "Alunos",
  components: {
    NavBar,
    SideBar,
    Aluno,
    AlunoSolicitacao
  },
   data: () => ({
    dialog: false,
    dialog2: false,
    }),
  created() {
    document.title = "Alunos";
  },
};
</script>

<style>
.alunos {
  background-color: #f4f5f5;
}
</style>