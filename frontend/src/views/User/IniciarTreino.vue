<template>
  <div class="profile">
    <NavBar />

    <v-row>
      <v-col cols="12" md="1">
        <SideBar />
      </v-col>
      <v-col
        cols="12"
        md="10"
        class="mt-4 pb-16"
        align="center"
        style="color: #f95738; padding-top: 120px"
      >

      <v-card class="py-8" style="color: #f95738">
          <v-card-text>
            <h1 class="text-center" style="color: #f95738; font-size: 40px">
              <b> {{ treino.nome }} </b>
            </h1>
          </v-card-text>
        </v-card>




        <v-carousel
          ref="carousel"
          v-model="slide"
          height="600"
          hide-delimiter-background
          show-arrows-on-hover
          class="mb-8"
        >
          <v-carousel-item v-for="(item, i) in treino.exercicios" :key="i">
            <v-sheet color="grey" height="100%">
              <v-row class="fill-height" align="center" justify="center">
                 <v-col cols="12" md="8">
                  <v-card
                    elevation="17"
                    color="white"
                    class="text-center mt-8"
                    style="text-align: center"
                    height="500"
                  >
                  <v-row  class="ml-6">
                    <v-col cols="12" md="5">
                    
                    <div class="text-center mx-4 ">
                      <v-list-item-content class="black--text mt-8" style="font-size: 20px">
                        <h2>{{ item.nome }}</h2>
                        <h2>{{ n_series }} / {{ item.series }} Séries</h2>

                        <v-row class="text-center mt-6" style="text-align: center">
                          <v-col v-if="!item.tipo" cols="12" md="6">
                            <p
                              class="
                                pa-4
                                grey
                                lighten-2
                                text-no-wrap
                                rounded-pill
                              "
                            >
                              <b>Duração</b>
                            </p>
                            <div class="text-center" style="font-size:40px">
                            <v-progress-circular
                              :rotate="360"
                              :size="150"
                              :width="15"
                              :value="duracao_serie_reverso"
                              color="#f95738"
                            >
                              {{ duracao_serie }} s
                            </v-progress-circular>
                            </div>                     
                            
                          </v-col>
                          <v-col
                            v-if="item.tipo"
                            cols="12"
                            md="6"
                          >
                            <p
                              class="
                                pa-4
                                grey
                                lighten-2
                                text-no-wrap
                                rounded-pill
                              "
                            >
                              <b > {{ item.repeticoes }} reps </b>
                            </p>
                            
                              <div class="text-center">
                                <v-progress-circular
                                  :size="150"
                                  :width="15"
                                  color="#f95738"
                                  indeterminate
                                >
                                </v-progress-circular>
                            </div>
                           

                          </v-col>
                          <v-col cols="12" md="6">
                            <p
                              class="
                                pa-4
                                grey
                                lighten-2
                                text-no-wrap
                                rounded-pill
                              "
                            >
                              <b>Descanso </b>
                            </p>
                            <div class="text-center" style="font-size:40px">
                            <v-progress-circular
                              :rotate="360"
                              :size="150"
                              :width="15"
                              
                              :value=" duracao_descanso_reverso "
                              color="#7189FF"
                            >
                              {{ duracao_descanso }} s
                            </v-progress-circular>
                            </div>
                          </v-col>
                        </v-row>
                        <v-btn
                          color="black"
                          block
                          dark
                          :disabled="!terminar_series"
                          v-if="item.tipo"
                          v-on:click="aumentar_serie(item, i)"
                        >
                          Próxima série
                        </v-btn>
                        <v-btn
                          v-if="!item.tipo"
                          color="black"
                          block
                          :disabled="!terminar_series"
                          v-on:click="countDownTimer_serie1(item, i)"
                          >Iniciar bloco</v-btn
                        >
                      </v-list-item-content>
                    </div>
                    </v-col>
                    <v-col cols="12" md="7">

                    <div class="text-center">
                      <div class="mx-auto text-center">
                        <v-carousel
                        cycle
                          :show-arrows="false"
                          height="420"
                          width="300"
                        >
                          <v-carousel-item
                            v-for="(otim, o) in item.fotos"
                            :key="o"
                            class="pa-8 pe-10"
                          >
                            <template v-if="otim.includes('photo')">
                              <img :src="linkapi() + otim" />
                            </template>
                            <template v-else>
                              <video controls>
                                <source
                                  :src="linkapi() + otim"
                                  type="video/mp4"
                                />
                              </video>
                            </template>
                          </v-carousel-item>
                        </v-carousel>
                      </div>
                    </div>
                    </v-col>
                  </v-row>
                  </v-card>
                </v-col>
                <v-col
                  cols="12"
                  md="3"
                  v-if="i + 1 < Object.keys(treino.exercicios).length"
                >
                  <h3 class="text-center" style="color: black">
                    Próximo exercício
                  </h3>
                  <v-card elevation="17" color="white" class="black--text">
                    <div class="text-center mx-4">
                      <h4>{{ treino.exercicios[i + 1].nome }}</h4>
                      <h5>{{ treino.exercicios[i + 1].series }} séries</h5>
                      <h5 v-if="treino.exercicios[i + 1].tipo">
                        Repetições:
                        {{ treino.exercicios[i + 1].repeticoes }} reps
                      </h5>
                      <h5 v-if="!(treino.exercicios[i + 1].tipo)">
                        Duração: {{ treino.exercicios[i + 1].repeticoes }}s
                      </h5>
                      <h5>
                        Descanso: {{ treino.exercicios[i + 1].descanso }}s
                      </h5>
                    </div>
                  </v-card>
                </v-col>
                <v-col cols="12" md="3" v-else> </v-col>
                <v-col cols="12" md="3"></v-col>
              </v-row>
            </v-sheet>
          </v-carousel-item>
        </v-carousel>
        <v-btn v-on:click="terminarTreino()" color="#f95738" dark
          >Terminar treino</v-btn
        >
      </v-col>
    </v-row>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_User.vue";
import axios from "axios";

export default {
  name: "IniciarTreino",
  components: {
    NavBar,
    SideBar,
  },
  data() {
    return {
      //True -> Repeticoes | False -> Duracao
      slide: false,
      dialog1: false,
      terminar_series: true,
      interval: {},
      n_series: 0,
      duracao_descanso: 0,
      duracao_serie: 0,
      duracao_descanso_reverso: 0,
      duracao_serie_reverso: 0,

      treino: {
        nome: "",
        duracao: "",
        categorias: [{ categoria: "" }],
        dificuldade: "",
        data: "",
        exercicios: [
          {
            nome: "",
            series: 0,
            tipo: "",
            repeticoes: 0,
            descanso: 0,
            fotos: [],
          },
        ],
        avaliacoes: [],
      },
    };
  },
  beforeDestroy () {
      clearInterval(this.interval)
    },
  methods: {
    
    terminarTreino: function () {
      this.$router.push("/treino/" + this.$route.params.codigo);
    },











    countDownTimer_serie(item, i) {
      if (!this.terminar_series) {
        
        if (this.duracao_serie > 0) {
          setTimeout(() => {
            
            this.duracao_serie_reverso+=this.value_duracao(item.repeticoes);
            this.duracao_serie--;
            this.countDownTimer_serie(item, i);
          }, 1000);
        } 
        else {
          this.duracao_serie_reverso+=this.value_duracao(item.repeticoes);
          if (this.duracao_serie == 0) {
            this.countDownTimer_descanso1(item, i);
          }
        }
      } 
      else {
        this.n_series = 0;
        this.duracao_descanso = 0;
        this.duracao_serie = 0;
        }
    },
    countDownTimer_serie1(item, i) {
      if (this.terminar_series && !item.tipo) {
        this.n_series = 0;
      }
      this.terminar_series = false;
      this.duracao_serie = item.repeticoes;
      this.countDownTimer_serie(item, i);
    },
    countDownTimer_descanso1(item, i) {
      this.duracao_descanso_reverso=0;
      this.terminar_series = false;
      this.duracao_descanso = item.descanso;
      this.countDownTimer_descanso(item, i);
    },
    countDownTimer_descanso(item, i) {
      if (this.duracao_descanso > 0) {
        setTimeout(() => {
          this.duracao_descanso_reverso =  this.duracao_descanso_reverso + this.value_descanso(item.descanso);
          this.duracao_descanso--;
          this.countDownTimer_descanso(item, i);
        }, 1000);
      } else {
        this.duracao_descanso_reverso =  this.duracao_descanso_reverso + this.value_descanso(item.descanso);
        if (this.duracao_descanso == 0) {
          this.duracao_serie_reverso=0;
          if (!item.tipo) {
            this.aumentar_serie(item, i);
            if (!this.terminar_series) this.countDownTimer_serie1(item, i);
          } 
          else {
            if(this.n_series==item.series) this.n_series=0;
            this.terminar_series = true;
          }
        }
      }
    },
    aumentar_serie(item, i) {
      if(!item.tipo){
         this.n_series++;
      }
      if (this.n_series < item.series) {
          if(item.tipo){
            this.n_series++;
            this.countDownTimer_descanso1(item, i);
          } 
      } 
      else {
          this.terminar_series = true;
          this.n_series = 0;
      }
    },
    linkapi() {
      return process.env.VUE_APP_BASELINK;
    },
    playSound() {
      //var audio = new Audio('http://soundbible.com/mp3/analog-watch-alarm_daniel-simion.mp3');
      //var audio = new Audio("http://localhost:4576/api/assets/audio/37");
      //audio.play();
    },

    submit() {
      this.$router.push("/treinos??");
    },
    value_duracao(item){
      console.log(100/item);
      return 100/item;
    },
    value_descanso(item){
      console.log(100/item);
      return 100/item;
    },
  },
  
  mounted() {
    axios
      .get(
        process.env.VUE_APP_BASELINK +
          "/api/treinos/getTreino?codigo=" +
          this.$route.params.codigo,
        { headers: { token: localStorage.getItem("token") } }
      )
      .then((response) => {
        this.treino = response.data;
        console.log("heijsfidjs" + JSON.stringify(this.treino));
      })

      .finally(() => (this.loading = false));
  },
};
</script>