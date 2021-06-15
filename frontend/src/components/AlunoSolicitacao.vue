<template>
  <v-container>
    <h5 class="mb-4 ml-8 mt-4" style="color: #5b5b5b">
      {{ titles.length }} solicitações
    </h5>
    <v-row class="fill-height overflow-y-auto" v-if="titles.length">
      <v-col
        lg="3"
        md="4"
        sm="6"
        cols="12"
        v-for="(title, index) in titles"
        :key="index"
      >
        <v-sheet min-height="150px" class="fill-height" color="transparent">
          <v-card hover class="white">
            <v-img
               :src="linkfoto() + title.foto_utilizador"
              class="white--text align-end"
              height="150px"
            >
            </v-img>
            <v-card-title> {{ title.utilizador }} </v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>

              <InfoAlunoSolicitacao :data="title"/>

            </v-card-actions>
          </v-card>
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>

</template>

<script>
import axios from "axios";
import InfoAlunoSolicitacao from "./InfoAlunoSolicitacao.vue"

export default {
  name: "Aluno",
  components:{
    InfoAlunoSolicitacao
  },
  data() {
    return {
      titles: [],
      page: 1,
    };
  },
  computed: {
    url() {
      return process.env.VUE_APP_BASELINK+"/api/treinador/getAlunosPendentes"
    },
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      const response = await axios.get(this.url, {headers: {'token': localStorage.getItem("token")}});
      this.titles = response.data;
      
    },
    linkfoto(){
      return  process.env.VUE_APP_BASELINK
    },
  },
};
</script>
