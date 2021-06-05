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
        cycle
        :interval=  "this.interval" 
        @input="onSlideChange"

       
          height="600"
          hide-delimiter-background
          show-arrows-on-hover
        >
          <v-carousel-item v-for="(slide, i) in slides" :key="i">
            <v-sheet color="grey" height="100%">
              <v-row class="fill-height" align="center" justify="center">
                <v-col cols="12" md="1" > </v-col>
                <v-col cols="12" md="7" >
                  <h3 class="text-center">Exercício atual</h3>
                  <v-card elevation="17" shaped color="white" style="text-align: center">
            <div class="text-center mx-4">
                    <v-img style='text-align:center'
                        max-height="250"
                        max-width="400"
                        src="https://randomuser.me/api/portraits/men/93.jpg"
                      ></v-img>
                    
                    <v-list-item-content class="black--text">
                      <h2>Nome</h2>
                      <h2>Séries</h2>

                      <p>{{ countDown }}</p>
                      <v-btn color="black" text v-on:click="countDownTimer()">Iniciar</v-btn>
                    </v-list-item-content></div>
                  </v-card>
                </v-col>
                <v-col cols="12" md="3">
                  <h3 class="text-center">Próximo exercício</h3>
                  <v-card elevation="17" shaped color="white" class="black--text">
                    <div class="text-center mx-4">
                    <h4>Nome</h4>
                    <h5>Séries</h5>
                    <h5>Duração</h5>
                    <h5>Descanso</h5>
                    </div>
                  </v-card>
                  
                </v-col><v-col cols="12" md="3"></v-col>
              </v-row>
            </v-sheet>
          </v-carousel-item>
        </v-carousel>
      </v-col>
    </v-row>
    <div class="text-center my-16">
      <v-btn v-on:click="terminarTreino()" color="#f95738" dark
        >Terminar treino</v-btn
      >
    </div>
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
      slides: ["First", "Second", "Third", "Fourth", "Fifth"],
      countDown: 2,
      interval: 10000
    };
  },
  methods: {
    terminarTreino: function () {},
     //onSlideChange() {
        //if (this.countDown===0) {
         //   this.interval = 1000000;
        //}
    //},
    countDownTimer() {
      if (this.countDown > 0) {
        setTimeout(() => {
          this.countDown -= 1;
          this.countDownTimer();
        }, 1000);
      } else {
        if (this.countDown == 0) {
          this.interval=1;
          this.playSound();
          this.countDown = 2;
        } 
      }
      this.interval=10000;
    },
    playSound() {
      //var audio = new Audio('http://soundbible.com/mp3/analog-watch-alarm_daniel-simion.mp3');
      var audio = new Audio("http://localhost:4576/api/assets/audio/37");
      audio.play();
    },
  },
};
</script>