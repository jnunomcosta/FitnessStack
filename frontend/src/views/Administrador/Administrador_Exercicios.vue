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
              @click:row="verExercicio"
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
        v-on:click="apagarExercicio()"
      >
        <v-icon>mdi-delete</v-icon>
      </v-btn>
     
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
    verExercicio(){

    }
    //verExercicio: function (value) {
      //console.log("ROW VALUES:", value);
      //this.$router.push("/treinos/" + value.codigo);
    //},
  },
};
</script>

<style>
.procurarExercicio {
  background-color: #d3d3d2;
}
</style>