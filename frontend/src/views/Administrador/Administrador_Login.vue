<template>
  <v-app>
    <div class="admin_login">
      <NavBar />

      <v-content>
        <v-row class="mt-10">
          <v-col cols="12" md="8">
            <v-card-text class="mt-16 pt-10" style="margin-bottom: 180px">
              <h1 class="text-center display-2" style="color: #f95738">
                Entre no Fitness Stack
              </h1>
              <v-form ref="login_form" class="px-16 mx-8 mt-10">
                <v-text-field
                  label="Nome de Utilizador"
                  name="Username"
                  v-model="input.username"
                  prepend-icon="mdi-account"
                  type="text"
                  color="#f95738"
                  :rules="usernameLoginRules"
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
                />

                <div class="text-center my-8">
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
              <h1 class="text-center display-1">É administrador?</h1>
              <h5 class="text-center my-3">Realize aqui o seu login!</h5>
            </v-card-text>
          </v-col>
        </v-row>
      </v-content>
    </div>
  </v-app>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import axios from "axios";

export default {
  name: "Login Admin",
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
    usernameLoginRules: [(v) => !!v || "Deve introduzir um nome de utilizador"],
    passwordLoginRules: [(v) => !!v || "Deve introduzir uma palavra-passe"],
    loginAuthError: false,
    loginServerError: false,
  }),
  methods: {
    validate_login() {
      if (this.$refs.login_form.validate()) {
        this.login();
      }
    },
    login() {
        var loginInfoAdmin = {
          username: this.input.username,
          password: this.input.password,
          //password: sjcl.codec.hex.fromBits(sjcl.hash.sha256.hash(this.input.password)),
        };
        console.log(loginInfoAdmin);
        axios
          .post(process.env.VUE_APP_BASELINK+'/api/login/administrador', loginInfoAdmin)
          .then((response) => {

          if (status == "200") {
            localStorage.setItem("token", response.data.token);
            localStorage.setItem("username", this.input.username);
            localStorage.setItem("usertype", 2);
            this.$router.push("/administrador/perfil");
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
        });
    },
  },
};
</script>
