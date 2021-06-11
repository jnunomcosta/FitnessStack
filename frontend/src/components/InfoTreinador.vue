<template>
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
            <v-card-title
              >Joao Ratão<v-icon dense color="#f95738" class="ml-2"
                >mdi-weight-lifter</v-icon
              ></v-card-title
            >
            <v-card-subtitle>joniratatui</v-card-subtitle>

            <v-card-text>
              <v-row align="center" class="mx-0">
                <div class="body-2">joniratatui@gmail.com</div>
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
                Olá sou o João e tenho 30 anos. Sou licenciado em Desporto e...
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
                    <v-btn color="#f95738" text @click="dialog2 = false">
                      Solicitar contrato
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
export default {
  name: "InfoExercicio",
  data() {
    return {
      dialog: false,
      dialog0: false,
      dialog1: false,
      dialog2: false,
      
    };
  },
};
</script>