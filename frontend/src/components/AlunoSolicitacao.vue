<template>
  <v-container>
    <h5 class="mb-4 ml-8 " style="color: #5b5b5b">
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
            <!--<v-card-title v-text="title.body"></v-card-title>-->
            <v-card-title> {{ title.utilizador }} </v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>

              <InfoAlunoSolicitacao :data="title"/>

              <!-- <v-dialog v-model="dialog" persistent max-width="700">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    class="mx-4"
                    small
                    color="#f95738"
                    dark
                    v-bind="attrs"
                    v-on="on"
                  >
                    VER MAIS
                  </v-btn>
                </template>

                <v-card height="37vh" style="text-align: center">
                  <v-row>
                    <v-col cols="12" md="4">
                      <v-card-text>
                        <div class="mx-auto text-center">
                          <v-avatar class="mt-4" size="150">
                            <v-img src="https://picsum.photos/200"></v-img>
                          </v-avatar>
                        </div>
                        <v-card-title class="justify-center">Xana</v-card-title>
                        <v-card-subtitle> xanareigada </v-card-subtitle>
                        <v-divider class="mx-4"></v-divider>
                        <p>Email</p>
                        <v-rating
                          :value="4.5"
                          color="amber"
                          dense
                          half-increments
                          readonly
                          size="14"
                        ></v-rating>
                      </v-card-text>
                    </v-col>
                    <v-col
                      cols="12"
                      md="1"
                      style="margin-top: 40px; margin-bottom: 40px"
                    >
                      <v-divider class="mx-10" vertical></v-divider>
                    </v-col>
                    <v-col cols="12" md="7" style="margin-top: 50px">
                      <p>
                        Olá sou o João e tenho 30 anos. Sou licenciado em
                        Desporto e ...
                      </p>

                      <v-spacer></v-spacer>
                      <div style="margin-top: 200px">
                        <v-btn color="#f95738" text @click="dialog4 = false">
                          Sair
                        </v-btn>
                        <v-btn color="#f95738" text @click="dialog4 = false">
                          Enviar pedido
                        </v-btn>
                      </div>
                    </v-col>
                  </v-row>
                </v-card>
              </v-dialog> -->
            </v-card-actions>
          </v-card>
          <v-card v-intersect="infiniteScrolling"></v-card>
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
  
    /*infiniteScrolling(entries, observer, isIntersecting) {
      setTimeout(() => {
        this.page++;
        axios
          .get(this.url)
          .then((response) => {
            if (response.data.length > 1) {
              response.data.forEach((item) => this.titles.push(item));
            } 
          })
          .catch((err) => {
            console.log(err);
          });
      }, 500);
    },*/
  },
};
</script>
