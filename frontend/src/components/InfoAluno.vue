<template>
  <v-container class="ma-0 pa-0">
    <v-dialog
      v-model="dialog"
      fullscreen
      hide-overlay
      transition="dialog-bottom-transition"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          class="mx-2 my-2"
          v-bind="attrs"
          v-on="on"
          small
          color="#f95738"
          dark
        >
          Agendar treino
        </v-btn>
      </template>
      <template v-slot:default="dialog">
        <v-card>
          <v-toolbar color="#f95738" dark
            ><h3>Agendar treino</h3>
            <v-spacer></v-spacer>
            <v-btn icon @click="dialog.value = false"
              ><v-icon>mdi-close</v-icon></v-btn
            >
          </v-toolbar>
          <v-row
            justify="space-around"
            style="margin-top: 70px; margin-left: 100px; margin-right: 100px"
          >
            <v-col cols="12" md="8">
              <template>
                <v-sheet>
                  <v-toolbar flat>
                    <v-btn
                      outlined
                      class="mr-4"
                      color="grey darken-2"
                      @click="setToday"
                    >
                      Today
                    </v-btn>
                    <v-btn fab text small color="grey darken-2" @click="prev">
                      <v-icon small> mdi-chevron-left </v-icon>
                    </v-btn>
                    <v-btn fab text small color="grey darken-2" @click="next">
                      <v-icon small> mdi-chevron-right </v-icon>
                    </v-btn>
                    <v-toolbar-title v-if="$refs.calendar">
                      {{ $refs.calendar.title }}
                    </v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-menu bottom right>
                      <template v-slot:activator="{ on, attrs }">
                        <v-btn
                          outlined
                          color="grey darken-2"
                          v-bind="attrs"
                          v-on="on"
                        >
                          <span>{{ typeToLabel[type] }}</span>
                          <v-icon right> mdi-menu-down </v-icon>
                        </v-btn>
                      </template>
                      <v-list>
                        <v-list-item @click="type = 'day'">
                          <v-list-item-title>Day</v-list-item-title>
                        </v-list-item>
                        <v-list-item @click="type = 'week'">
                          <v-list-item-title>Week</v-list-item-title>
                        </v-list-item>
                        <v-list-item @click="type = 'month'">
                          <v-list-item-title>Month</v-list-item-title>
                        </v-list-item>
                        <v-list-item @click="type = '4day'">
                          <v-list-item-title>4 days</v-list-item-title>
                        </v-list-item>
                      </v-list>
                    </v-menu>
                  </v-toolbar>
                </v-sheet>
                <v-sheet height="auto" width="700">
                  <v-calendar
                    ref="calendar"
                    v-model="focus"
                    color="#f95738"
                    :events="events"
                    :event-color="getEventColor"
                    :type="type"
                    @click:event="showEvent"
                    @click:more="viewDay"
                    @click:date="viewDay"
                    @change="updateRange"
                  ></v-calendar>
                  <v-menu
                    v-model="selectedOpen"
                    :close-on-content-click="false"
                    :activator="selectedElement"
                    offset-x
                  >
                    <!-- <v-card color="grey lighten-4" min-width="200px" flat>
                      <v-toolbar :color="selectedEvent.color" dark>
                        <v-btn icon>
                          <v-icon>mdi-pencil</v-icon>
                        </v-btn>
                        <v-toolbar-title
                          v-html="selectedEvent.name"
                        ></v-toolbar-title>
                        <v-spacer></v-spacer>
                        <v-btn icon>
                          <v-icon>mdi-heart</v-icon>
                        </v-btn>
                        <v-btn icon>
                          <v-icon>mdi-dots-vertical</v-icon>
                        </v-btn>
                      </v-toolbar>
                      <v-card-text>
                        <h3 class="text-center">{{treino_info.nome}}</h3>
                        <h3 class="text-center">{{treino_info.dificuldade}}</h3>
                        <h3 class="text-center">{{treino_info.duracao}}</h3>
                      </v-card-text>
                      <v-card-actions>
                        <v-btn
                          text
                          color="#f95738"
                          @click="selectedOpen = false"
                        >
                          Cancelar
                        </v-btn>
                      </v-card-actions>
                    </v-card> -->
                    <v-card color="grey lighten-4" min-width="200px" flat>
                  <v-toolbar :color="selectedEvent.color" dark>
                    <v-toolbar-title
                      v-html="treino_info.nome"
                    ></v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-btn icon @click="verTreino(treino_info.codigo)">
                      <v-icon>mdi-magnify</v-icon>
                    </v-btn>
                  </v-toolbar>
                  <v-card-text>
                    <h3 class="text-center">{{treino_info.nome}}</h3>
                    <h3 class="text-center">{{treino_info.dificuldade}}</h3>
                    <h3 class="text-center">{{treino_info.duracao}}</h3>
                  </v-card-text>
                  <v-card-actions>
                    <v-btn text color="#f95738" @click="selectedOpen = false">
                      Cancelar
                    </v-btn>
                  </v-card-actions>
                </v-card>
                  </v-menu>
                </v-sheet>
              </template>
            </v-col>

            <v-col cols="12" md="4" class="mt-16">
              <v-autocomplete
                    label="Código Treino*"
                    v-model="select"
                    :items="items"
                    required
                  >
              </v-autocomplete>
            <template>
            <v-menu
              ref="menu"
              v-model="menu_1"
              :close-on-content-click="false"
              :return-value.sync="date"
              transition="scale-transition"
              offset-y
              min-width="auto"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="date_1"
                  label="Data"
                  prepend-icon="mdi-calendar"
                  color="#f95738"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker v-model="date_1" no-title scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="#f95738" @click="menu_1 = false">
                  Cancelar
                </v-btn>
                <v-btn text color="#f95738" @click="$refs.menu.save(date_1)">
                  OK
                </v-btn>
              </v-date-picker>
            </v-menu>
            <v-spacer></v-spacer>
          </template>

          <template>
            <v-dialog
              ref="dialog"
              v-model="modal2"
              :return-value.sync="time"
              persistent
              width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="time"
                  label="Hora"
                  prepend-icon="mdi-clock-time-four-outline"
                  readonly
                  color="#f95738"
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-time-picker v-if="modal2" v-model="time" full-width>
                <v-spacer></v-spacer>
                <v-btn text color="#f95738" @click="modal2 = false">
                  Cancelar
                </v-btn>
                <v-btn text color="#f95738" @click="$refs.dialog.save(time)">
                  OK
                </v-btn>
              </v-time-picker>
            </v-dialog>
          </template>
          <div class="text-center mt-10">
            <v-btn color="#f95738" v-on:click="confirmar()" dark> Confirmar </v-btn>
          </div>
            </v-col>
          </v-row>
        </v-card>
      </template>
    </v-dialog>

    <v-dialog
      v-model="dialog"
      fullscreen
      hide-overlay
      transition="dialog-bottom-transition"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          class="mx-2 my-2"
          v-bind="attrs"
          v-on="on"
          small
          color="#f95738"
          dark
        >
          <v-icon>mdi-text-box-search</v-icon>
        </v-btn>
      </template>
      <template v-slot:default="dialog">
        <v-card>
          <v-toolbar color="#f95738" dark
            ><h3>{{ utilizador.nome }} </h3>
            <v-spacer></v-spacer>
            <v-btn icon @click="dialog.value = false"
              ><v-icon>mdi-close</v-icon></v-btn
            >
          </v-toolbar>
          <v-col cols="12" md="2">
            <v-card style="text-align: center">
              <div class="mx-auto text-center">
                <v-avatar class="mt-4" size="150">
                  <v-img
                 :src="'http://localhost:4576' + utilizador.foto_perfil"
              ></v-img>
                </v-avatar>
              </div>
              <v-card-subtitle> {{utilizador.username}} </v-card-subtitle>
              <v-divider class="mx-4"></v-divider>
              <div class="mt-4 body-2">{{utilizador.email}}</div>
            </v-card>
          </v-col>

          <v-col cols="12" md="5">
            <v-card>
              <v-card-title
                >Peso atual: {{utilizador.peso}}

                <v-spacer></v-spacer>
               <!-- Meta: 70kg--> 
              </v-card-title>
              <!--  <div style="margin-left: 50px; margin-right: 50px">
                <br />
                <v-progress-linear
                  color="#f95738"
                  height="20"
                  value="20"
                  striped
                ></v-progress-linear>
              </div>--> 
              <v-card-text>
                <v-row>
                  <v-col cols="12" md="6">
                    <p>Altura: {{utilizador.altura}}cm</p> 

                    <p>Percentagem de massa gorda {{utilizador.m_gorda}}%</p>

                    <p>Percentagem de massa muscular {{utilizador.m_muscular}}%</p>
                  </v-col>

                  <v-col cols="12" md="6">
                    <p>IMC: {{imc}}</p>
                    <p>IMC ideal: 18.5 - 24.9</p>
                  </v-col>
                </v-row>
              </v-card-text>
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

            <!-- <v-card class="mt-4">
            <v-card-title class="justify-center"> Os meus treinos </v-card-title>
            <v-divider class="mx-4"></v-divider>
            <v-card-subtitle>Total de horas de treino</v-card-subtitle>
            <v-card-subtitle>Média de minutos por treino</v-card-subtitle>
            <v-card-subtitle
              >Média de minutos de treino por dia</v-card-subtitle
            >
          </v-card>  -->
          <!--
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
              <line-chart :data="utilizador"/>
            </div>
          </v-card>
          </v-col>
  <!--
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
              <v-card-title class="justify-center"
                >Minutos por treino</v-card-title
              >
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
          </v-col>-->

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="#f95738" text @click="removeContrato();dialog.value = false">
              Terminar contrato
            </v-btn>
          </v-card-actions>
        </v-card>
      </template>
    </v-dialog>
  </v-container>
</template>

<script>
//import VueApexCharts from "vue-apexcharts";
import LineChart from "@/components/InfoFisicaGrafico.vue";
import axios from "axios";  

export default {
  name: "InfoAluno",
  components: {
    LineChart,
    //apexchart: VueApexCharts,
  },
  data: () => ({
    utilizador:null,
    imc:0,
    select:"",
    items:[],
    time: null,
    menu2: false,
    modal2: false,
    date_1: new Date().toISOString().substr(0, 10),
    menu_1: false,
    modal_1: false,
    menu2_1: false,
    dialog1: false,
    dialog2: false,
    dialog3: false,
    dialog4: false,
    dialog5: false,
    dialog6: false,
    treino_info: {},
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
    focus: "",
    type: "month",
    typeToLabel: {
      month: "Month",
      week: "Week",
      day: "Day",
      "4day": "4 Days",
    },
    selectedEvent: {},
    selectedElement: null,
    selectedOpen: false,
    events: [],
    colors: [
      "blue",
      "indigo",
      "deep-purple",
      "cyan",
      "green",
      "orange",
      "grey darken-1",
    ],
    names: [
      "Meeting",
      "Holiday",
      "PTO",
      "Travel",
      "Event",
      "Birthday",
      "Conference",
      "Party",
    ],
  }),
  props:["data"],
  mounted() {
    axios
      .get("http://localhost:4576/api/treinos/getCodigos",{headers: {'token': localStorage.getItem("token")}})
      .then(response => {
        this.items = response.data
      })
    axios
      .get("http://localhost:4576/api/agenda/getAgenda?username="+this.data.utilizador,{headers: {'token': localStorage.getItem("token")}})
      .then(response => {
        console.log(response.data)
        response.data.forEach(x => {
          var objjjj = {
            name: x.name,
            start: new Date(x.start),
            end: new Date(x.end),
            color: x.color,
          }
          console.log(objjjj);
          this.events.push(objjjj);
        });
        console.log(this.events);
      }) 

    axios
      .get("http://localhost:4576/api/user/getUser?username="+this.data.utilizador,{headers: {'token': localStorage.getItem("token")}})
      .then((response) => { 
        this.utilizador = response.data;
        console.log("zzzzzzzzzzzzzzzzzzz"+JSON.stringify(this.utilizador))
        console.log("dkansdjnsadjnsa"+ JSON.stringify(this.utilizador))
        this.imc =
          (this.utilizador.peso /
            (this.utilizador.altura * this.utilizador.altura)) *
          10000;
      })
      .finally(() => (this.loading = false));

    this.$refs.calendar.checkChange();
  },
  methods: {
    confirmar(){
      var d = new Date(this.date_1 + ' ' + this.time);
      console.log(d);
      var da = new Date(d);
      da.setHours(da.getHours()+1);
      var cor = this.colors[this.rnd(0, this.colors.length - 1)]
      var evento = {
        name: this.select,
        start: d,
        end: da,
        color: cor,
      }
      var body = {
        treino: this.select,
        data_hora: this.date_1 + ' ' + this.time,
        utilizador: this.data.utilizador,
        cor: cor,
      }
      axios
        .post("http://localhost:4576/api/agenda/novaMarcacao",body,{headers: {'token': localStorage.getItem("token")}})
        .then(response => {
          console.log(response)
        })  
      axios
        .get("http://localhost:4576/api/treinos/getNomeTreino?codigo="+this.select,{headers: {'token': localStorage.getItem("token")}})
        .then(response => {
          evento.name = this.select + " - " + response.data.nome;
          this.events.push(evento); 
        })  
    },
    removeContrato(){
      console.log("wiiiiiiiiiiiiiiiii")
       axios
          .post("http://localhost:4576/api/treinador/removerContrato",
          {username:this.data.utilizador},
          {headers: {'token': localStorage.getItem("token")}}
          )   
          .then((response) => {
              console.log("zzzzzzzzzzzzzzzzzzz"+JSON.stringify(response.data))
              console.log("removeuuuuu")
           })
          .finally(() => (this.loading = false));
    },
    viewDay({ date }) {
      this.focus = date;
      this.type = "day";
    },
    getEventColor(event) {
      return event.color;
    },
    setToday() {
      this.focus = "";
    },
    prev() {
      this.$refs.calendar.prev();
    },
    next() {
      this.$refs.calendar.next();
    },
    showEvent({ nativeEvent, event }) {
      var splited = event.name.split("-",1);
      axios
        .get("http://localhost:4576/api/treinos/getTreinoInfo?codigo="+splited[0],{headers: {'token': localStorage.getItem("token")}})
        .then(response => {
          this.treino_info = response.data
        })
      const open = () => {
        this.selectedEvent = event;
        this.selectedElement = nativeEvent.target;
        requestAnimationFrame(() =>
          requestAnimationFrame(() => (this.selectedOpen = true))
        );
      };

      if (this.selectedOpen) {
        this.selectedOpen = false;
        requestAnimationFrame(() => requestAnimationFrame(() => open()));
      } else {
        open();
      }

      nativeEvent.stopPropagation();
    },
    verTreino(treino_cod){
      this.$router.push("/treinador/treino/" + treino_cod);
    },
    updateRange({ start, end }) {
      /* const events = [];

      const min = new Date(`${start.date}T00:00:00`);
      const max = new Date(`${end.date}T23:59:59`);
      const days = (max.getTime() - min.getTime()) / 86400000;
      const eventCount = this.rnd(days, days + 20);

      for (let i = 0; i < eventCount; i++) {
        const allDay = this.rnd(0, 3) === 0;
        const firstTimestamp = this.rnd(min.getTime(), max.getTime());
        const first = new Date(firstTimestamp - (firstTimestamp % 900000));
        const secondTimestamp = this.rnd(2, allDay ? 288 : 8) * 900000;
        const second = new Date(first.getTime() + secondTimestamp);

        events.push({
          name: this.names[this.rnd(0, this.names.length - 1)],
          start: first,
          end: second,
          color: this.colors[this.rnd(0, this.colors.length - 1)],
          timed: !allDay,
        }); 

      }*/
      console.log(start.toISOString());
      console.log(end.toISOString());
      //this.events = events;
    },
    rnd(a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a;
    },
  },
};
</script>