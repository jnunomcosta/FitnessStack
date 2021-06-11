<template>
  <div class="profile">
    <NavBar />
    <SideBar />
    
    <h1 class="text-center display-2" style="color: #f95738; padding-top: 90px">
      {{ treino.nome }}
    </h1>
    <v-row>
      <v-col cols="12" md="1">
        
      </v-col>
      <v-col cols="12" md="4">
        <v-card>
          <div class="mx-auto text-center">
            <v-avatar class="mt-4" size="70">
              <v-img
                src="https://randomuser.me/api/portraits/men/93.jpg"
              ></v-img>
            </v-avatar>
          </div>
          <v-divider class="mx-8 mt-6"></v-divider>
          <v-list-item-content class="mx-auto text-center px-4 pt-4 pb-3">
            <h3>{{ treino.descricao }}</h3>
            <h3>Treino de {{ categorias }}</h3>
            <h3>Criado em {{ treino.data }}</h3>
            <h3>Dificuldade: {{ treino.dificuldade }}</h3>
            <h3>Duração: {{ treino.duracao }}</h3>
            <h3>Código: {{ $route.params.codigo }}</h3>
          </v-list-item-content>
        </v-card>
        <v-card v-scroll.self="onScroll" style="margin-right: 90px max-width:700px" class="mt-4 pt-2 overflow-y-auto" max-height="400" >
           <h2 class="text-center" style="color: #f95738">Avaliações</h2>
          <v-divider class="mx-8 mt-6"></v-divider>
          <div
            class="text-center"
            v-for="item in treino.avaliacoes"
            :key="item.username"
          >
            <v-row>
              <v-col cols="12" md="6">
                <p>{{ item.username }}</p>
              </v-col>
              <v-col cols="12" md="6">
                <p>
                  <v-rating
                    readonly
                    v-model="item.avaliacao"
                    background-color="black"
                    color="#f95738"
                  ></v-rating>
                </p>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12" md="12">
                <p>{{ item.comentario }}</p>
              </v-col>
            </v-row>

            <v-divider class="mx-8 mt-6"></v-divider>
          </div>
        </v-card>
      </v-col>
      <v-col cols="12" md="1"> </v-col>
      <v-col cols="12" md="5">
        <v-card v-scroll.self="onScroll" style="color: #f95738" class="pt-4 overflow-y-auto" max-height="800">  

          <h2 class="text-center">Exercícios</h2>
          <v-divider class="mx-8 mt-6"></v-divider>

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
                <tr
                  class="text-center"
                  v-for="item in treino.exercicios"
                  :key="item.nome"
                >
                  <td>{{ item.nome }}</td>
                  <td>{{ item.series }}</td>
                  <td>{{ item.repeticoes }}</td>
                  <td>{{ item.descanso }} + minutos</td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </v-card>

        <div class="text-center my-16">
          <v-btn width=180 v-on:click="iniciarTreino()" color="#f95738" dark
            >Iniciar treino</v-btn
          >
          <v-container class="text-center">
            <v-dialog v-model="dialog1" persistent max-width="380px">
              <template v-slot:activator="{ on, attrs }">
                <v-btn width=180 color="#f95738" dark v-bind="attrs" v-on="on"
                  >Avaliar treino</v-btn
                >
              </template>
              <v-card>
                <v-card-title>
                  <span class="headline text-center"
                    >Dê-nos o seu feedback acerca deste treino!</span
                  >
                </v-card-title>
                <v-card-text>
                  <div class="text-center mt-12">
                    <v-rating
                      v-model="rating"
                      color="#f95738"
                      background-color="grey darken-1"
                      empty-icon="$ratingFull"
                      half-increments
                      hover
                      large
                    ></v-rating>
                    <v-text-field
                      color="#f95738"
                      v-model="comentario"
                      label="Deixe-nos o seu comentário."
                      required
                    >
                    </v-text-field>
                  </div>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="#f95738" text @click="dialog1 = false">Sair</v-btn>
                  <v-btn color="#f95738" text @click="dialog1 = false" v-on:click="submit()">
                    Confirmar
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-container>
        </div>
      </v-col>
    </v-row>
  </div>
</template>
<script>
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_User.vue";
import axios from "axios";

export default {
  name: "Treino",
  components: {
    NavBar,
    SideBar,
  },
  data() {
    return {
      scrollInvoked: 0,
      dialog1: false,
      rating: "",
      comentario: "",
      avaliacoes: [],
      treino: {
        nome: "",
        duracao: "",
        categorias: [{ categoria: "" }],
        dificuldade: "",
        //treinador: "",
        data: "",
        exercicios: [{ nome: "", tipo:"", series: 0, repeticoes: 0, descanso: 0 }],
        avaliacoes: [],
      },
    };
  },
  methods: {
    iniciarTreino: function () {
      //this.$router.push("/iniciarTreino/" + this.$route.params.codigo);
      this.$router.push({ name: 'IniciarTreino', path: "iniciarTreino/" + this.$route.params.codigo})
    },
    onScroll () {
      this.scrollInvoked++
    },
    submit(){
      //var cod_treino = this.$route.params.codigo;
      console.log(this.rating);
      console.log(this.comentario);
      var post_body = {
        username: localStorage.getItem("username"),
        cod_treino: this.$route.params.codigo,
        avaliacao: this.rating,
        comentario: this.comentario,
      };
      this.treino.avaliacoes.push({
        username: localStorage.getItem("username"),
        avaliacao: this.rating,
        comentario: this.comentario,
      });
      axios
        .post(process.env.VUE_APP_BASELINK+"/api/treinos/avaliacao",post_body,{headers: {'token': localStorage.getItem("token")}})
        .then(response => {
          console.log(response);
        });
    }  
  },
  mounted() {
    axios
      .get(
        process.env.VUE_APP_BASELINK+"/api/treinos/getTreino?codigo=" +
          this.$route.params.codigo,{headers: {'token': localStorage.getItem("token")}}
      )
      .then((response) => {
        this.treino = response.data;
        console.log("heijsfidjs"+JSON.stringify(this.treino))
      })
      .finally(() => (this.loading = false));
  },
  computed: {
    categorias: function () {
      var s = "";
      for (var i = 0; i < this.treino.categorias.length - 1; i++) {
        s += this.treino.categorias[i] + ", ";
      }
      s += this.treino.categorias[i];
      return s;
    },
  },
};
</script>