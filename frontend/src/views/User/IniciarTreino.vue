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
        style="color: #f95738"
      >
        <h1
          class="text-center display-2 mb-6"
          style="color: #f95738; padding-top: 70px"
        >
          <b> {{ treino.nome }} </b>
        </h1>
        <v-carousel
          ref="carousel"
          v-model="slide"
          height="600"
          hide-delimiter-background
          show-arrows-on-hover
        >
          <v-carousel-item v-for="(item, i) in treino.exercicios" :key="i">
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
                        <v-carousel
                          :show-arrows="true"
                          height="300"
                          width="300"
                        >
                          <v-carousel-item
                            v-for="(otim, o) in item.fotos"
                            :key="o"
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

                      <v-list-item-content class="black--text">
                        <h2>{{ item.nome }}</h2>
                        <h2>{{ n_series }} / {{ item.series }} Séries</h2>

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
                              <b>Duração: {{ duracao_serie }}s</b>
                            </p>
                          </v-col>
                          <v-col
                            v-if="tipoRepeticoes(item.tipo)"
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
                              <b>Repetições: {{ item.repeticoes }} reps</b>
                            </p>
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
                              <b>Descanso: {{ duracao_descanso }}s</b>
                            </p>
                          </v-col>
                        </v-row>
                        <v-btn
                          color="black"
                          block
                          dark
                          :disabled="!terminar_series"
                          v-if="tipoRepeticoes(item.tipo)"
                          v-on:click="aumentar_serie(item, i)"
                        >
                          Próxima série
                        </v-btn>
                        <v-btn
                          v-if="tipoDuracao(item.tipo)"
                          color="black"
                          block
                          :disabled="!terminar_series"
                          v-on:click="countDownTimer_serie1(item, i)"
                          >Iniciar bloco</v-btn
                        >
                      </v-list-item-content>
                    </div>
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
                      <h5 v-if="tipoRepeticoes(treino.exercicios[i + 1].tipo)">
                        Repetições:
                        {{ treino.exercicios[i + 1].repeticoes }} reps
                      </h5>
                      <h5 v-if="tipoDuracao(treino.exercicios[i + 1].tipo)">
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
      //i: 0,
      
      n_series: 0,
      duracao_descanso: 0,
      duracao_serie: 0,
      //duracao_descanso: [],
      //n_series: [],
      //duracao_serie: [],


      treino: {
        nome: "",
        duracao: "",
        categorias: [{ categoria: "" }],
        dificuldade: "",
        //treinador: "",
        data: "",
        exercicios: [
          {
            //i: 0,
            nome: "",
            series: 0,
            tipo: "",
            repeticoes: 0,
            descanso: 0,
            fotos: [],
            //duracao_serie: 0,  
            //duracao_descanso: 0,
            //n_series: 0         
          },
        ],
        avaliacoes: [],
      },
    };
  },
  methods: {
    tipoRepeticoes: function (bool) {
      return bool;
    },
    tipoDuracao: function (bool) {
      return !bool;
    },
    terminarTreino: function () {
      this.$router.push("/treino/" + this.$route.params.codigo);
     },
    countDownTimer_serie(item, i) {
      if (!this.terminar_series) {
        if (this.duracao_serie > 0) {
          setTimeout(() => {
            //var value = this.treino.exercicios[i].duracao_serie[i] - 1;
            //this.$set(this.treino.exercicios[i], 'duracao_serie', value);
            this.duracao_serie--;
            //console.log(this.treino.exercicios[i].duracao_serie);
            this.countDownTimer_serie(item, i);
          }, 1000);
          
        } else {
          if (this.duracao_serie == 0) {
            this.playSound();
            this.countDownTimer_descanso1(item, i);
          }
        }
      } else {

        this.n_series=0;
        this.duracao_descanso = 0;
        this.duracao_serie=0;

        //this.$set(this.treino.exercicios[i], 'n_series', 0);

          //  this.$set(this.treino.exercicios[i], 'duracao_descanso',0);

            //this.$set(this.treino.exercicios[i], 'duracao_serie', 0);


        //this.treino.exercicios[i].n_series= 0;
        //this.treino.exercicios[i].duracao_descanso= 0;
        //this.treino.exercicios[i].duracao_serie= 0;
        //this.terminar_series = false; //se terminou as series, entao fica a false para a proxima 
      }
    },
    countDownTimer_serie1(item, i) {
      if(this.terminar_series && !item.tipo){
        this.n_series=0;
            //this.$set(this.treino.exercicios[i], 'n_series', 0);
        //this.treino.exercicios[i].n_series=0;
      } 
      this.terminar_series = false; 
      this.duracao_serie=item.repeticoes;
      //this.$set(this.treino.exercicios[i], 'duracao_serie', item.repeticoes);
     // this.treino.exercicios[i].duracao_serie = item.repeticoes;
      this.countDownTimer_serie(item, i);
    },
    countDownTimer_descanso1(item, i) {
      this.terminar_series = false;
      this.duracao_descanso = item.descanso;
            //this.$set(this.treino.exercicios[i], 'duracao_descanso', item.descanso);
      //this.treino.exercicios[i].duracao_descanso = item.descanso;
      this.countDownTimer_descanso(item,i);
    },
    countDownTimer_descanso(item, i) {
      if (this.duracao_descanso > 0) {
        setTimeout(() => {
          this.duracao_descanso--;
          //var value = this.treino.exercicios[i].duracao_descanso - 1;
            //this.$set(this.treino.exercicios[i], 'duracao_descanso', value);
          //this.treino.exercicios[i].duracao_descanso --;
          //console.log(this.treino.exercicios[i].duracao_descanso);
          this.countDownTimer_descanso(item, i);
        }, 1000);
      } else {
        if (this.duracao_descanso == 0) {
          if (!item.tipo) { //tipo=duraçao
            this.aumentar_serie(item, i);
            if(!this.terminar_series) this.countDownTimer_serie1(item, i);
          }
          else{
            this.terminar_series = true;
            
          } 
          this.playSound();
        }
      }
    },
    aumentar_serie(item, i) {
      if (this.n_series < item.series) {
        this.n_series++;
        //var value = this.treino.exercicios[i].n_series +1;
        //this.$set(this.treino.exercicios[i], 'n_series', value);
        //this.treino.exercicios[i].n_series++;
        //console.log("NUMERO SERIE" + this.treino.exercicios[i].n_series);
        if (item.tipo) this.countDownTimer_descanso1(item, i);
      } 
      else{
        this.terminar_series = true;
        this.n_series=0;
        //this.$set(this.treino.exercicios[i], 'n_series', 0);
        //this.treino.exercicios[i].n_series=0;
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

        //var n = 0;
        //this.treino.exercicios.forEach(x => x["indice"]= 0);
        //this.treino.exercicios.forEach(x => x["i"]= n++);
        //this.treino.exercicios.forEach(x => x["n_series"]= 0);
        //this.treino.exercicios.forEach(x => x["duracao_descanso"]= 0);
        //this.treino.exercicios.forEach(x => x["duracao_serie"]= 0);

        //this.treino.exercicios.forEach(() => this.n_series.push(0));
        //this.treino.exercicios.forEach(x => this.duracao_descanso.push(x.descanso));
        //this.treino.exercicios.forEach(x => this.duracao_serie.push(x.repeticoes));
        //console.log(this.duracao_descanso);
       
      })

      .finally(() => (this.loading = false));
  },
};
</script>