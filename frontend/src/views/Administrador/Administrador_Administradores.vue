<template>
  <div class="procurarAdministrador">
    <NavBar />
    <v-row>
      <v-col cols="12" md="1">
        <SideBar />
      </v-col>
      <v-col cols="12" md="10">
        <h1 style="padding-top: 100px; text-align: center; font-size: 40px">
          Procure um administrador
        </h1>

        <v-alert
          border="left"
          v-if="successDelete"
          text
          dismissible
          elevation="2"
          type="success"
        >
          Administrador(es) eliminado(s) com sucesso
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
          Não é permitido apagar este(s) administrador(es)
        </v-alert>

        <v-container>
          <v-row align="center" justify="space-around">
            <v-col cols="12" md="8">
              <v-toolbar dense rounded class="mx-6">
              <v-text-field
                v-model="search"
                hide-details
                prepend-icon="mdi-magnify"
                label="Procurar administrador"
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
              >
              
            </v-data-table>
            
          </v-card>
        </v-container>
        
      </v-col>
      
    
    </v-row>
    
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_Administrador.vue";
import axios from "axios";
export default {
  name: "ProcurarTreino",
  components: {
    NavBar,
    SideBar,
  },
  created() {
    document.title = "Treinos";
  },

  mounted(){
    axios
      .get(process.env.VUE_APP_BASELINK+"/api/admin/listar",{headers: {'token': localStorage.getItem("token")}})      
      .then(response => {
        this.rows= response.data;

      })
      .catch(e => console.log("erro" +e))  
  },
  data() {
    return {
      singleSelect: false,
      selected: [],
      search: "",
      columns: [
        { text: "Nome", value: "nome" },
        { text: "Username", value: "username" },
        { text: "Email", value: "email" }
      ],
      rows: [

      ],
    };
  },
  methods: {
    
  }

};
</script>

<style>
.procurarAdministrador {
  background-color: #d3d3d2;
}
</style>