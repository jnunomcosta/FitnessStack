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
import NavBar from "@/components/NavBar_User.vue";
import SideBar from "@/components/SideBar_Administrador.vue";

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
        { text: "Favoritos", value: "favoritos" },
        { text: "Código", value: "codigo" }
      ],
      rows: [
        {
          nome: "10 Min Ab Workout",
          duracao: "10 min",
          categoria: "Abdominais",
          dificuldade: "Iniciante",
          treinador: "Pamela Reif",
          data: "07/05/2021",
          avaliacao: "4.6/5",
          favoritos: "true",
          codigo: "12345",
        },
        {
          nome: "12 Min Happy Cardio",
          duracao: "12 min",
          categoria: "Cardio",
          dificuldade: "Iniciante",
          treinador: "Pamela Reif",
          data: "05/05/2021",
          avaliacao: "4.2/5",
          favoritos: "true",
          codigo: "67890",
        },
      ],
    };
  },
  methods: {
    verTreino: function (value) {
      console.log("ROW VALUES:", value);
      this.$router.push("/treinos/" + value.codigo);
    },
  },
};
</script>

<style>
.procurarTreino {
  background-color: #d3d3d2;
}
</style>