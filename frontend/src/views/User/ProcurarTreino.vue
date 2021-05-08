<template>
  <div class="procurarTreino">
    <NavBar />
    <h1 style="padding-top: 100px; text-align: center; font-size: 40px">
      Procure o seu treino
    </h1>
    <v-container>
      <v-row align="center" justify="space-around">
        <v-col cols="12" sm="6">
          <v-text-field
            v-model="search"
            hide-details
            prepend-icon="mdi-magnify"
            label="Procurar"
            solo
            color="#f95738"
          ></v-text-field>
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
    <Footer />
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_User.vue";
import Footer from "@/components/Footer.vue";

export default {
  name: "ProcurarTreino",
  components: {
    NavBar,
    Footer,
  },
  created() {
    document.title = "Fitness Stack";
  },
  data () {
    return {
      search: '',
      headers: [
        { text: 'Nome do Treino', value: 'nome' },
        { text: 'Duração', value: 'duracao' },
        { text: 'Categoria', value: 'categoria' },
        { text: 'Dificuldade', value: 'dificuldade' },
        { text: 'Treinador', value: 'treinador' },
        { text: 'Publicado em', value: 'data' },
        { text: 'Avaliação', value: 'avaliacao' },
        { text: 'Favoritos', value: 'favoritos' },
        { text: 'Código', value: 'codigo' }
      ],
      treinos: [
        {
          nome: '10 Min Ab Workout',
          duracao: "10 min",
          categoria: "Abdominais",
          dificuldade: "Iniciante",
          treinador: "Pamela Reif",
          data: "07/05/2021",
          avaliacao: '4.6/5',
          favoritos: 'true',
          codigo: "12345"
        },
        {
          nome: '12 Min Happy Cardio',
          duracao: "12 min",
          categoria: "Cardio",
          dificuldade: "Iniciante",
          treinador: "Pamela Reif",
          data: "05/05/2021",
          avaliacao: '4.2/5',
          favoritos: 'true',
          codigo: '67890'
        }
      ],
    }
  },
  methods: { 
    verTreino: function(value) {
      console.log('ROW VALUES:', value)
      this.$router.push('/treinos/'+value.codigo)
    }
  }
};
</script>

<style>
.procurarTreino {
  background-color: #d3d3d2;
}
</style>