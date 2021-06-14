<template>
  <div class="profile">
    <NavBar />
    
    <div>
      <SideBar />
      <v-row
        style="
          margin-left: 75px;
          padding-top: 100px;
          padding-bottom: 200px;
          margin-right: 25px;
        "
      >
      <v-col cols="12" md="3">
      </v-col>
      
        <v-col cols="12" md="6">
          
          <v-card style="text-align: center" class="mb-6">
            <div class="mx-auto text-center">
              <v-avatar class="mt-4" size="150">
                <v-img src="@/assets/favicon.png"></v-img>
              </v-avatar>
            </div>
            <v-card-title class="justify-center"> {{ admin.nome }} <v-icon dense color="#f95738" class="ml-2">mdi-shield-account</v-icon></v-card-title>
            <v-card-subtitle> {{ admin.username }}  </v-card-subtitle>
            <v-divider class="mx-4"></v-divider>
            <v-card-text class="my-1 black--text"><v-icon small class="mr-1">mdi-email</v-icon>{{ admin.email }}</v-card-text>

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
                    <span >Alterar nome de utilizador</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col cols="12" sm="6" md="12">
                          Digite o novo nome de utilizador.
                          <v-form ref="usernameForm">
                          <v-text-field
                            v-model="new_nome"
                            color="#f95738"
                            :rules="usernameRules"
                            prepend-icon="mdi-account-lock"
                            label="Nome de Utilizador"
                            required
                          ></v-text-field>
                          </v-form>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="#f95738" text @click="dialog1 = false">
                      Sair
                    </v-btn>
                    <v-btn color="#f95738" dark @click="validateUsername()">
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
                    <span >Alterar email</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col cols="12" sm="6" md="12">
                          Digite o novo email.
                          <v-form ref="emailForm">
                          <v-text-field
                            v-model="new_email"
                            color="#f95738"
                            :rules="emailRules"
                            prepend-icon="mdi-email"
                            label="Email"
                            required
                          ></v-text-field>
                          </v-form>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="#f95738" text @click="dialog2 = false">
                      Sair
                    </v-btn>
                    <v-btn color="#f95738" dark @click="validateEmail()">
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
                    <span >Alterar palavra-passe</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col cols="12" md="12">
                          <v-form ref="passwordForm">
                          Digite a palavra-passe antiga.
                          <v-text-field
                            v-model="old_pass"
                            :rules="passwordRules"
                            type="password"
                            color="#f95738"
                            counter
                            prepend-icon="mdi-lock"
                            label="Palavra-Passe Antiga"
                            required
                          ></v-text-field>
                          Digite a palavra-passe nova.
                          <v-text-field
                            v-model="new_pass"
                            color="#f95738"
                            type="password"
                            counter
                            :rules="passwordRules"
                            prepend-icon="mdi-lock-question"
                            label="Palavra-Passe Nova"
                            required
                          ></v-text-field>
                          </v-form>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="#f95738" text @click="dialog3 = false">
                      Sair
                    </v-btn>
                    <v-btn color="#f95738" dark @click="validatePassword()">
                      Atualizar
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-container>
          </v-card>
          <v-alert
            border="left"
            v-if="success"
            text
            dismissible
            elevation="2"
            type="success"
          >
            Informação atualizada com sucesso
          </v-alert>
          <v-alert
            border="left"
            v-if="error"
            text
            dismissible
            elevation="2"
            type="error"
          >
            Erro ao atualizar informação
          </v-alert>
        </v-col>
        
        <v-col cols="12" md="3">
        </v-col>
      </v-row>
    </div>
    <Footer />
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_Administrador.vue";


import axios from "axios";
//import sjcl from "sjcl";

export default {
  name: "Progresso",
  components: {
    NavBar,
    SideBar
  },
  created() {
    document.title = "Fitness Stack";
  },
  mounted() {
    axios
      //.get(process.env.VUE_APP_BASELINK+"/api/admin/getAdminInfo",{headers: {'token': localStorage.getItem("token")}})
      .get(process.env.VUE_APP_BASELINK+"/api/admin/getAdminInfo/"+localStorage.getItem("username"),{headers: {'token': localStorage.getItem("token")}})
      .then((response) => {
            this.admin.username=response.data.username;
            this.admin.email=response.data.email;
            this.admin.nome=response.data.nome;
      })
  },
  methods: {
    validateUsername() {
      if (this.$refs.usernameForm.validate()) {
        if (this.new_nome != "") {
          this.setUsername(this.new_nome);
          this.dialog1 = false;
        }
        else {
          this.dialog1 = false;
        }
      }
    },
    validateEmail() {
      if (this.$refs.emailForm.validate()) {
        if (this.new_email != "") {
          this.setEmail(this.new_email);
          this.dialog2 = false;
        }
        else {
          this.dialog2 = false;
        }
      }
    },
    validatePassword() {
      if (this.$refs.passwordForm.validate()) {
        if (this.old_pass != this.new_pass) {
          this.setPassword(this.old_pass,this.new_pass);
          this.dialog3 = false;
        }
        else {
          this.dialog3 = false;
        }
      }      
    },
    setEmail(new_email) {
      axios
        .post(
          process.env.VUE_APP_BASELINK+"/api/admin/mudarEmail",
            {
              "email": new_email
            }
            ,{headers: {'token': localStorage.getItem("token")}})
        
        .then((response) => {
          const status = JSON.parse(response.status);
          this.admin.email=new_email;

          if(status == "200"){
            this.success = true;
            setTimeout(() => {
              this.success = false;
            }, 5000);
          }          
        })
        .catch((error) => {
          if (error.response != null) {
            this.error = true;
            setTimeout(() => {
              this.error = false;
            }, 5000);
          }
        });
    },
    setUsername(new_username) {
      axios
        .post(
          process.env.VUE_APP_BASELINK+"/api/admin/mudarUsername",
            {
              "username_novo": new_username,
            }
        , {headers: {'token': localStorage.getItem("token")}})
        
        .then((response) => {
          const status = JSON.parse(response.status);

          if (status == "200") {
            this.admin.username = new_username
            localStorage.setItem("token",response.data.token)
            localStorage.setItem("username",new_username)

            this.success = true;
            setTimeout(() => {
              this.success = false;
            }, 5000);
          }    
        })
        .catch((error) => {
          if (error.response != null) {
            this.error = true;
            setTimeout(() => {
              this.error = false;
            }, 5000);
          }
        });

    },
    setPassword(oldP,newP){
        axios
        .post(
          process.env.VUE_APP_BASELINK+"/api/admin/mudarPassword",
            {
              "username":localStorage.getItem("username"),
              "new_password": newP,//sjcl.codec.hex.fromBits(sjcl.hash.sha256.hash(newP)),
              "old_password": oldP,//sjcl.codec.hex.fromBits(sjcl.hash.sha256.hash(oldP))
              }
        , {headers: {'token': localStorage.getItem("token")}})
        
        .then((response) => {

          const status = JSON.parse(response.status);
            
            if (status == "200") {
              this.success = true;
              setTimeout(() => {
              this.success = false;
            }, 5000);
            }
        })
        .catch((error) => {
          if (error.response != null) {
            this.error = true;
            setTimeout(() => {
              this.error = false;
            }, 5000);
          }
        });
    }
  },
  data: () => ({
    admin:{
      username:"",
      email:"",
      nome:"",
    },
    usernameRules: [
      (v) =>
        v == "" ||
        /^[a-zA-Z0-9.-]{2,}$/.test(v) ||
        "Nome de utilizador inválido",
    ],
    emailRules: [
      (v) => v == "" || /.+@.+/.test(v) || "Email inválido",
    ],
    passwordRules: [
      (v) => !!v || "Campo obrigatório",
      (v) =>
        /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/.test(v) ||
        "A palavra-passe deve ter no mínimo 8 caracteres, pelo menos 1 maiúscula, 1 minúscula e 1 número",
    ],
    error: false,
    success: false,
    dialog1: false,
    dialog2: false,
    dialog3: false,
    dialog4: false,
    dialog5: false,
    dialog6: false,
    infos: {
      nome: "10 Min Ab Workout",
      username: "10 min",
      email: "Abdominais",
      password: "Iniciante",
      genero: "Pamela Reif",
      datanascimento: "07/05/2021",
      peso: "4.6/5",
      altura: "true",
    },
    series_categorias: [44, 55, 13, 43, 22, 10, 10, 10, 10],
    chartOptions_categorias: {
      chart: {
        width: 380,
        type: "pie",
      },
      labels: ["A", "B", "C", "D", "E", "F", "G", "H", "I"],
      colors: [
        "#36393F",
        "#40444B",
        "#B9BBBE",
        "#D3D3D2",
        "#000314",
        "#F95738",
        "#FB7A60",
        "#FB9B88",
        "#7189FF",
      ],
      responsive: [
        {
          breakpoint: 480,
          options: {
            chart: {
              width: 200,
            },
            legend: {
              position: "bottom",
            },
          },
        },
      ],
    },
    series_peso_massa: [
      {
        name: "High - 2013",
        data: [28, 29, 33, 36, 32, 32, 33],
      },
      {
        name: "Low - 2013",
        data: [12, 11, 14, 18, 17, 13, 13],
      },
    ],
    chartOptions_peso_massa: {
      chart: {
        height: 350,
        type: "line",
        dropShadow: {
          enabled: true,
          color: "#f95738",
          top: 18,
          left: 7,
          blur: 10,
          opacity: 0.2,
        },
        toolbar: {
          show: false,
        },
      },
      colors: ["#40444B", "#F95738"],
      dataLabels: {
        enabled: true,
      },
      stroke: {
        curve: "smooth",
      },
      grid: {
        borderColor: "#e7e7e7",
        row: {
          colors: ["#f3f3f3", "transparent"], // takes an array which will be repeated on columns
          opacity: 0.5,
        },
      },
      markers: {
        size: 1,
      },
      xaxis: {
        categories: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul"],
        title: {
          text: "Data de pesagem",
        },
      },
      yaxis: {
        title: {
          text: "kg / %",
        },
        min: 5,
        max: 40,
      },
      legend: {
        position: "top",
        horizontalAlign: "right",
        floating: true,
        offsetY: -25,
        offsetX: -5,
      },
    },

    series_treinos: [
      {
        name: "Net Profit",
        data: [44, 55, 57, 56, 61, 58, 63, 60, 66],
      },
    ],
    chartOptions_treinos: {
      chart: {
        type: "bar",
        height: 350,
      },
      colors: ["#FB9B88"],
      plotOptions: {
        bar: {
          horizontal: false,
          columnWidth: "55%",
          endingShape: "rounded",
        },
      },
      dataLabels: {
        enabled: false,
      },
      stroke: {
        show: true,
        width: 2,
      },
      xaxis: {
        categories: [
          "Feb",
          "Mar",
          "Apr",
          "May",
          "Jun",
          "Jul",
          "Aug",
          "Sep",
          "Oct",
        ],
        title: {
          text: "Data do treino",
        },
      },
      yaxis: {
        title: {
          text: "Minutos de treino",
        },
      },
      fill: {
        opacity: 1,
      },
      tooltip: {
        y: {
          formatter: function (val) {
            return "$ " + val + " thousands";
          },
        },
      },
    },
    minPeso: 30,
    maxPeso: 200,
    minPercentagem: 0,
    maxPercentagem: 100,
  }),
};
</script>

};


<style>
.profile {
  background-color: #d3d3d2;
}
</style>
