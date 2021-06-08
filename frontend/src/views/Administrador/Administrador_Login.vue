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
                <!-- <v-btn v-on:click="login()" color="#f95738" dark
                      >Login</v-btn
                    > -->
                <v-btn color="#f95738" dark>Login</v-btn>
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
      <Footer />
    </div>
  </v-app>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import Footer from "@/components/Footer.vue";
//import axios from "axios";

export default {
  name: "Login Admin",
  components: {
    NavBar,
    Footer,
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
    /*login() {
      if(this.modo_login == false && this.modo_login_admin==false){ //utilizador
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
      } else {
        console.log("A username and password must be present");
      }
      }
      else{
        if (this.modo_login_admin==false) {//treinador
        if (this.input.username != "" && this.input.password != "") {
        var loginInfoTreinador = {
          username: this.input.username,
          password: this.input.password,
          //password: sjcl.codec.hex.fromBits(sjcl.hash.sha256.hash(this.input.password)),
        };
        axios
          .post("http://localhost:4576/api/login/treinador", loginInfoTreinador)
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
      } else {
        console.log("A username and password must be present");
      }
      }
      }

      if(this.modo_login_admin == true){
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
      }
    },
    async registar() {
      function carrega_foto(x) {
        return new Promise((resolve) => {
          let blob = new Blob([x]),fileReader = new FileReader();
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
        password: sjcl.codec.hex.fromBits(sjcl.hash.sha256.hash(this.input_register.password)),
        username: this.input_register.username,
        nome: this.input_register.nome,
        data: this.input_register.date,
        peso: this.input_register.peso,
        altura: this.input_register.altura,
        genero: genero_registo,
      };
      registoInfo.foto_perfil = await carrega_foto(this.input_register.imagem);

      axios
          .post('http://localhost:4576/api/register/user',registoInfo)
          .then(response => {
            const status = JSON.parse(response.status);
            if (status == '200'){
                 var login_info = {
                  username: registoInfo.username,
                  password: registoInfo.password
                } 
                axios
                    .post('http://localhost:4576/api/login/user',login_info)
                    .then(response2 => {
                      const status2 = JSON.parse(response2.status);
                      if(status2 == '200'){
                        localStorage.setItem('token', response2.data.token);
                        localStorage.setItem('username', registoInfo.username);
                        this.$router.push('/perfil');
                      }
                    }) 
            }
          })
    },*/
  },
};
</script>
