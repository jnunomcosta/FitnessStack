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

        <v-alert
          border="left"
          v-if="successDelete"
          text
          dismissible
          elevation="2"
          type="success"
        >
          Treinador(es) eliminado(s) com sucesso
        </v-alert>

        <v-alert
          border="left"
          v-if="registerServerError"
          text
          dismissible
          elevation="2"
          type="error"
        >
          Erro do servidor
        </v-alert>

        <v-container>
          <v-row align="center" justify="space-around">
            <v-col cols="12" md="8">
              <v-toolbar dense rounded class="mx-6">
              <v-text-field
                v-model="search"
                hide-details
                prepend-icon="mdi-magnify"
                label="Procurar exercício"
                single-line
                color="#f95738"
              ></v-text-field>
              </v-toolbar>
            </v-col>
          </v-row>
        </v-container>
        <v-container align="center" justify="space-around">
          <v-card>
            <v-data-table         
              v-model="selected"
              :headers="columns"
              :items="rows"
              :single-select="singleSelect"
              show-select
              item-key="nome"
              :search="search"
              >
            </v-data-table>
            
          </v-card>
        </v-container>
        
      </v-col>

      <v-btn
        fab
        dark
        large
        color="#f95738"
        fixed
        right
        bottom
        v-bind="attrs"
        v-on:click="apagarTreinador()"
      >
        <v-icon>mdi-delete</v-icon>
      </v-btn>

      <v-dialog v-model="dialogDelete" persistent max-width="300">
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            fab
            dark
            large
            color="#f95738"
            fixed
            right
            bottom
            v-bind="attrs"
            v-on="on"
          >
            <v-icon>mdi-delete</v-icon>
          </v-btn>
        </template>
        <v-card>
          <v-card-title class="error white--text">
            Apagar Exercício(s)
          </v-card-title>
          <v-card-text class="mt-4"
            >Deseja apagar o(s) exercício(s)? Esta ação é
            irreversível!</v-card-text
          >
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="error" text @click="dialogDelete = false">
              Cancelar
            </v-btn>
            <v-btn color="error" dark @click="confirmar_apagar()">
              Confirmar
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
     
    </v-row>
    
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_Administrador.vue";
import axios from 'axios';

export default {
  name: "ProcurarExercicio",
  components: {
    NavBar,
    SideBar,
  },
  created() {
    document.title = "Exercicios";
  },
  data() {
    return {
      successDelete: false,
      registerServerError: false,
      singleSelect: false,
      selected: [],
      search: "",
      columns: [
        { text: "Nome do Exercicio", value: "nome" },
        { text: "Duração", value: "duracao" },
        { text: "Material Necessário", value: "material" },
        { text: "Descrição", value: "descricao" }
      ],
      rows: [],
    };
  },
  mounted() {
    axios
      .get(process.env.VUE_APP_BASELINK+'/api/exercicio/listar',{headers: {'token': localStorage.getItem("token")}})
      .then(response => {
        this.rows = response.data 
      })
  },
  methods: {
    apagarExercicio(){
      let deletbody = [];
      this.selected.forEach(element => {
        deletbody.push(element.id);
      });
      axios 
        .delete(process.env.VUE_APP_BASELINK+'/api/exercicio/deleteExercicios',{headers:{token: localStorage.getItem("token")},data:deletbody})
        .then(response => {
          //ERRO 500 e porque o exercicio esta num treino!
          if(response.status == 200){
            this.$router.push("/administrador/exercicios/");
          }
        }) 
    },
  },
};
</script>

<style>
.procurarExercicio {
  background-color: #d3d3d2;
}
</style>