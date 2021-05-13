<template>
  <div class="homeUser">
    <NavBar />
    <div>
      <v-row style="margin-left: 100px; margin-top: 100px; margin-right: 100px; margin-bottom: 25px">
        <v-col cols="12" md="2">
          <v-card style="text-align: center">
          
             <div class="mx-auto text-center">
            <v-avatar class="mt-4" size="150" >
               <v-img src="https://picsum.photos/200"></v-img>
            </v-avatar>
             </div>
            <v-card-title class="justify-center">Xana</v-card-title>
            <v-card-subtitle> xanareigada </v-card-subtitle>
            <v-divider class="mx-4"></v-divider>
            <p>Email</p> 
            <v-row class="justify-center">
              <v-col cols="12" md="4">
                <v-dialog v-model="dialog" persistent max-width="400px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      height="25"
                      width="20"
                      color="white"
                      dark
                      v-bind="attrs"
                      v-on="on"
                    >
                      <v-icon color="#f95738"> mdi-account-edit-outline </v-icon>
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title>
                      <span class="headline">Alterar username</span>
                    </v-card-title>
                    <v-card-text>
                      <v-container>
                        <v-row>
                          <v-col cols="12" sm="6" md="12">
                            Digite o novo username.
                            <v-text-field
                            
                              placeholder="username"
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
                      <v-btn color="#f95738" text @click="dialog1 = false">
                        Atualizar
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-col>

              <v-col cols="12" md="4">
                <v-dialog v-model="dialog" persistent max-width="400px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      height="25"
                      color="white"
                      dark
                      v-bind="attrs"
                      v-on="on"
                    >
                      <v-icon color="#f95738"> mdi-email-edit-outline  </v-icon>
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
                              
                              placeholder="40"
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
                      <v-btn color="#f95738" text @click="dialog2 = false">
                        Atualizar
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-col>

              <v-col cols="12" md="4">
                <v-dialog v-model="dialog" persistent max-width="400px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      height="25"
                      color="white"
                      dark
                      v-bind="attrs"
                      v-on="on"
                    >
                      <v-icon color="#f95738"> mdi-lock-reset</v-icon>
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title>
                      <span class="headline">Alterar password</span>
                    </v-card-title>
                    <v-card-text>
                      <v-container>
                        <v-row>
                          <v-col cols="12" sm="6" md="12">
                            Digite a password antiga.
                            <v-text-field
                             required
                            ></v-text-field>
                            Digite a password nova.
                            <v-text-field
                              placeholder="40"
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
                      <v-btn color="#f95738" text @click="dialog3 = false">
                        Atualizar
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-col>

            </v-row>
          










           </v-card>
          
          
          
        </v-col>

        <v-col cols="12" md="5">
          <v-card>
            <v-card-title
              >Peso atual: 80kg
              <v-dialog v-model="dialog" persistent max-width="300px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    class="mx-4"
                    height="25"
                    color="white"
                    dark
                    v-bind="attrs"
                    v-on="on"
                  >
                    <v-icon color="#f95738"> mdi-lead-pencil </v-icon>
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span class="headline">Atualizar peso</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            placeholder="40"
                            required
                          ></v-text-field>
                          kg
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="#f95738" text @click="dialog4 = false">
                      Sair
                    </v-btn>
                    <v-btn color="#f95738" text @click="dialog4 = false">
                      Atualizar
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
              <v-spacer></v-spacer>
              Meta: 70kg
            </v-card-title>
            <div style="margin-left: 50px; margin-right: 50px">
              <br />
              <v-progress-linear
                color="#f95738"
                height="20"
                value="20"
                striped
              ></v-progress-linear>
            </div>
            <v-card-text>
              <v-row>
              <v-col cols="12" md="6">
                <p>Altura</p>
                
              <p>
                Percentagem de massa gorda
                <v-dialog v-model="dialog" persistent max-width="500px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      class="mx-4"
                      height="25"
                      color="white"
                      dark
                      v-bind="attrs"
                      v-on="on"
                    >
                      <v-icon color="#f95738"> mdi-lead-pencil </v-icon>
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title>
                      <span class="headline">Atualizar percentagem de massa gorda</span>
                    </v-card-title>
                    <v-card-text>
                      <v-container>
                        <v-row>
                          <v-col cols="12" sm="6" md="4">
                            <v-text-field
                              placeholder="40"
                              required
                            ></v-text-field>
                            %
                          </v-col>
                        </v-row>
                      </v-container>
                    </v-card-text>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn color="#f95738" text @click="dialog5 = false">
                        Sair
                      </v-btn>
                      <v-btn color="#f95738" text @click="dialog5 = false">
                        Atualizar
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </p>
              
              <p>
                Percentagem massa muscular
                <v-dialog v-model="dialog" persistent max-width="500px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      class="mx-4"
                      height="25"
                      color="white"
                      dark
                      v-bind="attrs"
                      v-on="on"
                    >
                      <v-icon color="#f95738"> mdi-lead-pencil </v-icon>
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title>
                      <span class="headline">Atualizar percentagem massa muscular</span>
                    </v-card-title>
                    <v-card-text>
                      <v-container>
                        <v-row>
                          <v-col cols="12" sm="6" md="4">
                            <v-text-field
                              placeholder="40"
                              required
                            ></v-text-field>
                            %
                          </v-col>
                        </v-row>
                      </v-container>
                    </v-card-text>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn color="#f95738" text @click="dialog6 = false">
                        Sair
                      </v-btn>
                      <v-btn color="#f95738" text @click="dialog6 = false">
                        Atualizar
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </p>
              </v-col>
              
                <v-col cols="12" md="6">
                <p>IMC</p>
                <p>IMC ideal</p>
                </v-col>
              </v-row>
            </v-card-text>
          </v-card>

          
          <v-card class="mt-4">
            <v-card-title class="justify-center"> Os meus treinos </v-card-title>
             <v-divider class="mx-4"></v-divider>
          <v-row>
            <v-col cols="12" md="4">
              <v-list-item two-line>
                <v-list-item-content>
                  <v-list-item-title>Total de horas de treino</v-list-item-title>
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
              <v-list-item two-line >
                <v-list-item-content>
                  <v-list-item-title>Média de minutos de treino</v-list-item-title>
                  <v-list-item-title>por dia</v-list-item-title>
                  <v-list-item-subtitle>10</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
            </v-col>
          </v-row>
          </v-card>
         
           <!-- <v-card class="mt-4">
            <v-card-title class="justify-center"> Os meus treinos </v-card-title>
            <v-divider class="mx-4"></v-divider>
            <v-card-subtitle>Total de horas de treino</v-card-subtitle>
            <v-card-subtitle>Média de minutos por treino</v-card-subtitle>
            <v-card-subtitle
              >Média de minutos de treino por dia</v-card-subtitle
            >
          </v-card>  -->


          <v-card class="mt-7">
            <v-card-title class="justify-center"> Categorias de treinos </v-card-title>
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
          </v-card>
        </v-col>
        




        <v-col cols="12" md="5">
          <v-card class=" mx-auto text-center">
            <v-card-title class="justify-center">Peso e Massa Muscular</v-card-title>
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
            <v-card-title class="justify-center">Minutos por treino</v-card-title>
            <v-divider class="mx-4"></v-divider>
            <div width="300" height="300">
              <apexchart
                type="bar"
                height="350"
                :options="chartOptions_treinos"
                :series="series_treinos"
              ></apexchart>
            </div>
          </v-card>
          </v-col>
      </v-row>
    </div>
    <Footer />
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_User.vue";
import Footer from "@/components/Footer.vue";
import VueApexCharts from "vue-apexcharts";

export default {
  name: "Progresso",
  components: {
    NavBar,
    Footer,
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
  }),
};
</script>

};


<style>
.homeUser {
  background-color: #d3d3d2;
}
</style>
