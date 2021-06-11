<template>
  <div class="procurarTreino">
    <NavBar />
    <v-row>
      <v-col cols="12" md="1">
        <SideBar />
      </v-col>
      <v-col cols="12" md="10">
        <h1 style="padding-top: 100px; text-align: center; font-size: 40px">
          Procure um treino
        </h1>
        <v-container>
          <v-row align="center" justify="space-around">
            <v-col cols="12" md="8">
              <v-toolbar dense rounded class="mx-6">
              <v-text-field
                v-model="search"
                hide-details
                prepend-icon="mdi-magnify"
                label="Procurar treino"
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
              item-key="codigo"
              :search="search"
              @click:row="verTreino"
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
        v-on:click="apagarTreino()"
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
  name: "ProcurarTreino",
  components: {
    NavBar,
    SideBar,
  },
  created() {
    document.title = "Treinos";
  },
  data() {
    return {
      singleSelect: false,
      dialog: false,
      selected: [],
      search: "",
      columns: [
        { text: "Nome do Treino", value: "nome" },
        { text: "Duração", value: "duracao" },
        { text: "Categoria", value: "categoria" },
        { text: "Dificuldade", value: "dificuldade" },
        { text: "Treinador", value: "treinador" },
        { text: "Publicado em", value: "data" },
        { text: "Avaliação", value: "avaliacao" },
        { text: "Código", value: "codigo" }
      ],
      rows: [
      ],
    };
  },
  mounted() {
    axios
      .get(process.env.VUE_APP_BASELINK+'/api/treinos/listar',{headers: {'token': localStorage.getItem("token")}})
      .then(response => {
        this.rows = response.data 
      })
  },
  methods: {
    apagarTreino(){
      let deletbody = [];
      this.selected.forEach(element => {
        deletbody.push(element.codigo);
      });
      axios 
        .delete(process.env.VUE_APP_BASELINK+'/api/treinos/deleteTreino',{headers:{token: localStorage.getItem("token")},data:deletbody})
        .then(response => {
          if(response.status == 200){
            this.$router.push("/administrador/treinos/");
          }
        }) 
    },
    verTreino(){

    },
  },
};
</script>

<style>
.procurarTreino {
  background-color: #d3d3d2;
}
</style>