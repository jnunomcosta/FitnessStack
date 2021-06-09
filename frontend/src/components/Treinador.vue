<template>
  <v-container>
    <h5 class="mb-4 ml-8" style="color: #5b5b5b">
      {{ titles.length }} treinadores
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
              :src="'http://localhost:4576' + title.imagem"
              class="white--text align-end"
              height="150px"
            >
            </v-img>
            <!--<v-card-title v-text="title.body"></v-card-title>-->
            <v-card-title>{{title.nome}}</v-card-title>
            <v-card-subtitle>
              <v-row align="center" class="mx-0">
                <v-rating
                  :value="4.5"
                  color="amber"
                  dense
                  half-increments
                  readonly
                  size="14"
                ></v-rating>
                <div class="grey--text ml-4">4.5 (413)</div>
              </v-row>
            </v-card-subtitle>
            <v-card-actions>
              <v-spacer></v-spacer>

              <!-- <InfoTreinador /> -->
              <v-dialog transition="dialog-bottom-transition" max-width="600">
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        class="mx-2 my-2"
        v-bind="attrs"
        v-on="on"
        small
        color="#f95738"
        dark
      >
        <v-icon>mdi-text-box-search</v-icon>
      </v-btn>
    </template>
    <template v-slot:default="dialog">
      <v-card>
        <v-toolbar color="#f95738" dark
          ><h3>{{title.nome}}</h3>
          <v-spacer></v-spacer>
          <v-btn icon @click="dialog.value = false"
            ><v-icon>mdi-close</v-icon></v-btn
          >
        </v-toolbar>

        <div class="d-flex flex-no-wrap justify-space-between">
          <div>
            <v-avatar class="ma-3" width="250" height="300" tile>
              <v-img
                :src="'http://localhost:4576' + title.imagem"
              ></v-img>
            </v-avatar>
          </div>
          <div class="ma-2">
            <v-card-title
              >{{title.nome}}<v-icon dense color="#f95738" class="ml-2"
                >mdi-weight-lifter</v-icon
              ></v-card-title
            >
            <v-card-subtitle>{{title.username}}</v-card-subtitle>

            <v-card-text>
              <v-row align="center" class="mx-0">
                <div class="body-2">{{title.email}}</div>
              </v-row>

              <v-row align="center" class="mx-0 mt-6 mb-4">
                <v-rating
                  :value="4.5"
                  color="amber"
                  dense
                  half-increments
                  readonly
                  size="14"
                ></v-rating>

                <div class="grey--text ml-4">4.5 (413)</div>
              </v-row>

              <v-divider></v-divider>
              <div class="my-4">
                {{title.descricao}}
              </div>
            </v-card-text>
          </div>
        </div>
        
        
        <v-dialog v-model="dialog1" persistent max-width="500px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="#f95738"
                    dark
                    small
                    class="mb-1"
                    v-bind="attrs"
                    v-on="on"
                  >
                    Solicitar contrato
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span class="headline">Escreva uma mensagem ao treinador.</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col cols="12" sm="6" md="12">
                          <v-text-field
                            v-model="input_c"
                            color="#f95738"
                            label="Mensagem"
                            required
                          ></v-text-field>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="#f95738" text @click="dialog2 = false">
                      Sair
                    </v-btn>
                    <v-btn color="#f95738" text @click="solicitar(title.username,input_c);dialog2 = false">
                      Solicitar contrato
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
      </v-card>
    </template>
  </v-dialog>


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
/* import InfoTreinador from "./InfoTreinador.vue";
 */
export default {
  name: "Treinador",
  /* components: {
    InfoTreinador,
  }, */
  data() {
    return {
      titles: [],
      page: 1,
    };
  },
  computed: {
    url() {
      /* return "https://jsonplaceholder.typicode.com/posts?_page=" + this.page; */
      return "http://localhost:4576/api/treinador/listar";
    },
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      const response = await axios.get(this.url,{headers: {'token': localStorage.getItem("token")}});
      console.log("dsadsadsa"+response.data);
      this.titles = response.data;
    },
    solicitar(treinador,comment){

      console.log("ndfjds"+ JSON.stringify(treinador))
      console.log("zzzzzzzzzz"+ JSON.stringify(comment))
      axios
      .post("http://localhost:4576/api/user/criarContrato",
      {
        treinador:treinador,
        comentario:comment
      },
      {headers: {'token': localStorage.getItem("token")}})

      .then((response) => {
        
      console.log("sucesso")

       console.log("dkansdjnsadjnsa"+ JSON.stringify(response.data))
      })
      .finally(() => (console.log("jo")));
    }
    }
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
  
};
</script>
