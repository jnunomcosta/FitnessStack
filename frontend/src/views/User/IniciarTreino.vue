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
        style="
          color: #f95738;
        "
      >
        <h1
          class="text-center display-2 mb-6"
          style="color: #f95738; padding-top: 70px"
        >
          {{ treino.nome }}
        </h1>
        <v-carousel
          ref="carousel"
          v-model="slide"
          height="600"
          hide-delimiter-background
          show-arrows-on-hover
        >
          <v-carousel-item v-for="item in treino.exercicios" :key="item.nome">
            <v-sheet color="grey" height="100%">
              <v-row class="fill-height" align="center" justify="center">
                <v-col cols="12" md="1"> </v-col>
                <v-col cols="12" md="7">
                  <v-card
                    elevation="17"
                    color="white"
                    class="text-center"
                    style="text-align: center"
                  >
                    <div class="text-center mx-4">
                      <div class="mx-auto text-center">
                        <v-avatar tile class="mt-4" size="200">
                          <v-img
                            src="https://randomuser.me/api/portraits/men/93.jpg"
                          ></v-img>
                        </v-avatar>
                      </div>

                      <v-list-item-content class="black--text">
                        <h2>{{ item.nome }}</h2>
                        <h2>{{ series }} / {{ item.series }} Séries</h2>

                        <v-row class="text-center" style="text-align: center">
                          <v-col v-if="tipoDuracao(item.tipo)" cols="12" md="6">
                            <p
                              class="
                                pa-4
                                grey
                                lighten-2
                                text-no-wrap
                                rounded-pill
                              "
                            >
                              Duração: {{ item.duracao }} segundos
                            </p>
                          </v-col>
                          <v-col v-if="tipoRepeticoes(item.tipo)" cols="12" md="6">
                            <p
                              class="
                                pa-4
                                grey
                                lighten-2
                                text-no-wrap
                                rounded-pill
                              "
                            >
                              Repetições: {{ item.duracao }} repetições
                            </p>
                            <v-btn
                              color="black"
                              dark
                              v-on:click="aumentar_serie()"
                            >
                              Próxima série
                              <v-icon dense> mdi-ArrowRightBoldOutline </v-icon>
                            </v-btn>
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
                              Descanso: {{ item.descanso  }} segundos
                            </p>
                          </v-col>
                        </v-row>
                        <v-btn
                          v-if="flag_duracao"
                          color="black"
                          text
                          v-on:click="countDownTimer_serie()"
                          >Iniciar</v-btn
                        >
                      </v-list-item-content>
                    </div>
                  </v-card>
                </v-col>
                <v-col cols="12" md="3">
                  <h3 class="text-center" style="color: black">
                    Próximo exercício
                  </h3>
                  <v-card elevation="17" color="white" class="black--text">
                    <div class="text-center mx-4">
                      <!-- <h4>{{ item[item.nome+1].nome }} </h4>
                      <h5>{{ item[item.nome+1].series }} Séries</h5>
                      <h5 v-if="tipoRepeticoes(item[item.nome+1].tipo)">{{ item[item.nome+1].duracao }}  repetições</h5>
                      <h5 v-if="tipoDuracao(item[item.nome+1].tipo)">{{ item[item.nome+1].duracao }}  segundos</h5>
                      <h5>{{ item[item.nome+1].descanso }} </h5> -->
                    </div>
                  </v-card> </v-col
                ><v-col cols="12" md="3"></v-col>
              </v-row>
            </v-sheet>
          </v-carousel-item>
        </v-carousel>
        <v-btn v-on:click="terminarTreino()" color="#f95738" dark>Terminar treino</v-btn>

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
      dialog1: false,
      slides: ["First", "Second", "Third", "Fourth", "Fifth"],
      duracao_serie: 2,
      duracao_descanso: 2,
      series: 0,
      series_total: 5,
      flag_repeticoes: false,
      flag_duracao: true,
      terminar_series: false,
      treino: {
        nome: "",
        duracao: "",
        categorias: [{ categoria: "" }],
        dificuldade: "",
        //treinador: "",
        data: "",
        exercicios: [{ nome: "", series: 0, tipo: "", repeticoes: 0, descanso: 0 }],
        avaliacoes: [],
      },

      //exercicios: [{ nome: "", series: 0, repeticoes: 0, descanso: 0 }],
      
    };
  },
  methods: {
    //pageChange(i){ console.log('current Index', i); },
    tipoRepeticoes: function (bool) {
      return bool;
    },
    tipoDuracao: function (bool) {
      return (!bool);
    },
    terminarTreino: function () {
      this.$router.push('/treino/' + this.$route.params.codigo)
      //this.refs.carousel.goToPage(1);
      //this.$refs.carousel[0].goToPage(this.$refs.carousel[0].getNextPage());
    },
    //onSlideChange() {
    //if (this.countDown===0) {
    //   this.interval = 1000000;
    //}
    //},
    countDownTimer_serie() {
      if (this.terminar_series==false){
        if (this.duracao_serie > -1) {
          setTimeout(() => {
            this.duracao_serie -= 1;
            this.countDownTimer_serie();
          }, 1000);
        } else {
          if (this.duracao_serie == -1) {
            this.playSound();
            this.duracao_serie = 2; //voltar a dar o valor inicial
            this.countDownTimer_descanso();
          }
        }
      }
      else {
        this.series=0;
        this.terminar_series=false;
      }
    },

    countDownTimer_descanso() {
      if (this.duracao_descanso > -1) {
        setTimeout(() => {
          this.duracao_descanso -= 1;
          this.countDownTimer_descanso();
        }, 1000);
      } else {
        if (this.duracao_descanso == -1) {
          if (this.flag_duracao) {
            this.aumentar_serie();
            this.countDownTimer_serie();
          }
          this.playSound();
          this.duracao_descanso = 2;
        }
      }
    },

    aumentar_serie() {
      if (this.series < this.series_total) {
        this.series++;
        if (this.flag_repeticoes) this.countDownTimer_descanso();
      }
      else this.terminar_series=true;
    },

    playSound() {
      //var audio = new Audio('http://soundbible.com/mp3/analog-watch-alarm_daniel-simion.mp3');
      var audio = new Audio("http://localhost:4576/api/assets/audio/37");
      audio.play();
    },

    submit(){
      this.$router.push('/treinos??')
    }
  },
  mounted() {
    axios
      .get(
        "http://localhost:4576/api/treinos/getTreino?codigo=" +
          this.$route.params.codigo
      )
      .then((response) => {
        this.treino = response.data;
      })
      .finally(() => (this.loading = false));
  },
  
};
</script>