<template>
  <div class="treinadores">
    <NavBar />
    <v-row>
      <v-col cols="12" md="1">
        <SideBar />
      </v-col>
      <v-col
        cols="12"
        md="10"
        style="padding-top: 120px; padding-bottom: 100px"
      >
        <v-dialog transition="dialog-bottom-transition" max-width="700">
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              v-show="treinador != null"
              class="mx-6"
              v-bind="attrs"
              v-on="on"
              color="#f95738"
              dark
            >
              O meu treinador
              <v-chip small color="white" style="color:black" class="ml-2" v-if="!contrato.estado"> Pendente </v-chip>
            </v-btn>
          </template>
          <template v-slot:default="dialog">
            <v-card>
              <v-toolbar color="#f95738" dark
                ><h3 class="mr-4">{{ treinador.nome }}</h3>
                <v-chip color="white" style="color:black" v-if="!contrato.estado"> Pendente </v-chip>
                <v-chip color="green" v-else>Ativo</v-chip>
                <v-spacer></v-spacer>

                <v-btn icon @click="dialog.value = false"
                  ><v-icon>mdi-close</v-icon></v-btn
                >
              </v-toolbar>

              <row class="d-flex flex-no-wrap">
                <v-col cols="12" md="5">
                  <v-avatar class="ma-3" width="250" height="300" tile>
                    <v-img :src="linkapi() + treinador.foto_perfil"></v-img>
                  </v-avatar>
                </v-col>
                <v-col cols="12" md="7">
                  <v-card-title
                    >{{ treinador.nome }}
                    <v-icon dense color="#f95738" class="ml-2"
                      >mdi-weight-lifter</v-icon
                    >
                  </v-card-title>
                  <v-card-subtitle>{{ treinador.username }}</v-card-subtitle>

                  <v-card-text>
                    <v-row align="center" class="mx-0">
                      <div class="body-2">{{ treinador.email }}</div>
                    </v-row>

                    <v-row align="center" class="mx-0 mt-6 mb-4">
                      <v-rating
                        :value="treinador.classsificacao"
                        background-color="black"
                        color="#f95738"
                        dense
                        half-increments
                        readonly
                        size="14"
                      ></v-rating>

                      <div class="grey--text ml-4">
                        {{ parseFloat(treinador.classsificacao).toFixed(1) }}
                        ({{ treinador.numero_classsificacao }})
                      </div>
                    </v-row>

                    <v-divider></v-divider>
                    <div class="my-4">
                      {{ treinador.descricao }}
                    </div>
                  </v-card-text>
                </v-col>
              </row>

              <v-card-actions>
                <v-spacer></v-spacer>

                <v-btn
                  color="#f95738"
                  text
                  @click="
                    cancelar();
                    dialog.value = false;
                  "
                >
                  Cancelar contrato
                </v-btn>

                <v-dialog v-model="dialog1" persistent max-width="380px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      v-if="contrato.estado"
                      v-bind="attrs"
                      v-on="on"
                      color="#f95738"
                      text
                    >
                      Avaliar Treinador
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title>
                      <span class="text-center"
                        >Dê-nos o seu feedback acerca deste treinador!</span
                      >
                    </v-card-title>
                    <v-card-text>
                      <div class="text-center mt-12">
                        <v-rating
                          v-model="rating"
                          color="#f95738"
                          background-color="grey darken-1"
                          empty-icon="$ratingFull"
                          half-increments
                          hover
                          large
                        ></v-rating>
                        <v-text-field
                          color="#f95738"
                          v-model="comentario"
                          label="Deixe-nos o seu comentário."
                          required
                        >
                        </v-text-field>
                      </div>
                    </v-card-text>
                    <v-btn color="#f95738" text @click="dialog1 = false"
                      >Sair</v-btn
                    >
                    <v-btn
                      color="#f95738"
                      dark
                      @click="dialog1 = false"
                      v-on:click="submit()"
                    >
                      Confirmar
                    </v-btn>
                  </v-card>
                </v-dialog>
              </v-card-actions>
            </v-card>
          </template>
        </v-dialog>

        <v-container class="pa-0 ma-0"> <Treinador /></v-container>
      </v-col>
    </v-row>
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_User.vue";
import Treinador from "@/components/Treinador.vue";

import axios from "axios";

export default {
  name: "Treinadores",
  data: () => ({
    rating: "",
    comentario: "",
    dialog1: false,
    contrato: {
      utilizador: "",
      estado: false,
      treinador: "",
      comentario: "",
    },
    treinador: null,
    /*treinador:{
          "foto_perfil": "",
          "nome": "",
          "email":"",
          "username": "",
          "descricao":""

      }*/
  }),
  components: {
    NavBar,
    SideBar,
    Treinador,
  },
  created() {
    document.title = "Treinadores";
  },
  mounted() {
    axios
      .get(process.env.VUE_APP_BASELINK + "/api/user/getTreinadorResp", {
        headers: { token: localStorage.getItem("token") },
      })
      .then((response) => {
        this.contrato = response.data;
        console.log(JSON.stringify(this.contrato));

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
  methods: {
    linkapi() {
      return process.env.VUE_APP_BASELINK;
    },
    cancelar() {
      axios
        .post(
          process.env.VUE_APP_BASELINK + "/api/user/removerContrato",
          {},
          { headers: { token: localStorage.getItem("token") } }
        )

        .then((response) => {
          console.log(response);
          this.$router.go();
        });
    },
    submit() {
      //var cod_treino = this.$route.params.codigo;
      console.log(this.rating);
      console.log(this.comentario);
      var post_body = {
        username: localStorage.getItem("username"),
        treinador: this.contrato.treinador,
        avaliacao: this.rating,
        comentario: this.comentario,
      };

      axios
        .post(
          process.env.VUE_APP_BASELINK + "/api/treinador/avaliacao",
          post_body,
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response) => {
          console.log(JSON.stringify(response.data));
        });

      this.treino.avaliacoes.push({
        username: localStorage.getItem("username"),
        avaliacao: this.rating,
        comentario: this.comentario,
      });
      this.$router.go();
    },
  },
};
</script>

<style>
.treinadores {
  background-color: #d3d3d2;
}
</style>