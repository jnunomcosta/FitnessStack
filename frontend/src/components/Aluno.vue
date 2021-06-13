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
                <v-btn icon>
                  <v-icon>mdi-filter</v-icon>
                </v-btn>
              </v-toolbar>
  <v-container>
    <!-- <h5 class="mb-4 ml-8 " style="color: #5b5b5b">
      {{ titles.length }} alunos
    </h5> -->
    <h5 class="mb-4 ml-8" style="color: #5b5b5b">
      <span v-text="visibleAlunos"></span> de
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
            <!--<v-card-title v-text="title.body"></v-card-title>-->
            <v-card-title>{{ title.utilizador }}</v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>

              <InfoAluno  :data="title"/>

            </v-card-actions>
          </v-card>
          <v-card v-intersect="infiniteScrolling"></v-card>
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>
</div>
</template>

<script>
import axios from "axios";
import InfoAluno from "./InfoAluno.vue"

export default {
  name: "Aluno",
  components:{
    InfoAluno
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
    /* url() {
      return process.env.VUE_APP_BASELINK+"/api/treinador/getAlunosAtivos"
    }, */
    visibleAlunos() {
      return this.alunos.filter(p=>p.isActive).length;
    },
  },/* 
  created() {
    this.fetchData();
  }, */
  methods: {
    /* async fetchData() {
      const response = await axios.get(this.url, {headers: {'token': localStorage.getItem("token")}});
      this.titles = response.data;
    }, */
    linkfoto(){
      return  process.env.VUE_APP_BASELINK
    },
    searchbarInput(){
      const searchContent = this.searchValue;
      console.log(searchContent);
      if(searchContent == ""){
        this.alunos = this.alunosbackup;
        this.total_alunos = this.alunosbackup.length;
      }
      else{
        this.alunos = [];
        this.total_alunos = 0;
        this.getDataFiltered(searchContent);
      }
    },
    pullData() {
         axios.get(process.env.VUE_APP_BASELINK+"/api/treinador/getAlunosAtivos",{headers: { token: localStorage.getItem("token")}})
        .then(response => {
          if(response.status==200){
            response.data.forEach(x => {
              this.alunos.push(x);
              this.alunosbackup.push(x);
              this.total_alunos++;
            })
          }
         })
    },
    getDataTotal() {
      axios.get(process.env.VUE_APP_BASELINK+"/api/treinador/getAlunosAtivosNumber",{headers: { token: localStorage.getItem("token")}})
         .then(response => {
           console.log(response);
          if(response.status==200){
            this.total=response.data.numero;
          }
         })
    },
    getDataFiltered(filtro){
      axios.get(process.env.VUE_APP_BASELINK+"/api/treinador/getAlunosAtivosFiltrado?filtro="+filtro,{headers: { token: localStorage.getItem("token")}})
        .then(response => {
          if(response.status==200){
            response.data.forEach(x => {
              this.alunos.push(x);
              //this.exerciciosBackup.push(x);
              this.total_alunos++;
            })
          }
         })
    },
  },
};
</script>
