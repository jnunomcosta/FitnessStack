<template>
  <v-dialog
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
        VER MAIS
      </v-btn>
    </template>
    <template v-slot:default="dialog">
      <v-card>
        <v-toolbar color="#f95738" dark
          ><h3>{{ utilizador.nome }}</h3>
          <v-spacer></v-spacer>
          <v-btn icon @click="dialog.value = false"
            ><v-icon>mdi-close</v-icon></v-btn
          >
        </v-toolbar>

        <row class="d-flex flex-no-wrap justify-space-between">
          <v-col cols="12" md="5">
            <v-avatar class="ma-3" width="250" height="300" tile>
              <v-img :src="linkfoto() + utilizador.foto_perfil"></v-img>
            </v-avatar>
          </v-col>
          <v-col cols="12" md="7">
            <v-card-title
              >{{ utilizador.nome
              }}</v-card-title>
            <v-card-subtitle>{{ utilizador.username }}</v-card-subtitle>

            <v-card-text >
              <div class="mb-4"><v-icon small class="mr-1">mdi-email</v-icon>{{ utilizador.email }}</div>

              <v-divider></v-divider>

              <div class="my-4">
                {{ data.comentario }}
              </div></v-card-text>
          </v-col>
        </row>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="#f95738" text @click="dialog.value = false">
            Recusar contrato
          </v-btn>
          <v-btn
            color="#f95738"
            text
            @click="
              accept();
              dialog.value = false
            "
          >
            Aceitar contrato
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>


<script>
import axios from "axios";
export default {
  name: "InfoAlunoSolicitacao",
  props: ["data"],
  data: () => ({
    dialog2: false,
    utilizador: null,
  }),
  mounted() {
    axios
      .get(
        process.env.VUE_APP_BASELINK +
          "/api/user/getUser?username=" +
          this.data.utilizador,
        { headers: { token: localStorage.getItem("token") } }
      )
      .then((response) => {
        this.utilizador = response.data;
        console.log(JSON.stringify(this.data));
        console.log(JSON.stringify(this.utilizador));
      })
      .finally(() => (this.loading = false));
  },
  methods: {
    linkfoto() {
      return process.env.VUE_APP_BASELINK;
    },
    accept() {
      axios
        .post(
          process.env.VUE_APP_BASELINK + "/api/treinador/aceitarContrato",
          { username: this.data.utilizador },
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response) => {
          console.log("sucesso");

          console.log(JSON.stringify(response.data));
          this.$router.go();
        })
    },
  },
};
</script>