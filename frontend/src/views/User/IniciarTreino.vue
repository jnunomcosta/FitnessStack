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
          <v-carousel-item v-for="(item,i) in treino.exercicios" :key="i">
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
                      i
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
                              Repetições: {{ item.repeticoes }} repetições
                            </p>
                            <v-btn
                              color="black"
                              dark
                              v-on:click="aumentar_serie(item)"
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
                          v-if="tipoDuracao(item.tipo)"
                          color="black"
                          text
                          v-on:click="countDownTimer_serie(item)"
                          >Iniciar</v-btn
                        >
                      </v-list-item-content>
                    </div>
                  </v-card>
                </v-col>
                <v-col cols="12" md="3" v-if="i+1 < Object.keys(treino.exercicios).length">
                  <h3 class="text-center" style="color: black">
                    Próximo exercício
                  </h3>
                  <v-card elevation="17" color="white" class="black--text">
                    <div class="text-center mx-4">
                      <h4>{{ item[i+1].nome }} </h4>
                      <h5>{{ item[i+1].series }} Séries</h5>
                      <h5 v-if="tipoRepeticoes(item[i+1].tipo)">{{ item[i+1].duracao }}  repetições</h5>
                      <h5 v-if="tipoDuracao(item[i+1].tipo)">{{ item[i+1].duracao }}  segundos</h5>
                      <h5>{{ item[i+1].descanso }} </h5>
                    </div>
                  </v-card> 
                </v-col>
                <v-col cols="12" md="3" v-else>

                </v-col>
              <v-col cols="12" md="3"></v-col>
                
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
      series: 0,
      terminar_series: false,
      duracao_descanso: 0,
      duracao_serie: 0,
      treino: {
        nome: "",
        duracao: "",
        categorias: [{ categoria: "" }],
        dificuldade: "",
        //treinador: "",
        data: "",
        exercicios: [{ nome: "", series: 0, tipo: "", repeticoes: 0, descanso: 0, foto: ""}],
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
    countDownTimer_serie(item) {
      this.duracao_serie = item.series;
      if (this.terminar_series==false){


        if (this.duracao_serie > -1) {
          setTimeout(() => {
            this.duracao_serie -= 1;
            //this.countDownTimer_serie(item);
          }, 1000);




        } else {
          if (this.duracao_serie == -1) {
            this.playSound();
            this.countDownTimer_descanso(item);
          }
        }
      }
      else {
        this.series=0;
        this.terminar_series=false;
      }
    },

    countDownTimer_descanso(item) {
      this.duracao_descanso = item.descanso;



      if (this.duracao_descanso > -1) {
        setTimeout(() => {
          this.duracao_descanso -= 1;
          //this.countDownTimer_descanso(item);
        }, 1000);



      } else {
        if (this.duracao_descanso == -1) {
          if (!item.tipo) {
            this.aumentar_serie(item);
            this.countDownTimer_serie();
          }
          this.playSound();
        }
      }
    },

    aumentar_serie(item) {
      if (this.series < item.series) {
        this.series++;
        if (item.tipo) this.countDownTimer_descanso(item);
      }
      else this.terminar_series=true;
    },

    playSound() {
      //var audio = new Audio('http://soundbible.com/mp3/analog-watch-alarm_daniel-simion.mp3');
      //var audio = new Audio("http://localhost:4576/api/assets/audio/37");
      //audio.play();
    },

    submit(){
      this.$router.push('/treinos??')
    }
  },
  mounted() {
    axios
      .get(process.env.VUE_APP_BASELINK + "/api/treinos/getTreino?codigo="+this.$route.params.codigo,{headers: { token: localStorage.getItem("token")}})
      .then((response) => {
        this.treino = response.data;
        console.log(response);
      })
      .finally(() => (this.loading = false));
  },
  
};
</script>