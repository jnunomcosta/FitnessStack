<template>
  <div class="profile">
    <NavBar />
    <div>
      <SideBar />
      <v-row
        style="
          margin-left: 75px;
          padding-top: 100px;
          padding-bottom: 100px;
          margin-right: 25px;
        "
      >
        <v-col cols="12" md="3">
          <v-card style="text-align: center">
            <div class="mx-auto text-center">
              <v-avatar class="mt-4" size="150">
                <v-img
                  :src="linkfoto()+ utilizador.foto_perfil"
                ></v-img>
              </v-avatar>
            </div>
            <v-card-title class="justify-center">{{
              utilizador.nome
            }}</v-card-title>
            <v-card-subtitle v-model="username">
              {{ utilizador.username }}
            </v-card-subtitle>
            <v-divider class="mx-4"></v-divider>
            <div class="mt-4 body-2">
              <v-span>{{ utilizador.email }}</v-span>
            </div>
            <v-container class="justify-center">
              <v-dialog v-model="dialog1" persistent max-width="400px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="#f95738"
                    dark
                    small
                    class="mb-1"
                    v-bind="attrs"
                    v-on="on"
                  >
                    <v-icon dense> mdi-account-edit-outline </v-icon>
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span class="headline">Alterar nome de utilizador</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col cols="12" sm="6" md="12">
                          Digite o novo nome de utilizador.
                          <v-text-field
                            v-model="new_username"
                            color="#f95738"
                            prepend-icon="mdi-account-lock"
                            label="Nome de Utilizador"
                            required
                          ></v-text-field>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="#f95738" text @click="dialog1 = false">
                      Sair
                    </v-btn>
                    <v-btn
                      color="#f95738"
                      text
                      @click="
                        setUsername(new_username);
                        dialog1 = false;
                      "
                    >
                      Atualizar
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog v-model="dialog2" persistent max-width="400px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="#f95738"
                    dark
                    small
                    class="ml-1 mb-1"
                    v-bind="attrs"
                    v-on="on"
                  >
                    <v-icon dense> mdi-email-edit-outline </v-icon>
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span class="headline">Alterar email</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col cols="12" sm="6" md="12">
                          Digite o novo email.
                          <v-text-field
                            v-model="input_email"
                            color="#f95738"
                            prepend-icon="mdi-email"
                            label="Email"
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
                    <v-btn
                      color="#f95738"
                      text
                      @click="
                        setEmail(input_email);
                        dialog2 = false;
                      "
                    >
                      Atualizar
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog v-model="dialog3" persistent max-width="400px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="#f95738"
                    dark
                    class="ml-1 mb-1"
                    small
                    v-bind="attrs"
                    v-on="on"
                  >
                    <v-icon dense> mdi-lock-reset</v-icon>
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span class="headline">Alterar palavra-passe</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col cols="12" md="12">
                          Digite a palavra-passe antiga.
                          <v-text-field
                            type="password"
                            v-model="old_password"
                            color="#f95738"
                            prepend-icon="mdi-lock"
                            label="Palavra-Passe Antiga"
                            required
                          ></v-text-field>
                          Digite a palavra-passe nova.
                          <v-text-field
                            type="password"
                            v-model="new_password"
                            color="#f95738"
                            prepend-icon="mdi-lock-question"
                            label="Palavra-Passe Nova"
                            required
                          ></v-text-field>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="#f95738" text @click="dialog3 = false">
                      Sair
                    </v-btn>
                    <v-btn
                      color="#f95738"
                      text
                      @click="
                        setPassword(old_password, new_password);
                        dialog3 = false;
                      "
                    >
                      Atualizar
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog v-model="dialog4" persistent max-width="400px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="#f95738"
                    dark
                    class="ml-1 mb-1"
                    small
                    v-bind="attrs"
                    v-on="on"
                  >
                    <v-icon dense> mdi-camera</v-icon>
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span class="headline">Alterar foto de perfil</span>
                  </v-card-title>
                  
                  <v-card-text>
                    <v-container>
                      <v-file-input
                        id="imagem"
                        v-model="imagem_input"
                        label="Imagem de Perfil"
                        name="imagem_perfil"
                        prepend-icon="mdi-camera"
                      />
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="#f95738" text @click="dialog4 = false">
                      Sair
                    </v-btn>
                    <v-btn
                      color="#f95738"
                      text
                      @click="
                        setImagem(imagem_input);
                        dialog4 = false;
                      "
                    >
                      Atualizar
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-container>
          </v-card>
        </v-col>

        <v-col cols="12" md="4">
          <v-card>
            <v-card-title class="justify-center">
                      <span style="color:#f95738">Informação Física</span>
                    </v-card-title>
                    <v-divider></v-divider>
              <v-row class="mt-0">
                <v-col cols="12" md="6">
                   <v-card-text>
                  <p style="font-size:20px">
                    <b>Peso atual:</b> {{ utilizador.peso }}kg
                  </p>
                  <v-spacer class="mt-4"></v-spacer>
                  <p><b>Massa Gorda:</b> {{ utilizador.m_gorda }}%</p>

                  <p><b>Massa Muscular:</b> {{ utilizador.m_muscular }}%</p>

                  <p><b>Género:</b> {{ utilizador.genero }}</p>
                  </v-card-text>
                </v-col>

                <v-col cols="12" md="6">
                  <v-card-text>
                  <p><b>Altura:</b> {{ utilizador.altura }}cm</p>
                  <p>
                    <b>IMC:</b>
                    <v-chip
                      v-if="verifyIMC"
                      class="ma-2"
                      color="green"
                      text-color="white"
                    >
                      {{ imc }}
                    </v-chip>
                    <v-chip
                      v-else
                      class="ma-2"
                      color="red"
                      text-color="white"
                    >
                      {{ imc }}
                    </v-chip>
                  </p>
                  <p><b>IMC ideal:</b> 18.5 - 24.9</p>
                  </v-card-text>
                </v-col>
              </v-row>
              <div>
                <v-dialog v-model="dialog6" persistent max-width="500px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      dark
                      class="ma-4"
                      color="#f95738"
                      small
                      v-bind="attrs"
                      v-on="on"
                    >
                      Atualizar dados
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title>
                      <span class="headline">Atualizar dados</span>
                    </v-card-title>
                    <v-form ref="form">
                    <v-card-text>
                      <v-text-field
                        v-model="input_peso"
                        color="#f95738"
                        type="number"
                        :rules="formRules"
                        :max="maxPeso"
                        :min="minPeso"
                        label="Peso (kg)"
                        required
                      >
                        ></v-text-field
                      >
                    </v-card-text>
                    <v-card-text>
                      <v-text-field
                        v-model="input_muscular"
                        color="#f95738"
                        type="number"
                        :rules="formRules"
                        :max="maxPercentagem"
                        :min="minPercentagem"
                        label="Massa muscular (%)"
                        required
                      ></v-text-field>
                    </v-card-text>
                    <v-card-text>
                      <v-text-field
                        v-model="input_gorda"
                        color="#f95738"
                        type="number"
                        :rules="formRules"
                        :max="maxPercentagem"
                        :min="minPercentagem"
                        label="Massa gorda (%)"
                        required
                      ></v-text-field>
                    </v-card-text>
                    <v-card-text>
                      <v-text-field
                        v-model="input_altura"
                        color="#f95738"
                        type="number"
                        :max="maxAltura"
                        :min="minAltura"
                        label="Altura (cm)"
                        hint="Campo Opcional"
                        required
                      ></v-text-field>
                    </v-card-text>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn color="#f95738" text @click="dialog6 = false">
                        Sair
                      </v-btn>
                      <v-btn
                        color="#f95738"
                        text
                        @click="validateForm(input_peso, input_muscular, input_gorda, input_altura)"
                      >
                        Atualizar
                      </v-btn>
                    </v-card-actions>
                    </v-form>
                  </v-card>
                </v-dialog>
              </div>
          </v-card>

          <!-- <v-card class="mt-4">
            <v-card-title class="justify-center"> Os meus treinos </v-card-title>
            <v-divider class="mx-4"></v-divider>
            <v-card-subtitle>Total de horas de treino</v-card-subtitle>
            <v-card-subtitle>Média de minutos por treino</v-card-subtitle>
            <v-card-subtitle
              >Média de minutos de treino por dia</v-card-subtitle
            >
          </v-card>  

          <v-card class="mt-7">
            <v-card-title class="justify-center">
              Categorias de treinos
            </v-card-title>
            <v-divider class="mx-4"></v-divider>
            <div id="chart">
              <apexchart
                type="pie"
                width="350"
                height="400"
                :options="chartOptions_categorias"
                :series="series_categorias"
              ></apexchart>
            </div>
          </v-card>-->
        </v-col>

        <v-col cols="12" md="5">
          <v-card class="mx-auto text-center">
            <v-card-title class="justify-center" style="color:#f95738"
              >Peso, Massa Muscular e Massa Gorda </v-card-title
            >
            <v-divider></v-divider>
            <div id="chart">
              <line-chart />
            </div>
          </v-card>

          <!-- <v-card class="mt-4">
            <v-card-title class="justify-center">
              Os meus treinos
            </v-card-title>
            <v-divider class="mx-4"></v-divider>
            <v-row>
              <v-col cols="12" md="4">
                <v-list-item two-line>
                  <v-list-item-content>
                    <v-list-item-title
                      >Total de horas de treino</v-list-item-title
                    >
                    <v-list-item-subtitle>12</v-list-item-subtitle>
                  </v-list-item-content>
                </v-list-item>
              </v-col>
              <v-col cols="12" md="4">
                <v-list-item two-line>
                  <v-list-item-content>
                    <v-list-item-title>Média de minutos</v-list-item-title>
                    <v-list-item-title>por treino</v-list-item-title>
                    <v-list-item-subtitle>1</v-list-item-subtitle>
                  </v-list-item-content>
                </v-list-item>
              </v-col>
              <v-col cols="12" md="4">
                <v-list-item two-line>
                  <v-list-item-content>
                    <v-list-item-title
                      >Média de minutos de treino</v-list-item-title
                    >
                    <v-list-item-title>por dia</v-list-item-title>
                    <v-list-item-subtitle>10</v-list-item-subtitle>
                  </v-list-item-content>
                </v-list-item>
              </v-col>
            </v-row>
          </v-card> -->
        </v-col>
      </v-row>
    </div>
    <Footer />
  </div>
</template>

<script>
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_User.vue";
import LineChart from "@/components/InfoFisicaGrafico.vue";

import axios from "axios";
import sjcl from "sjcl";

export default {
  name: "Progresso",
  components: {
    NavBar,
    SideBar,
    LineChart,
  },
  created() {
    document.title = "Fitness Stack";
  },
  data: () => ({
    formRules: [
      (v) => !!v || "Campo obrigatório",
    ],
    dialog1: false,
    dialog2: false,
    dialog3: false,
    dialog4: false,
    dialog5: false,
    dialog6: false,
    imagem_input: null,
    username: "",
    new_username: "",
    input_email: "",
    old_password: "",
    new_password: "",
    input_peso: "",
    input_muscular: "",
    input_gorda: "",
    input_altura: "",

    minPeso: 30,
    maxPeso: 200,
    minPercentagem: 0,
    maxPercentagem: 100,
    minAltura: 100,
    maxAltura: 250,
    imc: 0,
    utilizador: {
      email: "",
      datanascimento: "",
      nome: "",
      username: "",
      peso: 0.0,
      m_gorda: 0.0,
      m_muscular: 0.0,
      altura: 0.0,
      genero: false,
      foto_perfil: "",
      info_fisica: [],
    },
  }),
  mounted() {
    axios
      .get(process.env.VUE_APP_BASELINK+"/api/user/getUserInfo", {
        headers: { token: localStorage.getItem("token") },
      })
      .then((response) => {
        this.utilizador = response.data;
        console.log(this.utilizador.foto_perfil);
        var valor =
          (this.utilizador.peso /
            (this.utilizador.altura * this.utilizador.altura)) *
          10000;
        this.imc = parseFloat(valor).toFixed(1);
      })
      .finally(() => (this.loading = false));
  },
  methods: {
    validateForm(input_peso, input_muscular, input_gorda, input_altura){
      if(this.$refs.form.validate()){
        if(input_altura!="") this.pesagem_altura(input_peso, input_muscular, input_gorda, input_altura);
        else this.pesagem(input_peso, input_muscular, input_gorda);
        this.dialog6 = false
      }
    },
    pesagem_altura(new_peso, new_muscular, new_gorda, new_altura) {
      var data = new Date(),
        dia = data.getDate().toString(),
        diaF = dia.length == 1 ? "0" + dia : dia,
        mes = (data.getMonth() + 1).toString(), //+1 pois no getMonth Janeiro começa com zero.
        mesF = mes.length == 1 ? "0" + mes : mes,
        anoF = data.getFullYear();

      axios
        .post(
          process.env.VUE_APP_BASELINK+"/api/user/novaInfoFisica",
          {
            data: anoF + "-" + mesF + "-" + diaF,
            peso: new_peso,
            m_muscular: new_muscular,
            m_gorda: new_gorda,
            altura: new_altura
          },
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response) => {
          console.log(response);
          this.utilizador.m_gorda = new_gorda;
          this.utilizador.m_muscular = new_muscular;
          this.utilizador.peso = new_peso;
          this.utilizador.altura = new_altura;
          var valor =
          (this.utilizador.peso /
            (this.utilizador.altura * this.utilizador.altura)) *
          10000;
        this.imc = parseFloat(valor).toFixed(1);
        })
        .finally(() => console.log("hi")); //msg erro a mudar email));*/
    },
    pesagem(new_peso, new_muscular, new_gorda) {
      var data = new Date(),
        dia = data.getDate().toString(),
        diaF = dia.length == 1 ? "0" + dia : dia,
        mes = (data.getMonth() + 1).toString(), //+1 pois no getMonth Janeiro começa com zero.
        mesF = mes.length == 1 ? "0" + mes : mes,
        anoF = data.getFullYear();

      axios
        .post(
          process.env.VUE_APP_BASELINK+"/api/user/novaInfoFisica",
          {
            data: anoF + "-" + mesF + "-" + diaF,
            peso: new_peso,
            m_muscular: new_muscular,
            m_gorda: new_gorda,
          },
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response) => {
          console.log(response);
          this.utilizador.m_gorda = new_gorda;
          this.utilizador.m_muscular = new_muscular;
          this.utilizador.peso = new_peso;
          var valor =
          (this.utilizador.peso /
            (this.utilizador.altura * this.utilizador.altura)) *
          10000;
        this.imc = parseFloat(valor).toFixed(1);
        })
        .finally(() => console.log("hi")); //msg erro a mudar email));*/
    },
    setEmail(new_email) {
      axios
        .post(
          process.env.VUE_APP_BASELINK+"/api/user/mudarEmail",
          {
            email: new_email,
          },
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response) => {
          console.log(response);
          this.utilizador.email = new_email;
        })
        .finally(() => console.log("hi")); //msg erro a mudar email));
    },
    setUsername(new_username) {
      axios
        .post(
          process.env.VUE_APP_BASELINK+"/api/user/mudarUsername",
          {
            username_novo: new_username,
          },
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response) => {
          if (response.status == 200) {
            this.utilizador.username = new_username;
            localStorage.setItem("token", response.data.token);
            localStorage.setItem("username", new_username);
            //localStorage.setItem("usertype",0)
          }
        })
        .finally(() => console.log("hi")); //msg erro a mudar username));
    },
    setPassword(oldP, newP) {
      axios
        .post(
          process.env.VUE_APP_BASELINK+"/api/user/mudarPassword",
          {
            new_password: sjcl.codec.hex.fromBits(sjcl.hash.sha256.hash(newP)),
            old_password: sjcl.codec.hex.fromBits(sjcl.hash.sha256.hash(oldP)),
          },
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response) => {
          const status = JSON.parse(response.status);

          if (status == "200") {
            console.log("mudei a pass");
          }
        })
        .finally(() => console.log("hi")); //msg erro a mudar username));
    },
    linkfoto(){
      return  process.env.VUE_APP_BASELINK
    },

    async setImagem(nova_imagem) {
      function carrega_foto(x) {
        return new Promise((resolve) => {
          let blob = new Blob([x]),
            fileReader = new FileReader();
          fileReader.readAsArrayBuffer(blob);
          fileReader.onload = function () {


          
            console.log("carreguei o ficheiro");
            console.log(this.result);
            resolve(Buffer.from(this.result).toString("base64"));
          };
        });
      }

      let foto = await carrega_foto(nova_imagem);
      console.log(foto);
      axios
        .post(
          process.env.VUE_APP_BASELINK+"/api/user/mudarImagem",
          { nova_foto: foto },
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response) => {
          if (response.status == 200) {
            console.log("funfou");
            console.log(this.utilizador.foto_perfil);
          }
        });
    },
  },
  computed: {
    verifyIMC() {
      return (this.imc >= 18.5 && this.imc <= 24.9);
    },
  }
};
</script>

<style>
.profile {
  background-color: #d3d3d2;
}
</style>
