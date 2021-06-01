<template>
  <div class="treinadores">
    <NavBar />
    <v-row style="margin-top: 90px">
      <v-col cols="12" md="1">
        <SideBar />
      </v-col>
      <v-col cols="12" md="10">
        <v-dialog transition="dialog-bottom-transition" max-width="700">
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              class="mx-6"
              v-bind="attrs"
              v-on="on"
              color="#f95738"
              dark>
              O meu treinador
            </v-btn>
          </template>
          <template v-slot:default="dialog">
            <v-card>
              <v-toolbar color="#f95738" dark
                ><h3>João Ratão</h3>
                <v-spacer></v-spacer>
                <v-btn icon @click="dialog.value = false"
                  ><v-icon>mdi-close</v-icon></v-btn
                >
              </v-toolbar>

              <div class="d-flex flex-no-wrap justify-space-between">
                <div>
                  <v-avatar class="ma-3" width="250" height="300" tile>
                    <v-img
                      src="https://randomuser.me/api/portraits/men/93.jpg"
                    ></v-img>
                  </v-avatar>
                </div>
                <div class="ma-2">
                  <v-card-title>Joao Ratão
                    <v-icon dense color="#f95738" class="ml-2">mdi-weight-lifter</v-icon>
                  </v-card-title>
                  <v-card-subtitle>joniratatui</v-card-subtitle>

                  <v-card-text>
                    <v-row align="center" class="mx-0">
                      <div class="body-2">joniratatui@gmail.com</div>
                      <v-spacer></v-spacer>
                    </v-row>
                    <div style="margin-top: 14px">
                      <v-rating
                        v-model="rating"
                        color="yellow darken-3"
                        background-color="grey darken-1"
                        empty-icon="$ratingFull"
                        half-increments
                        hover
                      ></v-rating>
                      <v-spacer></v-spacer>
                    </div>

                    <v-divider></v-divider>
                    <div class="my-4">
                      Olá sou o João e tenho 30 anos. Sou licenciado em Desporto
                      e...
                    </div>
                  </v-card-text>
                </div>
              </div>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="#f95738" text @click="dialog.value = false">
                  Cancelar contrato
                </v-btn>
              </v-card-actions>
            </v-card>
          </template>
        </v-dialog>

        <v-container class="pa-0 ma-0">
          <v-row align="center" justify="space-around">
            <v-col cols="12" md="12">
              <v-toolbar dense rounded class="mx-6" style="margin-top: 40px">
                <v-text-field
                  hide-details
                  prepend-icon="mdi-magnify"
                  label="Procurar treinador"
                  single-line
                  color="#f95738"
                ></v-text-field>
                <v-btn icon>
                  <v-icon>mdi-filter</v-icon>
                </v-btn>
              </v-toolbar>
            </v-col>
          </v-row>
          <Treinador
        /></v-container>
      </v-col>
    </v-row>
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_User.vue";
import Treinador from "@/components/Treinador.vue";

export default {
  name: "Treinadores",
  components: {
    NavBar,
    SideBar,
    Treinador,
  },
  created() {
    document.title = "Treinadores";
  },
};
</script>

<style>
.treinadores {
  background-color: #f4f5f5;
}
</style>