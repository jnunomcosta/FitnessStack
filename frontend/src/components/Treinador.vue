<template>
  <div>
    <v-alert
      border="left"
      v-if="success"
      text
      dismissible
      elevation="2"
      type="success"
    >
      Solicitação enviada com sucesso
    </v-alert>
    <v-alert
      border="left"
      v-if="error"
      text
      dismissible
      elevation="2"
      type="error"
    >
      Erro ao enviar solicitação
    </v-alert>

    <v-toolbar dense rounded class="mx-6" style="margin-top: 40px">
      <v-text-field
        v-model="searchValue"
        @input="searchbarInput()"
        hide-details
        prepend-icon="mdi-magnify"
        label="Procurar treinador"
        single-line
        color="#f95738"
      ></v-text-field>
    </v-toolbar>
    <v-container>
      <h5 class="mb-4 ml-8" style="color: #5b5b5b">
        <span v-text="visibleTreinadores"></span> de
        <span v-text="total"></span> treinadores
      </h5>
      <v-row class="fill-height overflow-y-auto" v-if="treinadores.length">
        <v-col
          lg="3"
          md="4"
          sm="6"
          cols="12"
          v-for="(title, index) in treinadores"
          :key="index"
        >
          <v-sheet min-height="150px" class="fill-height" color="transparent">
            <v-lazy
              v-model="title.isActive"
              :options="{ threshold: 0.5 }"
              class="fill-height"
            >
              <v-card hover class="white">
                <v-img
                  :src="linkapi() + title.imagem"
                  class="white--text align-end"
                  height="150px"
                >
                </v-img>
                <v-card-title>{{ title.nome }}</v-card-title>
                <v-card-subtitle>
                  <v-row align="center" class="mx-0">
                    <v-rating
                      :value="title.classsificacao"
                      background-color="black"
                      color="#f95738"
                      dense
                      half-increments
                      readonly
                      size="14"
                    ></v-rating>
                    <div class="grey--text ml-4">
                      {{ parseFloat(title.classsificacao).toFixed(1) }} ({{
                        title.numero_classsificacao
                      }})
                    </div>
                  </v-row>
                </v-card-subtitle>
                <v-card-actions>
                  <v-spacer></v-spacer>

                  <!-- <InfoTreinador /> -->
                  <v-dialog
                  v-model="dialog"
                    transition="dialog-bottom-transition"
                    max-width="700"
                  >
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
                    <template >
                      <v-card>
                        <v-toolbar color="#f95738" dark
                          ><h3>{{ title.nome }}</h3>
                          <v-spacer></v-spacer>
                          <v-btn icon @click="dialog = false"
                            ><v-icon>mdi-close</v-icon></v-btn
                          >
                        </v-toolbar>

                        <row class="d-flex flex-no-wrap">
                          <v-col cols="12" md="5">
                            <v-avatar
                              class="ma-3"
                              width="250"
                              height="300"
                              tile
                            >
                              <v-img :src="linkapi() + title.imagem"></v-img>
                            </v-avatar>
                          </v-col>
                          <v-col cols="12" md="7">
                            <v-card-title
                              >{{ title.nome
                              }}<v-icon dense color="#f95738" class="ml-2"
                                >mdi-weight-lifter</v-icon
                              ></v-card-title
                            >
                            <v-card-subtitle>{{
                              title.username
                            }}</v-card-subtitle>

                            <v-card-text>
                              <v-row align="center" class="mx-0">
                                {{ title.email }}</v-row
                              >

                              <v-row align="center" class="mx-0 mt-6 mb-4">
                                <v-rating
                                  :value="title.classsificacao"
                                  background-color="black"
                                  color="#f95738"
                                  dense
                                  half-increments
                                  readonly
                                  size="14"
                                ></v-rating>

                                <div class="grey--text ml-4">
                                  {{
                                    parseFloat(title.classsificacao).toFixed(1)
                                  }}
                                  ({{ title.numero_classsificacao }})
                                </div>
                              </v-row>

                              <v-divider></v-divider>
                              <div class="my-4">
                                {{ title.descricao }}
                              </div>
                            </v-card-text>
                          </v-col>
                        </row>

                        <v-card-actions>
                          <v-spacer></v-spacer>
                          <v-dialog
                            v-model="dialog2"
                            persistent
                            max-width="500px"
                          >
                            <template v-slot:activator="{ on, attrs }">
                              <v-btn
                                v-show="treinador == null"
                                color="#f95738"
                                text
                                v-bind="attrs"
                                v-on="on"
                              >
                                Solicitar contrato
                              </v-btn>
                            </template>
                            <v-card>
                              <v-card-title>
                                <span>Escreva uma mensagem ao treinador</span>
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
                                <v-btn
                                  color="#f95738"
                                  text
                                  @click="dialog2 = false"
                                >
                                  Sair
                                </v-btn>
                                <v-btn
                                  color="#f95738"
                                  dark
                                  v-on:click="
                                    {
                                      solicitar(title.username, input_c);
                                      dialog2 = false;
                                      dialog = false;
                                    }
                                  "
                                >
                                  Solicitar contrato
                                </v-btn>
                              </v-card-actions>
                            </v-card>
                          </v-dialog>
                        </v-card-actions>
                      </v-card>
                    </template>
                  </v-dialog>
                </v-card-actions>
              </v-card>
            </v-lazy>
          </v-sheet>
        </v-col>
      </v-row>
      <v-row v-else-if="total == 0">
        <v-col class="text-center">
          <h5 style="padding-top: 150px; padding-bottom: 400px; color: #5b5b5b">
            Não existem treinadores registados
          </h5>
        </v-col>
      </v-row>
      <v-row v-else>
        <v-col class="text-center">
          <h5 style="padding-top: 150px; padding-bottom: 400px; color: #5b5b5b">
            Não foi encontrado nenhum treinador
          </h5>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Treinador",
  data() {
    return {
      success: false,
      error: false,
      input_c: "",
      dialog: false,
      dialog2: false,
      titles: [],
      page: 1,
      total: 0,
      searchValue: "",
      treinadores: [],
      treinadoresbackup: [],
      total_treinadores: 0,
      treinador: null,
      contrato: {
        utilizador: "",
        estado: false,
        treinador: "",
        comentario: "",
      },
    };
  },
  mounted() {
    this.pullData();
    this.getDataTotal();
    axios
      .get(process.env.VUE_APP_BASELINK + "/api/user/getTreinadorResp", {
        headers: { token: localStorage.getItem("token") },
      })
      .then((response) => {
        this.contrato = response.data;

        axios
          .get(
            process.env.VUE_APP_BASELINK +
              "/api/treinador/getTreinadorInfo?username=" +
              this.contrato.treinador,
            { headers: { token: localStorage.getItem("token") } }
          )
          .then((responsetreinador) => {
            this.treinador = responsetreinador.data;
          });
      });
  },
  computed: {
    url() {
      return process.env.VUE_APP_BASELINK + "/api/treinador/listar";
    },
    visibleTreinadores() {
      return this.treinadores.filter((p) => p.isActive).length;
    },
  },
  methods: {
    searchbarInput() {
      const searchContent = this.searchValue;
      console.log(searchContent);
      if (searchContent == "") {
        this.treinadores = this.treinadoresbackup;
        this.total_treinadores = this.treinadoresbackup.length;
      } else {
        this.treinadores = [];
        this.total_treinadores = 0;
        this.getDataFiltered(searchContent);
      }
    },
    pullData() {
      axios
        .get(process.env.VUE_APP_BASELINK + "/api/treinador/listar", {
          headers: { token: localStorage.getItem("token") },
        })
        .then((response) => {
          if (response.status == 200) {
            response.data.forEach((x) => {
              this.treinadores.push(x);
              this.treinadoresbackup.push(x);
              this.total_treinadores++;
            });
          }
        });
    },
    getDataTotal() {
      axios
        .get(
          process.env.VUE_APP_BASELINK + "/api/treinador/getNumTreinadores",
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response) => {
          console.log(response);
          if (response.status == 200) {
            this.total = response.data.numero;
          }
        });
    },
    getDataFiltered(filtro) {
      axios
        .get(
          process.env.VUE_APP_BASELINK +
            "/api/treinador/listarFiltrado?filtro=" +
            filtro,
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response) => {
          if (response.status == 200) {
            response.data.forEach((x) => {
              this.treinadores.push(x);
              //this.exerciciosBackup.push(x);
              this.totalExercicios++;
            });
          }
        });
    },
    linkapi() {
      return process.env.VUE_APP_BASELINK;
    },
    solicitar(treinador, comment) {
      axios
        .post(
          process.env.VUE_APP_BASELINK + "/api/user/criarContrato",
          {
            treinador: treinador,
            comentario: comment,
          },
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response) => {
          const status = JSON.parse(response.status);
          if (status == "200") {
            this.success = true;
            setTimeout(() => {
              this.success = false;
              this.$router.go();
            }, 5000);
          }
        })
        .catch((error) => {
          if (error.response != null) {
            this.error = true;
            setTimeout(() => {
              this.error = false;
            }, 5000);
          }
        });
    },
  },
};
</script>
