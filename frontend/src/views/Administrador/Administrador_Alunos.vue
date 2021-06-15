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

        <v-alert
          border="left"
          v-if="successDelete"
          text
          dismissible
          elevation="2"
          type="success"
        >
          Aluno(s) eliminado(s) com sucesso
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

      <v-alert
          border="left"
          v-if="forbiddenError"
          text
          dismissible
          elevation="2"
          type="error"
        >
          Não é permitido apagar este(s) aluno(s)
        </v-alert>

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
            Apagar Aluno(s)
          </v-card-title>
          <v-card-text class="mt-4"
            >Deseja apagar o(s) aluno(s)? Esta ação é
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
      dialogDelete: false,
      successDelete: false,
      registerServerError: false,
      forbiddenError: false,
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
    confirmar_apagar() {
      this.apagarUtilizador();
      this.dialogDelete = false;
    },
    apagarUtilizador(){
      let deletbody = [];
      this.selected.forEach(element => {
        deletbody.push(element.username);
      });
      axios 
        .delete(process.env.VUE_APP_BASELINK + '/api/user/deleteUtilizador',{headers:{token: localStorage.getItem("token")},data:deletbody})
        .then(response => {
         if(response.status == 200){
            this.successDelete = true;
            setTimeout(() => {
              this.successDelete = false;
              this.$router.go();
            }, 5000);
          } 
        })
        .catch((error) => {
          if (error.response != null) {
            if (error.response.status == "500") {
              this.forbiddenError = true;
              setTimeout(() => {
                this.forbiddenError = false;
              }, 5000);
            } else {
              this.registerServerError = true;
              setTimeout(() => {
                this.registerServerError = false;
              }, 5000);
            }
          }
        });
    },
  },
};
</script>

<style>
.procurarAluno {
  background-color: #d3d3d2;
}
</style>