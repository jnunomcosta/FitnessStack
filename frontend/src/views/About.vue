<template>
  <div class="about text-center">
    <h1>Exemplo de Consumo da API</h1>
    <p v-if="loading">A carregar...</p>
    <p v-if="error">Há erros...</p>
    <p>{{ variavelRecebidaDaAPI }}</p>
  </div>
</template>

<script>
import axios from 'axios';
export default {
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