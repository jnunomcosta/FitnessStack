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
              <v-form class="px-16 mx-8 mt-10">
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
              </v-form>

              <div class="text-center my-8">
                <v-btn v-on:click="login()" color="#f95738" dark
                      >Login</v-btn
                    >
              </div>
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
  }),
  methods: {
    login() {
        var loginInfoAdmin = {
          username: this.input.username,
          password: this.input.password,
          //password: sjcl.codec.hex.fromBits(sjcl.hash.sha256.hash(this.input.password)),
        };
        console.log(loginInfoAdmin);
        axios
          .post("http://localhost:4576/api/login/administrador", loginInfoAdmin)
          .then((response) => {

            const status = JSON.parse(response.status);
            
            if (status == "200") {
             //localStorage.setItem("token", response.data.token);
              localStorage.setItem("username", this.input.username);
              this.$router.push("/administrador/perfil");
            }

            //TALVEZ TER UM MAXIMO DE TENTATIVAS DE LOGIN IDK
            
            //MOSTRAR UM AVISO DE QUE A PALAVRA PASSE ESTA ERRADA
          })
    },
  },
};
</script>
