<template>
  <div class="treinadores">
    <NavBar />
    <v-row style="margin-top: 40px">
      <v-col cols="12" md="1">
        <SideBar />
      </v-col>
      <v-col cols="12" md="10">

        <v-dialog v-model="dialog" persistent max-width="700">
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              style="margin-top: 90px"
              class="mx-5"
              height="47"
              color="#f95738"
              dark
              v-bind="attrs"
              v-on="on"
            >
              O meu treinador
            </v-btn>
          </template>

          <v-card height="37vh" style="text-align: center">
            <v-row>
              <v-col cols="12" md="4">
                <!--<v-card-title class="justify-center"> Nome do treinador </v-card-title> -->
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
                  <div>
                    <v-rating
                      v-model="rating"
                      color="yellow darken-3"
                      background-color="grey darken-1"
                      empty-icon="$ratingFull"
                      half-increments
                      hover
                    ></v-rating>
                  </div>
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
                  Olá sou o João e tenho 30 anos. Sou licenciado em Desporto e
                  ...
                </p>

                <v-spacer></v-spacer>
                <div style="margin-top: 200px">
                  <v-btn color="#f95738" text @click="dialog4 = false">
                    Sair
                  </v-btn>
                  <v-btn color="#f95738" text @click="dialog4 = false">
                    Cancelar contrato
                  </v-btn>
                </div>
              </v-col>
            </v-row>
          </v-card>
        </v-dialog>

        <v-container class="pa-0 ma-0">
          <v-row align="center" justify="space-around">
            <v-col cols="12" md="12">
              <v-toolbar dense rounded class="mx-6" style="margin-top: 90px">
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
import NavBar from "@/components/NavBar_User.vue";
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