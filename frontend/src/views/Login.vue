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
                  <h1 class="titulo text-center" style="color: #f95738">
                    Entre no Fitness Stack
                  </h1>
                  <v-form ref="login_form" class="px-16 mx-8 mt-12">
                    <v-text-field
                      label="Nome de Utilizador"
                      name="Username"
                      v-model="input.username"
                      prepend-icon="mdi-account"
                      type="text"
                      color="#f95738"
                      :rules="usernameLoginRules"
                      required
                    />
                    <v-text-field
                      id="password"
                      label="Palavra-Passe"
                      name="password"
                      v-model="input.password"
                      prepend-icon="mdi-lock"
                      type="password"
                      color="#f95738"
                      :rules="passwordLoginRules"
                      required
                    />
                    <v-checkbox
                      v-model="modo_login"
                      label="É Treinador?"
                      color="#7189ff"
                    />

                    <div class="text-center my-6">
                      <v-btn v-on:click="validate_login()" color="#f95738" dark
                        >Login</v-btn
                      >
                    </div>
                    <v-alert
                      border="left"
                      v-if="loginAuthError"
                      text
                      dismissible
                      elevation="2"
                      type="warning"
                    >
                      Credenciais inválidas
                    </v-alert>
                    <v-alert
                      border="left"
                      v-if="loginServerError"
                      text
                      dismissible
                      elevation="2"
                      type="error"
                    >
                      Erro do servidor
                    </v-alert>
                  </v-form>
                </v-card-text>
              </v-col>
              <v-col cols="12" md="4" style="background-color: #f95738">
                <v-card-text class="white--text mt-16">
                  <h1 class="text-center">Ainda não se registou?</h1>
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
                  <h1 class="text-center">Já se registou?</h1>
                  <h5 class="text-center my-3">
                    Realize o login para se manter conectado!
                  </h5>
                </v-card-text>
                <div class="text-center mb-10" style="padding-bottom: 600px">
                  <v-btn outlined dark @click="step--">Login</v-btn>
                </div>
              </v-col>

              <v-col cols="12" md="8">
                <v-card-text class="mt-12">
                  <h1 class="titulo text-center" style="color: #f95738">
                    Nova Conta
                  </h1>
                  <v-form ref="register_form" class="px-16 mx-8 mt-6">
                    <v-text-field
                      label="Nome Completo"
                      name="Name"
                      v-model="input_register.nome"
                      prepend-icon="mdi-account"
                      :rules="nameRules"
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
                          label="Data de Nascimento"
                          prepend-icon="mdi-calendar"
                          readonly
                          hint="Deve ter no mínimo 12 anos"
                          persistent-hint
                          v-bind="attrs"
                          v-on="on"
                          color="#f95738"
                          required
                        ></v-text-field>
                      </template>
                      <v-date-picker
                        v-model="input_register.date"
                        no-title
                        :rules="birthdateRules"
                        :max="getMaxDate"
                        scrollable
                        color="#7189ff"
                      >
                        <v-spacer></v-spacer>
                        <v-btn text color="#7189ff" @click="menu = false">
                          Cancelar
                        </v-btn>
                        <v-btn
                          text
                          color="#7189ff"
                          @click="$refs.menu.save(input_register.date)"
                        >
                          OK
                        </v-btn>
                      </v-date-picker>
                    </v-menu>
                    <v-text-field
                      label="Email"
                      name="Email"
                      v-model="input_register.email"
                      prepend-icon="mdi-email"
                      :rules="emailRules"
                      type="text"
                      color="#f95738"
                      required
                    />
                    <v-text-field
                      label="Nome de Utilizador"
                      name="Username"
                      v-model="input_register.username"
                      prepend-icon="mdi-account-lock"
                      :rules="usernameRules"
                      type="text"
                      color="#f95738"
                      required
                    />
                    <v-text-field
                      id="password"
                      label="Palavra-Passe"
                      name="password"
                      v-model="input_register.password"
                      prepend-icon="mdi-lock"
                      :rules="passwordRules"
                      counter
                      type="password"
                      color="#f95738"
                      required
                    />

                    <v-select
                      :items="['Masculino', 'Feminino']"
                      v-model="input_register.genero"
                      prepend-icon="mdi-gender-male-female"
                      label="Género"
                      color="#f95738"
                      required
                    />

                    <v-file-input
                      id="imagem"
                      v-model="input_register.imagem"
                      label="Imagem de Perfil"
                      :rules="avatarRules"
                      color="#f95738"
                      name="imagem_perfil"
                      prepend-icon="mdi-camera"
                    />
                    <div class="text-center my-8">
                      <v-dialog v-model="dialog" persistent max-width="600px">
                        <template v-slot:activator="{ on, attrs }">
                          <v-btn
                            color="#7189ff"
                            class="ma-2 white--text"
                            v-bind="attrs"
                            v-on="on"
                          >
                            Completar Perfil
                            <v-icon right dark> mdi-clipboard-plus </v-icon>
                          </v-btn>
                        </template>
                        <v-card>
                          <v-card-title>
                            <span class="headline"
                              >Complete o seu perfil...</span
                            >
                          </v-card-title>
                          <v-card-text>
                            <v-form ref="form">
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
                                        :max="maxPeso"
                                        :min="minPeso"
                                        :rules="pesoRules"
                                        color="#7189ff"
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
                                        :rules="alturaRules"
                                        :max="maxAltura"
                                        :min="minAltura"
                                        type="number"
                                        style="width: 60px"
                                        color="#7189ff"
                                      ></v-text-field>
                                    </template>
                                  </v-slider>
                                </v-col>
                              </v-row>
                            </v-form>
                          </v-card-text>
                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="#7189ff" text @click="validate_form()">
                              OK
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                      <v-btn
                        color="#f95738"
                        dark
                        v-on:click="validate_register()"
                        >Registar</v-btn
                      >
                    </div>
                    <v-alert
                      border="left"
                      v-if="registerAuthError"
                      text
                      dismissible
                      elevation="2"
                      type="warning"
                    >
                      Email e/ou Nome de Utilizador já existem
                    </v-alert>
                    <v-alert
                      border="left"
                      v-if="registerServerError"
                      text
                      dismissible
                      elevation="2"
                      type="error"
                    >
                      Erro do servidor
                    </v-alert>
                  </v-form>
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
      date: "",
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
    date: new Date(),
    usernameLoginRules: [(v) => !!v || "Deve introduzir um nome de utilizador"],
    passwordLoginRules: [(v) => !!v || "Deve introduzir uma palavra-passe"],
    loginAuthError: false,
    loginServerError: false,
    nameRules: [(v) => !!v || "Campo obrigatório"],
    birthdateRules: [
      (v) =>
        Math.abs(new Date().getFullYear() - new Date(v).getFullYear()) > 12 ||
        "Data de nascimento inválida",
    ],
    emailRules: [
      (v) => !!v || "Campo obrigatório",
      (v) => /.+@.+/.test(v) || "Email inválido",
    ],
    usernameRules: [
      (v) => !!v || "Campo obrigatório",
      (v) => /^[a-zA-Z0-9.-]{2,}$/.test(v) || "Nome de utilizador inválido",
    ],
    passwordRules: [
      (v) => !!v || "Campo obrigatório",
      (v) =>
        /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/.test(v) ||
        "A palavra-passe deve ter no mínimo 8 letras e pelo menos 1 minúscula e 1 maiúscula",
    ],
    avatarRules: [
      (value) =>
        !value ||
        value.size < 5000000 ||
        "O tamanho da imagem deve ser inferior a 5 MB",
    ],
    registerAuthError: false,
    registerServerError: false,
    pesoRules: [
      (v) => v >= 30 && v <= 200 || "30 a 200",
    ],
    alturaRules: [
      (v) => v >= 100 && v <= 250 || "100 a 250"
    ]
  }),
  methods: {
    validate_login() {
      if (this.$refs.login_form.validate()) {
        this.login();
      }
    },
    validate_register() {
      if (this.$refs.register_form.validate()) {
        this.registar();
      }
    },
    validate_form() {
      if(this.$refs.form.validate()) {
        this.dialog = false;
        }
    },
    login() {
      console.log(process.env.VUE_APP_BASELINK + "/api/login/user");
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
            .post(process.env.VUE_APP_BASELINK + "/api/login/user", loginInfo)
            .then((response) => {
              const status = JSON.parse(response.status);

              if (status == "200") {
                localStorage.setItem("token", response.data.token);
                localStorage.setItem("username", this.input.username);
                localStorage.setItem("usertype", 0);
                this.$router.push("/perfil");
              }
            })
            .catch((error) => {
              if (error.response != null) {
                if (error.response.status == "401") {
                  this.loginAuthError = true;
                  setTimeout(() => {
                    this.loginAuthError = false;
                  }, 5000);
                }
              } else {
                this.loginServerError = true;
                setTimeout(() => {
                  this.loginServerError = false;
                }, 5000);
              }
            })
        } else {
          console.log("A username and password must be present");
        }
      } else {
        //treinador
        if (this.input.username != "" && this.input.password != "") {
          var loginInfoTreinador = {
            username: this.input.username,
            password: sjcl.codec.hex.fromBits(
              sjcl.hash.sha256.hash(this.input.password)
            ),
          };
          axios
            .post(
              process.env.VUE_APP_BASELINK + "/api/login/treinador",
              loginInfoTreinador
            )
            .then((response) => {
              const status = JSON.parse(response.status);

              if (status == "200") {
                localStorage.setItem("token", response.data.token);
                localStorage.setItem("username", this.input.username);
                localStorage.setItem("usertype", 1);
                this.$router.push("/treinador/perfil");
              }
            })
            .catch((error) => {
              if (error.response != null) {
                if (error.response.status == "401") {
                  this.loginAuthError = true;
                  setTimeout(() => {
                    this.loginAuthError = false;
                  }, 5000);
                }
              } else {
                this.loginServerError = true;
                setTimeout(() => {
                  this.loginServerError = false;
                }, 5000);
              }
            })
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
        .post(process.env.VUE_APP_BASELINK + "/api/register/user", registoInfo)
        .then((response) => {
          const status = JSON.parse(response.status);
          if (status == "200") {
            var login_info = {
              username: registoInfo.username,
              password: registoInfo.password,
            };
            axios
              .post(
                process.env.VUE_APP_BASELINK + "/api/login/user",
                login_info
              )
              .then((response2) => {
                const status2 = JSON.parse(response2.status);
                if (status2 == "200") {
                  localStorage.setItem("token", response2.data.token);
                  localStorage.setItem("username", registoInfo.username);
                  localStorage.setItem("usertype", 0);
                  this.$router.push("/perfil");
                }
              });
          }
        })
        .catch((error) => {
          if (error.response != null) {
            if (error.response.status == "401") {
              this.registerAuthError = true;
              setTimeout(() => {
                this.registerAuthError = false;
              }, 5000);
            }
          } else {
            this.registerServerError = true;
            setTimeout(() => {
              this.registerServerError = false;
            }, 5000);
          }
        });
    },
  },
  computed: {
    getMaxDate() {
      var maxDate = new Date(
        this.date.getFullYear() - 12,
        this.date.getMonth(),
        this.date.getDay()
      );
      return maxDate.toISOString().substr(0, 10);
    },
  },
};
</script>
