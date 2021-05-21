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
              item-key="codigo"
              :search="search"
              >
              
            </v-data-table>
            
          </v-card>
        </v-container>
        
      </v-col>
      <v-dialog v-model="dialog" persistent max-width="600px">
    
    
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
      </v-dialog>
     
    </v-row>
    
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_Administrador.vue";

export default {
  name: "ProcurarAluno",
  components: {
    NavBar,
    SideBar,
  },
  created() {
    document.title = "Alunos";
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
        {
          nome: "Joao",
          username: "joao10",
          email: "joao@gmail.com"
        }
      ],
    };
  },
  methods: {
    
  },
};
</script>

<style>
.procurarAluno {
  background-color: #d3d3d2;
}
</style>