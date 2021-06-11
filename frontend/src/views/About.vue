<template>
  <div class="about text-center">
    <NavBar />

    <h1 style="padding: 100px">Exemplo de Consumo da API</h1>
    <p v-if="loading">A carregar...</p>
    <p v-if="error">Há erros...</p>
    <p>{{ countDown }}</p>
    <v-btn color="#7189ff" text v-on:click="countDownTimer()">Registar</v-btn>
    <!-- <v-btn color="#7189ff" text v-on:click="playSound()">Alarm</v-btn> -->
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";

import axios from 'axios';

export default {
  name: "About",
  components: {
    NavBar,
  },
  created() {
    document.title = "Sobre o Fitness Stack";
  },
  
  data () {
    return {
      variavelRecebidaDaAPI: "",
      loading: true,
      error: false,
      countDown: 5,
    }
  },
  methods: {
    countDownTimer() {
      if(this.countDown > 0) {
        setTimeout(() => {
          this.countDown -= 1
          this.countDownTimer()
        }, 1000)
      }
      else {
        if (this.countDown == 0){
          this.playSound();
          this.countDown = 5;
        }
      }
    },
    playSound () {
      //var audio = new Audio('http://soundbible.com/mp3/analog-watch-alarm_daniel-simion.mp3');
      var audio = new Audio(process.env.VUE_APP_BASELINK+'/api/assets/audio/37');
      audio.play();
    }
  },
  mounted () {
    axios
      .get('https://api.coindesk.com/v1/bpi/currentprice.json')
      .then(response => {
        this.variavelRecebidaDaAPI = response.data.bpi
      })
      .finally(() => this.loading = false)
  }
}
</script>