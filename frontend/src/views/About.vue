<template>
  <div class="about text-center">
    <NavBar />

    <h1 style="padding: 100px">Exemplo de Consumo da API</h1>
    <p v-if="loading">A carregar...</p>
    <p v-if="error">Há erros...</p>
    <p>{{ variavelRecebidaDaAPI }}</p>
    <Footer />
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import Footer from "@/components/Footer.vue";

import axios from 'axios';

export default {
  name: "About",
  components: {
    NavBar,
    Footer,
  },
  created() {
    document.title = "Sobre o Fitness Stack";
  },
  // Exemplo de Consumo da API
  data () {
    return {
      variavelRecebidaDaAPI: "",
      loading: true,
      error: false
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