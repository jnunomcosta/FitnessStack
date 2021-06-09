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
        <v-col cols="12" md="2">
          <v-card style="text-align: center">
            <div class="mx-auto text-center">
              <v-avatar class="mt-4" size="150">
                <v-img
                  :src="'http://localhost:4576' + utilizador.foto_perfil"
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
            <div class="mt-4 body-2" ><v-span >{{ utilizador.email }}</v-span></div>
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
                    <v-btn color="#f95738" text @click="setUsername(new_username);dialog1 = false">
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
                    <v-btn color="#f95738" text @click="setEmail(input_email);dialog2 = false">
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
                    <v-btn color="#f95738" text @click="setPassword(old_password,new_password);dialog3 = false">
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
                      v-model="imagem_inputa"
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
                    <v-btn color="#f95738" text @click="setImagem(imagem_inputa);dialog4 = false">
                      Atualizar
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-container>
          </v-card>
        </v-col>

        <v-col cols="12" md="5">
          <v-card>
            
            <v-card-text>
              <v-row>
                <v-col cols="12" md="5">
                  <h2 class="font-weight-black"
              >Peso atual: {{ utilizador.peso }}kg
              
              
              
            </h2>
            <v-spacer class="mt-4"></v-spacer>

                  <p>
                    Massa Gorda {{ utilizador.m_gorda }}%
                    
                  </p>

                  <p>
                    Massa Muscular {{ utilizador.m_muscular }}%
                    
                  </p>
                </v-col>

                <v-col cols="12" md="7">
                  <p>Altura {{ utilizador.altura }}cm</p>
                  <p>IMC {{ imc }}</p>
                  <p>IMC ideal: 18.5 - 24.9</p>
                </v-col>
              </v-row>
              <div>
                  <v-dialog v-model="dialog6" persistent max-width="500px">
                      <template v-slot:activator="{ on, attrs }">
                        <v-btn  
                                            
                          dark  
                          class="mx-4 "
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
                          <span class="headline"
                            >Atualizar dados</span
                          >
                        </v-card-title>
                        <v-card-text>
                    <v-text-field
                      v-model="input_peso"
                      color="#f95738"
                      type="number"
                      :max="maxPeso"
                      :min="minPeso"
                      label="Peso (kg)"
                      required
                    >
                      ></v-text-field>
                    
                  </v-card-text>
                        <v-card-text>

                          <v-text-field
                            v-model="input_muscular"
                            color="#f95738"
                            type="number"
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
                            :max="maxPercentagem"
                            :min="minPercentagem"
                            label="Massa gorda (%)"
                            required
                          ></v-text-field>

                        </v-card-text>
                        <v-card-actions>
                          <v-spacer></v-spacer>
                          <v-btn color="#f95738" text @click="dialog6 = false">
                            Sair
                          </v-btn>
                          <v-btn color="#f95738" text @click="pesagem(input_peso,input_muscular,input_gorda);dialog6 = false">
                            Atualizar
                          </v-btn>
                        </v-card-actions>
                      </v-card>
                    </v-dialog>
              </div>
            </v-card-text>
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
            <v-card-title class="justify-center"
              >Peso e Massa Muscular</v-card-title
            >
            <v-divider class="mx-4"></v-divider>
            <div id="chart">
              <apexchart
                type="line"
                height="250"
                :options="chartOptions_peso_massa"
                :series="series_peso_massa"
              ></apexchart>
            </div>
          </v-card>

          <v-card class="mt-4">
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
          </v-card>
        </v-col>
      </v-row>
    </div>
    <Footer />
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_User.vue";
import VueApexCharts from "vue-apexcharts";


import axios from "axios";
import sjcl from "sjcl";

export default {
  name: "Progresso",
  components: {
    NavBar,
    SideBar,
    apexchart: VueApexCharts,
  },
  created() {
    document.title = "Fitness Stack";
  },
  data: () => ({
    dialog1: false,
    dialog2: false,
    dialog3: false,
    dialog4: false,
    dialog5: false,
    dialog6: false,
    imagem_inputa: null,
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
    },
  }),
  mounted() {
    axios
      .get("http://localhost:4576/api/user/getUserInfo",{headers: {'token': localStorage.getItem("token")}})
      .then((response) => {
        
        this.utilizador = response.data;
        this.imc =
          (this.utilizador.peso /
            (this.utilizador.altura * this.utilizador.altura)) *
          10000;
      })
      .finally(() => (this.loading = false));
  },
  methods: {

    pesagem(new_peso,new_muscular,new_gorda) {


        var data = new Date(),
        dia  = data.getDate().toString(),
        diaF = (dia.length == 1) ? '0'+dia : dia,
        mes  = (data.getMonth()+1).toString(), //+1 pois no getMonth Janeiro começa com zero.
        mesF = (mes.length == 1) ? '0'+mes : mes,
        anoF = data.getFullYear();
                
      axios
        .post(
          "http://localhost:4576/api/user/novaInfoFisica",
            {
              "data":anoF+"/"+mesF+"/"+diaF,
              "peso": new_peso,
              "m_muscular":new_muscular,
              "m_gorda":new_gorda
              }
            , {headers: {'token': localStorage.getItem("token")}}
        )
        .then((response) => {
              console.log(response);
              this.utilizador.m_gorda=new_gorda;
              this.utilizador.m_muscular=new_muscular
              this.utilizador.peso=new_peso
              this.imc =
          (this.utilizador.peso /
            (this.utilizador.altura * this.utilizador.altura)) *
          10000;

        })
        .finally(() => console.log("hi"));//msg erro a mudar email));*/

    },
      setEmail(new_email) {
      axios
        .post(
          "http://localhost:4576/api/user/mudarEmail",
            {
              "username": localStorage.getItem("username"),
              "email": new_email
              }
            , {headers: {'token': localStorage.getItem("token")}}
        )
        .then((response) => {
          console.log(response  )
          this.utilizador.email=new_email
          
        })
        .finally(() => console.log("hi"));//msg erro a mudar email));
    },
    setUsername(new_username) {
      axios
        .post(
          "http://localhost:4576/api/user/mudarUsername",
            {
              "username_novo": new_username,
              }
            , {headers: {'token': localStorage.getItem("token")}}
        )
        .then((response) => {
          console.log(response  )
          this.utilizador.username= new_username
          localStorage.setItem("token",response.token)
          localStorage.setItem("username",new_username)
        
          
        })
        .finally(() => console.log("hi"));//msg erro a mudar username));
    },
    setPassword(oldP,newP){
        axios
        .post(
          "http://localhost:4576/api/user/mudarPassword",
            {
              "username":localStorage.getItem("username"),
              "new_password": sjcl.codec.hex.fromBits(sjcl.hash.sha256.hash(newP)),
              "old_password": sjcl.codec.hex.fromBits(sjcl.hash.sha256.hash(oldP))
              }
            , {headers: {'token': localStorage.getItem("token")}}
        )
        .then((response) => {

          const status = JSON.parse(response.status);
            
            if (status == "200") {
              console.log("mudei a pass")
            }   
        })
        .finally(() => console.log("hi"));//msg erro a mudar username));
    },
    async setImagem(imagem_inputa){
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

      let foto = await carrega_foto(imagem_inputa);
      axios.post("http://localhost:4576/api/user/mudarImagem",{nova_foto:foto},{headers: {'token': localStorage.getItem("token")}})
           .then(response => {
             console.log(response);
           })
    }
    
  },
};
</script>

<style>
.profile {
  background-color: #d3d3d2;
}
</style>
