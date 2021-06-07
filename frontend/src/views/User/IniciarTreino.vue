<template>
  <div class="profile">
    <NavBar />
    <h1 class="text-center display-2" style="color: #f95738; margin-top: 90px">
      Titulo do treino
    </h1>
    <v-row
      class="mt-4"
      style="
        color: #f95738;
        margin-top: 60px;
        margin-right: 50px;
        margin-left: 100px;
      "
    >
      <v-col cols="12" md="1">
        <SideBar />
      </v-col>
      <v-col cols="12" md="12">
        <v-carousel
          ref="carousel"
          v-model="slide"
          height="600"
          hide-delimiter-background
          show-arrows-on-hover
        >
          <v-carousel-item v-for="(slide, i) in slides" :key="i">
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
                        <h2>{{ i }}</h2>
                        <h2>NOME DO EXERCÍCIO</h2>
                        <h2>{{ series }} / {{ series_total }} Séries</h2>

                        <v-row class="text-center" style="text-align: center">
                          <v-col v-if="flag_duracao" cols="12" md="6">
                            <p
                              class="
                                pa-4
                                grey
                                lighten-2
                                text-no-wrap
                                rounded-pill
                              "
                            >
                              Duração: {{ duracao_serie }} segundos
                            </p>
                          </v-col>
                          <v-col v-if="flag_repeticoes" cols="12" md="6">
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
                              Descanso: {{ duracao_descanso }} segundos
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
                      <h4>Nome</h4>
                      <h5>Séries</h5>
                      <h5>Duração</h5>
                      <h5>Descanso</h5>
                    </div>
                  </v-card> </v-col
                ><v-col cols="12" md="3"></v-col>
              </v-row>
            </v-sheet>
          </v-carousel-item>
        </v-carousel>
      </v-col>
    </v-row>

    
<v-btn color="#f95738" dark>Avaliar treino</v-btn>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_User.vue";

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
      terminar_series: false
      //page:0
      //interval: 2000,
    };
  },
  methods: {
    //pageChange(i){ console.log('current Index', i); },

    terminarTreino: function () {
      //this.refs.carousel.goToPage(1);
      //this.$refs.carousel[0].goToPage(this.$refs.carousel[0].getNextPage());
    },
    //onSlideChange() {
    //if (this.countDown===0) {
    //   this.interval = 1000000;
    //}
    //},
    countDownTimer_serie() {
      if (this.series>-1){
        if (this.duracao_serie > -1 && this.terminar_series==false) {
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
      else this.series=0;
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
};
</script>