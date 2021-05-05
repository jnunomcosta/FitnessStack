<template>
  <v-app>
    <div class="login">
      <NavBar />
      <v-content>
        <v-window v-model="step">
          <v-window-item :value="1">
            <v-row class="mt-10">
              <v-col cols="12" md="8">
                <v-card-text class="mt-16 pt-10" style="margin-bottom: 180px">
                  <h1 class="text-center display-2" style="color: #f95738">
                    Entre no Fitness Stack
                  </h1>
                  <div class="text-center mt-4">
                    <v-btn class="mx-2" fab color="black" outlined>
                      <v-icon large>mdi-google-plus</v-icon>
                    </v-btn>
                  </div>
                  <v-form class="px-16 mx-8">
                    <v-text-field
                      label="Nome de Utilizador"
                      name="Username"
                      prepend-icon="mdi-account"
                      type="text"
                      color="#f95738"
                    />
                    <v-text-field
                      id="password"
                      label="Palavra-Passe"
                      name="password"
                      prepend-icon="mdi-lock"
                      type="password"
                      color="#f95738"
                    />
                  </v-form>
                  <!--<h3 class="text-center mt-4">
                    Esqueceu-se da password? <a href="#">Clique aqui</a>
                  </h3>-->
                  <div class="text-center my-10">
                    <v-btn rounded color="#f95738" dark>Login</v-btn>
                  </div>
                </v-card-text>
              </v-col>
              <v-col cols="12" md="4" style="background-color: #f95738">
                <v-card-text class="white--text mt-16">
                  <h1 class="text-center display-1">Ainda não se registou?</h1>
                  <h5 class="text-center my-3">
                    Registe-se já e comece a treinar!
                  </h5>
                </v-card-text>
                <div class="text-center mb-10">
                  <v-btn rounded outlined dark @click="step++">Registar</v-btn>
                </div>
              </v-col>
            </v-row>
          </v-window-item>
          <v-window-item :value="2">
            <v-row class="mt-10">
              <v-col cols="12" md="4" style="background-color: #f95738">
                <v-card-text class="white--text mt-16">
                  <h1 class="text-center display-1">Já se registou?</h1>
                  <h5 class="text-center my-3">
                    Realize o login para se manter conectado!
                  </h5>
                </v-card-text>
                <div class="text-center mb-10">
                  <v-btn rounded outlined dark @click="step--">Login</v-btn>
                </div>
              </v-col>

              <v-col cols="12" md="8">
                <v-card-text class="mt-12">
                  <h1 class="text-center display-2" style="color: #f95738">
                    Criar Conta
                  </h1>
                  <div class="text-center mt-4">
                    <v-btn class="mx-2" fab color="black" outlined>
                      <v-icon large>mdi-google-plus</v-icon>
                    </v-btn>
                  </div>
                  <v-form class="px-16 mx-8">
                    <v-text-field
                      label="Nome Completo*"
                      name="Name"
                      prepend-icon="mdi-account"
                      type="text"
                      color="#f95738"
                      required
                    />
                    <v-menu
                      ref="menu"
                      v-model="menu"
                      :close-on-content-click="false"
                      :return-value.sync="date"
                      transition="scale-transition"
                      offset-y
                      min-width="auto"
                    >
                      <template v-slot:activator="{ on, attrs }">
                        <v-text-field
                          v-model="date"
                          label="Data de Nascimento*"
                          prepend-icon="mdi-calendar"
                          readonly
                          v-bind="attrs"
                          v-on="on"
                          color="#f95738"
                          required
                        ></v-text-field>
                      </template>
                      <v-date-picker
                        v-model="date"
                        no-title
                        scrollable
                        color="#7189ff"
                      >
                        <v-spacer></v-spacer>
                        <v-btn text color="#f95738" @click="menu = false">
                          Cancelar
                        </v-btn>
                        <v-btn
                          text
                          color="#f95738"
                          @click="$refs.menu.save(date)"
                        >
                          OK
                        </v-btn>
                      </v-date-picker>
                    </v-menu>
                    <v-text-field
                      label="Email*"
                      name="Email"
                      prepend-icon="mdi-email"
                      type="text"
                      color="#f95738"
                      required
                    />
                    <v-text-field
                      label="Nome de Utilizador*"
                      name="Username"
                      prepend-icon="mdi-account-lock"
                      type="text"
                      color="#f95738"
                      required
                    />
                    <v-text-field
                      id="password"
                      label="Palavra-Passe*"
                      name="password"
                      prepend-icon="mdi-lock"
                      type="password"
                      color="#f95738"
                      required
                    />
                    <small>* campo obrigatório</small>
                  </v-form>

                  <v-dialog v-model="dialog" persistent max-width="600px">
                    <template v-slot:activator="{ on, attrs }">
                      <div class="text-center my-8">
                        <v-btn
                          rounded
                          color="#f95738"
                          dark
                          v-bind="attrs"
                          v-on="on"
                          >Registar</v-btn
                        >
                      </div>
                    </template>
                    <v-card>
                      <v-card-title>
                        <span class="headline">Complete o seu perfil...</span>
                      </v-card-title>
                      <v-card-text>
                        <v-container>
                          <v-row>
                            <v-col cols="12">
                              <v-subheader>Peso (kg)</v-subheader>
                              <v-slider
                                v-model="sliderPeso"
                                class="align-center"
                                :max="maxPeso"
                                :min="minPeso"
                                hide-details
                                color="#7189ff"
                              >
                                <template v-slot:append>
                                  <v-text-field
                                    v-model="sliderPeso"
                                    class="mt-0 pt-0"
                                    hide-details
                                    single-line
                                    type="number"
                                    style="width: 60px"
                                  ></v-text-field>
                                </template>
                              </v-slider>
                            </v-col>

                            <v-col cols="12">
                              <v-subheader>Altura (cm)</v-subheader>
                              <v-slider
                                v-model="sliderAltura"
                                class="align-center"
                                :max="maxAltura"
                                :min="minAltura"
                                hide-details
                                color="#7189ff"
                              >
                                <template v-slot:append>
                                  <v-text-field
                                    v-model="sliderAltura"
                                    class="mt-0 pt-0"
                                    hide-details
                                    single-line
                                    type="number"
                                    style="width: 60px"
                                  ></v-text-field>
                                </template>
                              </v-slider>
                            </v-col>

                            <v-col cols="12" center>
                              <v-select
                                :items="['Masculino', 'Feminino']"
                                label="Género"
                                color="#7189ff"
                                required
                              ></v-select>
                            </v-col>
                          </v-row>
                        </v-container>
                      </v-card-text>
                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn
                          color="#7189ff"
                          text
                          @click="dialog = false"
                          :href="'/'"
                        >
                          Registar
                        </v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                </v-card-text>
              </v-col>
            </v-row>
          </v-window-item>
        </v-window>
      </v-content>
      <Footer />
    </div>
  </v-app>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import Footer from "@/components/Footer.vue";

export default {
  name: "Login",
  components: {
    NavBar,
    Footer,
  },
  created() {
    document.title = "Fitness Stack";
  },
  data: () => ({
    step: 1,
    date: new Date().toISOString().substr(0, 10),
    menu: false,
    dialog: false,
    minPeso: 30,
    maxPeso: 200,
    sliderPeso: 70,
    minAltura: 100,
    maxAltura: 250,
    sliderAltura: 170,
  }),
};
</script>
