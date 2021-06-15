<template>
  <v-container class="ma-0 pa-0">
    <v-card>
      <v-card-title class="justify-center" style="color: #f95738"
        >Agenda de Treinos</v-card-title
      >
      <v-divider></v-divider>

      <v-alert class="ma-4"
            border="left"
            v-if="success"
            text
            dismissible
            elevation="2"
            type="success"
          >
            Treino agendado com sucesso
          </v-alert>
          <v-alert
            class="ma-4"
            border="left"
            v-if="error"
            text
            dismissible
            elevation="2"
            type="error"
          >
            Erro ao agendar treino
          </v-alert>

      <v-row class="pa-10">
        <v-col cols="12" md="8">
          <template>
            <v-sheet>
              <v-toolbar flat class="pl-6">
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
                    <h4>
                      Duração: {{ treino_info.duracao }}
                    </h4>
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
        <v-col cols="12" md="1"> </v-col>
        <v-col cols="12" md="3" class="mt-16">
          <v-form ref="agendaForm">
            <v-autocomplete
              label="Código Treino"
              v-model="select"
              :rules="formRules"
              :items="items"
              color="#f95738"
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
                    :rules="dateRules"
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
                  <v-btn text color="#7189ff" @click="$refs.menu.save(date_1)">
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
                  <v-btn text color="#7189ff" @click="$refs.dialog.save(time)">
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
      </v-row>
    </v-card>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "AgendarTreino",
  data: () => ({
    formRules: [(v) => !!v || "Campo obrigatório"],
    dateRules: [
      (v) => !!v || "Campo obrigatório",
      (v) => Math.abs(new Date(v) - new Date()) > 0 || "Data inválida",
    ],
    error: false,
    success: false,
    select: "",
    items: [],
    time: null,
    menu2: false,
    modal2: false,
    date: "",
    date_1: new Date().toISOString().substr(0, 10),
    menu_1: false,
    modal_1: false,
    menu2_1: false,
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
    treino_info: {},
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
  mounted() {
    //this.$refs.calendar.checkChange();
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
          localStorage.getItem("username"),
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
          console.log(objjjj);
          this.events.push(objjjj);
        });
        console.log(this.events);
      });
  },
  methods: {
    validateForm() {
      if (this.$refs.agendaForm.validate()) {
        this.confirmar();
      }
    },
    viewDay({ date }) {
      this.focus = date;
      this.type = "day";
    },
    verTreino(cod) {
      this.$router.push("/treino/" + cod);
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
        utilizador: localStorage.getItem("username"),
        cor: cor,
      };
      axios
        .post(process.env.VUE_APP_BASELINK + "/api/agenda/novaMarcacao", body, {
          headers: { token: localStorage.getItem("token") },
        })
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
    updateRange({ start, end }) {
      console.log(start.toISOString());
      console.log(end.toISOString());
    },
    rnd(a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a;
    },
  },
};
</script>
