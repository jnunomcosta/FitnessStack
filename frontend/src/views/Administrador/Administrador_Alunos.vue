<template>
  <div class="procurarAluno">
    <NavBar />
    <v-row>
      <v-col cols="12" md="1">
        <SideBar />
      </v-col>
      <v-col cols="12" md="10">
        <h1 style="padding-top: 100px; text-align: center; font-size: 40px">
          Procure um aluno
        </h1>
        <v-container>
          <v-row align="center" justify="space-around">
            <v-col cols="12" md="8">
              <v-toolbar dense rounded class="mx-6">
              <v-text-field
                v-model="search"
                hide-details
                prepend-icon="mdi-magnify"
                label="Procurar aluno"
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
        v-on:click="apagarUtilizador()"
      >
        <v-icon>mdi-delete</v-icon>
      </v-btn>
     
    </v-row>
    
  </div>
</template>

<script>
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_Administrador.vue";
import axios from "axios";

export default {
  name: "ProcurarAluno",
  components: {
    NavBar,
    SideBar,
  },
  created() {
    document.title = "Alunos";
  },
  mounted(){
    axios
      .get(process.env.VUE_APP_BASELINK+"/api/user/listar",{headers: {'token': localStorage.getItem("token")}})      
      .then(response => {
        this.rows= response.data;
        console.log("adasdsadsadhhhhhhhhhhhh"+response.data)

      })
      .catch(e => console.log("erro" +e))

      console.log("dasjdamsjdsnajdzzzzzzzzzzzz");
  
  },
  data() {
    return {
      singleSelect: false,
      selected: [],
      search: "",
      columns: [
        { text: "Nome ", value: "nome" },
        { text: "Username", value: "username" },
        { text: "Email", value: "email" }
      ],
      rows: [
      ],
    };
  },
  methods: {
    apagarUtilizador(){
      let deletbody = [];
      this.selected.forEach(element => {
        deletbody.push(element.username);
      });
      axios 
        .delete(process.env.VUE_APP_BASELINK + '/api/user/deleteUtilizador',{headers:{token: localStorage.getItem("token")},data:deletbody})
        .then(response => {
          //ERRO 500 -> nao conseguiu eliminar, pode ter feito um comentario ou ter marcacoes ou um treinador associado
          if(response.status == 200){
            this.$router.push("/administrador/alunos/");
          }
        })  
    },
  },
};
</script>

<style>
.procurarAluno {
  background-color: #d3d3d2;
}
</style>