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
              :headers="headers"
              :items="treinos"
              :search="search"
              @click:row="verTreino"
            ></v-data-table>
          </v-card>
        </v-container>
      </v-col>
    </v-row>
     <v-btn fab dark large color="#f95738" fixed right bottom to="/criartreino">
      <v-icon>mdi-plus</v-icon>
     </v-btn>
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_User.vue";
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
      search: "",
      headers: [
        { text: "Nome do Treino", value: "nome" },
        { text: "Duração", value: "duracao" },
        { text: "Categoria", value: "categoria" },
        { text: "Dificuldade", value: "dificuldade" },
        { text: "Treinador", value: "treinador" },
        { text: "Publicado em", value: "data" },
        { text: "Avaliação", value: "avaliacao" },
        { text: "Favoritos", value: "favoritos" },
        { text: "Código", value: "codigo" },
      ],
      treinos: [
        {
          nome: "",
          duracao: "",
          categoria: "",
          dificuldade: "",
          treinador: "",
          data: "",
          avaliacao: "",
          favoritos: "",
          codigo: "",
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
  mounted () {
    axios
      .get('http://localhost:4576/api/treinos/listar')
      .then(response => {
        this.treinos = response.data 
      })
      .finally(() => this.loading = false)
  }
};
</script>

<style>
.procurarTreino {
  background-color: #d3d3d2;
}
</style>