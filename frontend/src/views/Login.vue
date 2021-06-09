<template>
  <v-app>
    <div class="login">
      <NavBar />
      <v-content>
        <v-window v-model="step">
          <v-window-item :value="1">
            <v-row class="mt-10">
              <v-col cols="12" md="8">
                <v-card-text class="mt-16 pt-8" style="margin-bottom: 180px">
                  <h1 class="text-center display-2" style="color: #f95738">
                    Entre no Fitness Stack
                  </h1>
                  <v-form class="px-16 mx-8 mt-6">
                    <v-text-field
                      label="Nome de Utilizador"
                      name="Username"
                      v-model="input.username"
                      prepend-icon="mdi-account"
                      type="text"
                      color="#f95738"
                    />
                    <v-text-field
                      id="password"
                      label="Palavra-Passe"
                      name="password"
                      v-model="input.password"
                      prepend-icon="mdi-lock"
                      type="password"
                      color="#f95738"
                    />
                    <v-checkbox v-model="modo_login" label="É Treinador?" />
                  </v-form>
                  
                  <div class="text-center my-6">
                    <v-btn v-on:click="login()" color="#f95738" dark
                      >Login</v-btn
                    >
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
                  <v-btn outlined dark @click="step++">Registar</v-btn>
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
                  <v-btn outlined dark @click="step--">Login</v-btn>
                </div>
              </v-col>

              <v-col cols="12" md="8">
                <v-card-text class="mt-12">
                  <h1 class="text-center display-2" style="color: #f95738">
                    Criar Conta
                  </h1>
                  <v-form class="px-16 mx-8 mt-6">
                    <v-text-field
                      label="Nome Completo*"
                      name="Name"
                      v-model="input_register.nome"
                      prepend-icon="mdi-account"
                      type="text"
                      color="#f95738"
                      required
                    />
                    <v-menu
                      ref="menu"
                      v-model="menu"
                      :close-on-content-click="false"
                      transition="scale-transition"
                      offset-y
                      min-width="auto"
                    >
                      <template v-slot:activator="{ on, attrs }">
                        <v-text-field
                          v-model="input_register.date"
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
                        v-model="input_register.date"
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
                          @click="$refs.menu.save(input_register.date)"
                        >
                          OK
                        </v-btn>
                      </v-date-picker>
                    </v-menu>
                    <v-text-field
                      label="Email*"
                      name="Email"
                      v-model="input_register.email"
                      prepend-icon="mdi-email"
                      type="text"
                      color="#f95738"
                      required
                    />
                    <v-text-field
                      label="Nome de Utilizador*"
                      name="Username"
                      v-model="input_register.username"
                      prepend-icon="mdi-account-lock"
                      type="text"
                      color="#f95738"
                      required
                    />
                    <v-text-field
                      id="password"
                      label="Palavra-Passe*"
                      name="password"
                      v-model="input_register.password"
                      prepend-icon="mdi-lock"
                      type="password"
                      color="#f95738"
                      required
                    />
                    <v-file-input
                      id="imagem"
                      v-model="input_register.imagem"
                      label="Imagem de Perfil"
                      name="imagem_perfil"
                      prepend-icon="mdi-camera"
                    />
                    <small>* campo obrigatório</small>
                  </v-form>
                  <v-dialog v-model="dialog" persistent max-width="600px">
                    <template v-slot:activator="{ on, attrs }">
                      <div class="text-center my-8">
                        <v-btn color="#f95738" dark v-bind="attrs" v-on="on"
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
                                v-model="input_register.peso"
                                class="align-center"
                                :max="maxPeso"
                                :min="minPeso"
                                hide-details
                                color="#7189ff"
                              >
                                <template v-slot:append>
                                  <v-text-field
                                    v-model="input_register.peso"
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
                                v-model="input_register.altura"
                                class="align-center"
                                :max="maxAltura"
                                :min="minAltura"
                                hide-details
                                color="#7189ff"
                              >
                                <template v-slot:append>
                                  <v-text-field
                                    v-model="input_register.altura"
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
                                v-model="input_register.genero"
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
                        <v-btn color="#7189ff" text v-on:click="registar()">
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
    </div>
  </v-app>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import axios from "axios";
import sjcl from "sjcl";

export default {
  name: "Login",
  components: {
    NavBar,
  },
  created() {
    document.title = "Fitness Stack";
  },
  data: () => ({
    input: {
      username: "",
      password: "",
    },
    modo_login: false,
    input_register: {
      username: "",
      password: "",
      email: "",
      nome: "",
      idade: 2,
      peso: 70,
      altura: 170,
      genero: "",
      foto_perfil: "",
    },
    step: 1,
    menu: false,
    dialog: false,
    minPeso: 30,
    maxPeso: 200,
    minAltura: 100,
    maxAltura: 250,
  }),
  methods: {
    login() {
      if (this.modo_login == false) {
        //utilizador
        if (this.input.username != "" && this.input.password != "") {
          var loginInfo = {
            username: this.input.username,
            password: sjcl.codec.hex.fromBits(
              sjcl.hash.sha256.hash(this.input.password)
            ),
          };
          axios
            .post("http://localhost:4576/api/login/user", loginInfo)
            .then((response) => {
              const status = JSON.parse(response.status);

              if (status == "200") {
                localStorage.setItem("token", response.data.token);
                localStorage.setItem("username", this.input.username);
                this.$router.push("/perfil");
              }

              //TALVEZ TER UM MAXIMO DE TENTATIVAS DE LOGIN IDK

              //MOSTRAR UM AVISO DE QUE A PALAVRA PASSE ESTA ERRADA
            })
            .finally(() => (this.loading = false));
        } else {
          console.log("A username and password must be present");
        }
      } else {
        //treinador
        if (this.input.username != "" && this.input.password != "") {
          var loginInfoTreinador = {
            username: this.input.username,
            password: this.input.password,
            //password: sjcl.codec.hex.fromBits(sjcl.hash.sha256.hash(this.input.password)),
          };
          axios
            .post(
              "http://localhost:4576/api/login/treinador",
              loginInfoTreinador
            )
            .then((response) => {
              const status = JSON.parse(response.status);

              if (status == "200") {
                localStorage.setItem("token", response.data.token);
                localStorage.setItem("username", this.input.username);
                this.$router.push("/treinador/perfil");
              }

              //TALVEZ TER UM MAXIMO DE TENTATIVAS DE LOGIN IDK

              //MOSTRAR UM AVISO DE QUE A PALAVRA PASSE ESTA ERRADA
            })
            .finally(() => (this.loading = false));
        } else {
          console.log("A username and password must be present");
        }
      }
    },
    async registar() {
      function carrega_foto(x) {
        return new Promise((resolve) => {
          let blob = new Blob([x]),
            fileReader = new FileReader();
          fileReader.readAsArrayBuffer(blob);
          fileReader.onload = function () {
            resolve(Buffer.from(this.result).toString("base64"));
          };
        });
      }

      //FALTA VERIFICAR SE O EMAIL E A PASS SAO CORRETOS OU EXISTEM OU QQ COISA ASSIM

      let genero_registo = true;
      if (this.input_register.genero != "Masculino") {
        genero_registo = false;
      }
      var registoInfo = {
        email: this.input_register.email,
        password: sjcl.codec.hex.fromBits(
          sjcl.hash.sha256.hash(this.input_register.password)
        ),
        username: this.input_register.username,
        nome: this.input_register.nome,
        data: this.input_register.date,
        peso: this.input_register.peso,
        altura: this.input_register.altura,
        genero: genero_registo,
      };
      registoInfo.foto_perfil = await carrega_foto(this.input_register.imagem);

      axios
        .post("http://localhost:4576/api/register/user", registoInfo)
        .then((response) => {
          const status = JSON.parse(response.status);
          if (status == "200") {
            var login_info = {
              username: registoInfo.username,
              password: registoInfo.password,
            };
            axios
              .post("http://localhost:4576/api/login/user", login_info)
              .then((response2) => {
                const status2 = JSON.parse(response2.status);
                if (status2 == "200") {
                  localStorage.setItem("token", response2.data.token);
                  localStorage.setItem("username", registoInfo.username);
                  this.$router.push("/perfil");
                }
              });
          }
        });
    },
  },
};
</script>
