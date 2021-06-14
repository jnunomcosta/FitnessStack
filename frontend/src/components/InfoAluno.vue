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
        <v-card color="#d3d3d2">
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
            <v-card class="px-10">
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
                    ></v-calendar>
                    <v-menu
                      v-model="selectedOpen"
                      :close-on-content-click="false"
                      :activator="selectedElement"
                      offset-x
                    >
                      <v-card color="grey lighten-4" min-width="200px" flat>
                        <v-toolbar :color="selectedEvent.color" dark>
                          <v-toolbar-title
                            v-html="treino_info.nome"
                          ></v-toolbar-title>
                          <v-spacer></v-spacer>
                          <v-btn icon @click="verTreino(treino_info.codigo)">
                            <v-icon>mdi-text-box-search</v-icon>
                          </v-btn>
                        </v-toolbar>
                        <v-card-text>
                          <h4>Dificuldade: {{ treino_info.dificuldade }}</h4>
                          <h4 v-if="treino_info.duracao / 60 < 1">
                            Duração: {{ treino_info.duracao }}s
                          </h4>
                          <h4 v-else>
                            Duração:
                            {{
                              parseFloat(treino_info.duracao / 60).toFixed(0)
                            }}min
                          </h4>
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
                      </v-card>
                    </v-menu>
                  </v-sheet>
                </template>
              </v-col>
            </v-card>
            <v-card>
              <v-col cols="12" md="12" class="mt-16">
                <v-form ref="agendaForm">
                <v-autocomplete
                  label="Código Treino"
                  v-model="select"
                  :rules="formRules"
                  color="#f95738"
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
                        :rules="dateRules"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-date-picker
                      v-model="date_1"
                      color="#7189ff"
                      :rules="dateRules"
                      :min="new Date().toISOString().slice(0, 10)"
                      no-title
                      scrollable
                    >
                      <v-spacer></v-spacer>
                      <v-btn text color="#7189ff" @click="menu_1 = false">
                        Cancelar
                      </v-btn>
                      <v-btn
                        text
                        color="#7189ff"
                        @click="$refs.menu.save(date_1)"
                      >
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
                        :rules="formRules"
                        color="#f95738"
                        v-bind="attrs"
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-time-picker
                      v-if="modal2"
                      color="#7189ff"
                      v-model="time"
                      full-width
                    >
                      <v-spacer></v-spacer>
                      <v-btn text color="#7189ff" @click="modal2 = false">
                        Cancelar
                      </v-btn>
                      <v-btn
                        text
                        color="#7189ff"
                        @click="$refs.dialog.save(time)"
                      >
                        OK
                      </v-btn>
                    </v-time-picker>
                  </v-dialog>
                </template>
                <div class="text-center mt-10">
                  <v-btn color="#f95738" v-on:click="validateForm()" dark>
                    Confirmar
                  </v-btn>
                </div>
                </v-form>
              </v-col>
            </v-card>
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
        <v-card color="#d3d3d2">
          <v-toolbar color="#f95738" dark
            ><h3>{{ utilizador.nome }}</h3>
            <v-spacer></v-spacer>
            <v-btn icon @click="dialog.value = false"
              ><v-icon>mdi-close</v-icon></v-btn
            >
          </v-toolbar>
          <v-row class="ma-6">
            <v-col cols="12" md="2">
              <v-card style="text-align: center">
                <div class="mx-auto text-center">
                  <v-avatar class="mt-4" size="150">
                    <v-img :src="linkfoto() + utilizador.foto_perfil"></v-img>
                  </v-avatar>
                </div>
                <v-card-title>{{ utilizador.nome }} </v-card-title>
                <v-card-subtitle> {{ utilizador.username }} </v-card-subtitle>
                <v-divider class="mx-4"></v-divider>
                <v-card-text class="py-4 black--text">{{
                  utilizador.email
                }}</v-card-text>
                <v-btn
                  color="#f95738"
                  text
                  @click="
                    removeContrato();
                    dialog.value = false;
                  "
                >
                  Terminar contrato
                </v-btn>
              </v-card>
            </v-col>

            <v-col cols="12" md="5">
              <v-card>
                <v-card-title class="justify-center">
                  <span style="color: #f95738">Informação Física</span>
                </v-card-title>
                <v-divider></v-divider>
                <v-row class="mt-0">
                  <v-col cols="12" md="6">
                    <v-card-text>
                      <p style="font-size: 20px">
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
              </v-card>
            </v-col>
            <v-col cols="12" md="5">
              <v-card class="mx-auto text-center">
                <v-card-title class="justify-center">
                  <span style="color: #f95738"
                    >Peso, Massa Muscular e Massa Gorda</span
                  >
                </v-card-title>
                <v-divider></v-divider>
                <div id="chart">
                  <line-chart :data="utilizador" />
                </div>
              </v-card>
            </v-col>
          </v-row>
        </v-card>
      </template>
    </v-dialog>
  </v-container>
</template>

<script>
import LineChart from "@/components/InfoFisicaGrafico.vue";
import axios from "axios";

export default {
  name: "InfoAluno",
  components: {
    LineChart,
  },
  computed: {
    verifyIMC() {
      return this.imc >= 18.5 && this.imc <= 24.9;
    },
  },
  data: () => ({
     formRules: [(v) => !!v || "Campo obrigatório"],
    dateRules: [
      (v) => !!v || "Campo obrigatório",
      (v) => Math.abs(new Date(v) - new Date()) > 0 || "Data inválida",
    ],
    utilizador: null,
    imc: 0,
    select: "",
    items: [],
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
  props: ["data"],
  mounted() {
    axios
      .get(process.env.VUE_APP_BASELINK + "/api/treinos/getCodigos", {
        headers: { token: localStorage.getItem("token") },
      })
      .then((response) => {
        this.items = response.data;
      });
    axios
      .get(
        process.env.VUE_APP_BASELINK +
          "/api/agenda/getAgenda?username=" +
          this.data.utilizador,
        { headers: { token: localStorage.getItem("token") } }
      )
      .then((response) => {
        console.log(response.data);
        response.data.forEach((x) => {
          var objjjj = {
            name: x.name,
            start: new Date(x.start),
            end: new Date(x.end),
            color: x.color,
          };
          this.events.push(objjjj);
        });
        console.log(this.events);
      });

    axios
      .get(
        process.env.VUE_APP_BASELINK +
          "/api/user/getUser?username=" +
          this.data.utilizador,
        { headers: { token: localStorage.getItem("token") } }
      )
      .then((response) => {
        this.utilizador = response.data;
        //console.log("zzzzzzzzzzzzzzzzzzz"+JSON.stringify(this.utilizador))
        //console.log("dkansdjnsadjnsa"+ JSON.stringify(this.utilizador))
        var valor =
          (this.utilizador.peso /
            (this.utilizador.altura * this.utilizador.altura)) *
          10000;
        this.imc = parseFloat(valor).toFixed(1);
      });

    this.$refs.calendar.checkChange();
  },
  methods: {
    validateForm() {
      if (this.$refs.agendaForm.validate()) {
        this.confirmar();
      }
    },
    linkfoto() {
      return process.env.VUE_APP_BASELINK;
    },
    confirmar() {
      var d = new Date(this.date_1 + " " + this.time);
      console.log(d);
      var da = new Date(d);
      da.setHours(da.getHours() + 1);
      var cor = this.colors[this.rnd(0, this.colors.length - 1)];
      var evento = {
        name: this.select,
        start: d,
        end: da,
        color: cor,
      };
      var body = {
        treino: this.select,
        data_hora: this.date_1 + " " + this.time,
        utilizador: this.data.utilizador,
        cor: cor,
      };
      axios
        .post(process.env.VUE_APP_BASELINK + "/api/agenda/novaMarcacao", body, {
          headers: { token: localStorage.getItem("token") },
        })
        .then((response) => {
          console.log(response);
        });
      axios
        .get(
          process.env.VUE_APP_BASELINK +
            "/api/treinos/getNomeTreino?codigo=" +
            this.select,
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response) => {
          evento.name = this.select + " - " + response.data.nome;
          this.events.push(evento);
        });
    },
    async removeContrato() {
      //console.log("entrei no coiso")
      var response = await axios.post(
        process.env.VUE_APP_BASELINK + "/api/treinador/removerContrato",
        { username: this.data.utilizador },
        { headers: { token: localStorage.getItem("token") } }
      );
      if (response.status == 200) {
        this.$router.go();
      }
      /* axios
        .post(process.env.VUE_APP_BASELINK+"/api/treinador/removerContrato",
        {username:this.data.utilizador},
        {headers: {'token': localStorage.getItem("token")}}
        )   
        .then((response) => {
            console.log(JSON.stringify(response.data))
        
          }) */
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
      var splited = event.name.split("-", 1);
      axios
        .get(
          process.env.VUE_APP_BASELINK +
            "/api/treinos/getTreinoInfo?codigo=" +
            splited[0],
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response) => {
          this.treino_info = response.data;
        });
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
    verTreino(treino_cod) {
      this.$router.push("/treino/" + treino_cod);
    },
    rnd(a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a;
    },
  },
};
</script>
