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
      </v-col>
      
        <v-col cols="12" md="6">
          
          <v-card style="text-align: center">
            <div class="mx-auto text-center">
              <v-avatar class="mt-4" size="150">
                <v-img src="@/assets/favicon.png"></v-img>
              </v-avatar>
            </div>
            <v-card-title class="justify-center"> {{ admin.nome }}</v-card-title>
            <v-card-subtitle> {{ admin.username }} </v-card-subtitle>
            <v-divider class="mx-4"></v-divider>
            <div class="mt-4 body-2"> {{ admin.email }} </div>

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
                            v-model="new_nome"
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
                    <v-btn color="#f95738" text @click="setUsername(new_nome);dialog1 = false">
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
                            v-model="new_email"
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
                    <v-btn color="#f95738" text @click="setEmail(new_email);dialog2 = false">
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
                            v-model="old_pass"
                            color="#f95738"
                            prepend-icon="mdi-lock"
                            label="Palavra-Passe Antiga"
                            required
                          ></v-text-field>
                          Digite a palavra-passe nova.
                          <v-text-field
                            v-model="new_pass"
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
                    <v-btn color="#f95738" text @click="setPassword(old_pass,new_pass);dialog3 = false">
                      Atualizar
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-container>
          </v-card>
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
      //.get("http://localhost:4576/api/admin/getAdminInfo",{headers: {'token': localStorage.getItem("token")}})
      .get("http://localhost:4576/api/admin/getAdminInfo/"+localStorage.getItem("username"),{headers: {'token': localStorage.getItem("token")}})
      .then((response) => {
       
            this.admin.username=response.data.username;
            this.admin.email=response.data.email;
            this.admin.nome=response.data.nome;


      })
      .finally(() => (this.loading = false));
  },
  methods: {
    setEmail(new_email) {
      axios
        .post(
          "http://localhost:4576/api/admin/mudarEmail",
            {
              "email": new_email
            }
            ,{headers: {'token': localStorage.getItem("token")}})
        
        .then((response) => {
          console.log(response)
          if(response.status==200){
            this.admin.email=new_email
          }
          
          
        })
        .finally(() => console.log("hi"));//msg erro a mudar email));
    },
    setUsername(new_username) {
      axios
        .post(
          "http://localhost:4576/api/admin/mudarUsername",
            {
              "username_novo": new_username,
            }
        , {headers: {'token': localStorage.getItem("token")}})
        
        .then((response) => {
          console.log(response  )
          if(response.status == 200){
            this.admin.username = new_username
            localStorage.setItem("token",response.data.token)
            localStorage.setItem("username",new_username)
          }      
        })
        .finally(() => console.log("hi"));//msg erro a mudar username));
    },
    setPassword(oldP,newP){
        axios
        .post(
          "http://localhost:4576/api/admin/mudarPassword",
            {
              "username":localStorage.getItem("username"),
              "new_password": newP,//sjcl.codec.hex.fromBits(sjcl.hash.sha256.hash(newP)),
              "old_password": oldP,//sjcl.codec.hex.fromBits(sjcl.hash.sha256.hash(oldP))
              }
        , {headers: {'token': localStorage.getItem("token")}})
        
        .then((response) => {

          const status = JSON.parse(response.status);
            
            if (status == "200") {
              console.log("mudei a pass")
            }
            
        
          
        })
        .finally(() => console.log("hi"));//msg erro a mudar username));
    }
    
  },



  data: () => ({

    admin:{
      username:"",
      email:"",
      nome:"",
    },


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
