<template>
  <div class="profile">
    <NavBar />
    <h1 class="text-center display-2" style="color: #f95738; margin-top: 90px">
      {{ treino.nome }}
    </h1>
    <v-row class="mt-4">
      <v-col cols="12" md="1">
        <SideBar />
      </v-col>
      <v-col cols="12" md="5">
        <v-card>
          <div class="mx-auto text-center">
            <v-avatar class="mt-4" size="70">
              <v-img
                src="https://randomuser.me/api/portraits/men/93.jpg"
              ></v-img>
            </v-avatar>
          </div>
          <v-divider class="mx-8 mt-6"></v-divider>
          <v-list-item-content
            class="mx-auto text-center px-4 pt-4 pb-3 font-weight-bold"
          >
            <h3 class="pa-4 grey lighten-2 text-no-wrap rounded-pill"> Descrição: {{ treino.descricao }}</h3>
        
            <h3 class="pa-4 grey lighten-2 text-no-wrap rounded-pill">Categoria: <p v-for="item in treino.categorias" :key="item"> {{ item }} </p> </h3>
              
            <h3 class="pa-4 grey lighten-2 text-no-wrap rounded-pill"> Código: {{ $route.params.codigo }}</h3>
            <h3 class="pa-4 grey lighten-2 text-no-wrap rounded-pill">  Data de criação: {{ treino.data }} </h3>
            <h3 class="pa-4 grey lighten-2 text-no-wrap rounded-pill"> Dificuldade: {{ treino.dificuldade }}</h3>
            <h3 class="pa-4 grey lighten-2 text-no-wrap rounded-pill"> Duração: {{ treino.duracao }} </h3>
          </v-list-item-content>
        </v-card>
      </v-col>
      <v-col cols="12" md="1"> </v-col>
      <v-col cols="12" md="5">
        <v-card style="color: #f95738; margin-right: 90px">
          <h2 class="text-center">Exercícios</h2>

          <v-simple-table height="300px">
            <template v-slot:default>
              <thead>
                <tr>
                  <th class="text-center">Nome</th>
                  <th class="text-center">Séries</th>
                  <th class="text-center">Repetições</th>
                  <th class="text-center">Descanso</th>
                </tr>
              </thead>
              <tbody>
                <tr class="text-center" v-for="item in treino.exercicios" :key="item.nome">
                  <td>{{ item.nome }}</td>
                  <td>{{ item.series }}</td>
                  <td>{{ item.repeticoes }}</td>
                  <td>{{ item.descanso }} + minutos</td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </v-card>
      </v-col>
    </v-row>

    <div class="text-center my-16">
      <v-btn v-on:click="iniciarTreino()" color="#f95738" dark>Iniciar treino</v-btn>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_User.vue";
import axios from 'axios';

export default {
  name: "Treino",
  components: {
    NavBar,
    SideBar,
  },
  data() {
    return {
      exercicios: [
        { nome: "exercicio", series: 3, repeticoes: 10, descanso: 5 },
      ],
      treino: 
        {
          nome: "",
          duracao: "",
          categorias: [
            { categoria: ""}
          ],
          dificuldade: "",
          //treinador: "",
          data: "",
          exercicios: [
            { nome: "", series: 0, repeticoes: 0, descanso: 0},
          ]
        },
      
    };

  },
  methods: {
      iniciarTreino: function () {
      this.$router.push("/iniciarTreino/" + this.$route.params.codigo);
    },
  },
  mounted () {
    axios
      .get('http://localhost:4576/api/treinos/getTreino?codigo=' + this.$route.params.codigo)
      .then(response => {
        this.treino = response.data 
      })
      .finally(() => this.loading = false)
  }
};
</script>