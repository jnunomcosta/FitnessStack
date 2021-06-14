<template>
  <div>
    <v-toolbar dense rounded class="mx-6" style="margin-top: 40px">
      <v-text-field
        v-model="searchValue"
        @input="searchbarInput()"
        hide-details
        prepend-icon="mdi-magnify"
        label="Procurar aluno"
        single-line
        color="#f95738"
      ></v-text-field>
    </v-toolbar>
    <v-container>
      <h5 class="mb-4 ml-8" style="color: #5b5b5b">
        <span v-text="total_alunos"></span> de
        <span v-text="total"></span> alunos
      </h5>
      <v-row class="fill-height overflow-y-auto" v-if="alunos.length">
        <v-col
          lg="3"
          md="4"
          sm="6"
          cols="12"
          v-for="(title, index) in alunos"
          :key="index"
        >
          <v-sheet min-height="150px" class="fill-height" color="transparent">
            <v-card hover class="white">
              <v-img
                :src="linkfoto() + title.foto_utilizador"
                class="white--text align-end"
                height="150px"
              >
              </v-img>
              <v-card-title>{{ title.utilizador }}</v-card-title>
              <v-card-actions>
                <v-spacer></v-spacer>

                <InfoAluno :data="title" />
              </v-card-actions>
            </v-card>
          </v-sheet>
        </v-col>
      </v-row>
      <v-row v-else-if="total == 0">
        <v-col class="text-center">
          <h5 style="padding-top: 150px; padding-bottom: 400px; color: #5b5b5b">
            Não existem alunos associados
          </h5>
        </v-col>
      </v-row>
      <v-row v-else>
        <v-col class="text-center">
          <h5 style="padding-top: 150px; padding-bottom: 400px; color: #5b5b5b">
            Não foi encontrado nenhum aluno
          </h5>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
import InfoAluno from "./InfoAluno.vue";

export default {
  name: "Aluno",
  components: {
    InfoAluno,
  },
  data() {
    return {
      titles: [],
      page: 1,
      total: 0,
      searchValue: "",
      alunos: [],
      alunosbackup: [],
      total_alunos: 0,
    };
  },
  mounted() {
    this.pullData();
    this.getDataTotal();
  },
  computed: { 
    visibleAlunos() {
      return this.alunos.filter((p) => p.isActive).length;
    },
  },
  methods: {
    linkfoto() {
      return process.env.VUE_APP_BASELINK;
    },
    searchbarInput() {
      const searchContent = this.searchValue;
      console.log(searchContent);
      if (searchContent == "") {
        this.alunos = this.alunosbackup;
        this.total_alunos = this.alunosbackup.length;
      } else {
        this.alunos = [];
        this.total_alunos = 0;
        this.getDataFiltered(searchContent);
      }
    },
    pullData() {
      axios
        .get(process.env.VUE_APP_BASELINK + "/api/treinador/getAlunosAtivos", {
          headers: { token: localStorage.getItem("token") },
        })
        .then((response) => {
          if (response.status == 200) {
            response.data.forEach((x) => {
              this.alunos.push(x);
              this.alunosbackup.push(x);
              this.total_alunos++;
            });
          }
        });
    },
    getDataTotal() {
      axios
        .get(
          process.env.VUE_APP_BASELINK + "/api/treinador/getAlunosAtivosNumber",
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response) => {
          console.log(response);
          if (response.status == 200) {
            this.total = response.data.numero;
          }
        });
    },
    getDataFiltered(filtro) {
      axios
        .get(
          process.env.VUE_APP_BASELINK +
            "/api/treinador/getAlunosAtivosFiltrado?filtro=" +
            filtro,
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response) => {
          if (response.status == 200) {
            response.data.forEach((x) => {
              this.alunos.push(x);
              //this.exerciciosBackup.push(x);
              this.total_alunos++;
            });
          }
        });
    },
  },
};
</script>
