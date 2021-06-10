<template>
  <div class="procurarTreino">
    <NavBar />
    <v-row>
      <v-col cols="12" md="1">
        <SideBar />
      </v-col>
      <v-col cols="12" md="10">
        <h1 style="padding-top: 100px; text-align: center; font-size: 40px">
          Procure um treino
        </h1>
        <v-container>
          <v-row align="center" justify="space-around">
            <v-col cols="12" md="8">
              <v-toolbar dense rounded class="mx-6">
                <v-text-field
                  v-model="search"
                  hide-details
                  prepend-icon="mdi-magnify"
                  label="Procurar treino"
                  single-line
                  color="#f95738"
                ></v-text-field>
              </v-toolbar>
            </v-col>
          </v-row>
        </v-container>
        <v-container align="center" justify="space-around">
          <v-card>
            <v-data-table
              :headers="headers"
              :items="treinos"
              :search="search"
              
            >
             <template v-slot:item.ver="props">
                <v-btn
                  
                  class="mx-2"
                  icon
                  dark
                  small
                  color="pink"
                  @click="verTreino(props.item)"
                ><v-icon color="#f95738">mdi-text-box-search</v-icon>
                </v-btn>
              </template>
              <template v-slot:item.favoritos="props">
                <v-btn
                  v-if="props.item.favoritos"
                  class="mx-2"
                  icon
                  dark
                  small
                  color="red"
                  @click="onButtonClick(props.item)"
                >
                  <v-icon dark>mdi-heart</v-icon>
                </v-btn>
                <v-btn
                  v-else
                  class="mx-2"
                  icon
                  small
                  @click="onButtonClick(props.item)"
                >
                  <v-icon dark>mdi-heart</v-icon>
                </v-btn>
              </template>
              <template v-slot:item.classificacao="props">
                <div>
                  {{ parseFloat(props.item.classificacao).toFixed(1)
                  }}/5<v-icon color="#f95738">mdi-star</v-icon>
                </div>
              </template></v-data-table
            ></v-card
          ></v-container
        ></v-col
      ></v-row
    >
    <v-btn fab dark large color="#f95738" fixed right bottom to="/criartreino">
      <v-icon>mdi-plus</v-icon>
     </v-btn>
  </div>
</template>

           

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_User.vue";
import axios from "axios";

export default {
  name: "ProcurarTreino",
  components: {
    NavBar,
    SideBar,
  },
  created() {
    document.title = "Treinos";
  },
  data() {
    return {
      search: "",
      headers: [
        { text: "", value: "ver", sortable: false },
        { text: "Nome do Treino", value: "nome" },
        { text: "Duração", value: "duracao" },
        { text: "Categoria", value: "categoria" },
        { text: "Dificuldade", value: "dificuldade" },
        { text: "Criador", value: "criador" },
        { text: "Publicado em", value: "data" },
        { text: "Classificação", value: "classificacao"},
        { text: "Código", value: "codigo" },
        { text: "Favoritos", value: "favoritos" },
      ],
      treinos: [
        {
          nome: "",
          duracao: 0,
          categoria: "",
          dificuldade: "",
          criador: "",
          data: "",
          classificacao: 0,
          favoritos: "",
          codigo: "",
        },
      ],
    };
  },
  methods: {
    verTreino: function (value) {
      console.log("ROW VALUES:", value);
      this.$router.push("/treino/" + value.codigo);
    },
    onButtonClick(props) {
      if(props.favoritos){
        console.log("entrei no true")
        console.log(props);
        axios
          .post("http://localhost:4576/api/treinos/desfavoritar",{treino:props.codigo},{headers: { token: localStorage.getItem("token")}})
          .then(response => {
            console.log(response);
          })
          props.favoritos = false;
      }
      else {
        console.log("parente")
        console.log(props)
        axios
          .post("http://localhost:4576/api/treinos/favoritar",{treino:props.codigo},{headers: { token: localStorage.getItem("token")}})
          .then(response => {
            console.log(response);
          })
          props.favoritos = true;
      }
      
    },
  },
  mounted() {
    axios
      .get("http://localhost:4576/api/treinos/listarUtilizador?username="+localStorage.getItem("username"), {
        headers: { token: localStorage.getItem("token") },
      })
      .then((response) => {
        this.treinos = response.data;
      })
      .finally(() => (this.loading = false));
  },
  computed: {
    categorias: function (item) {
      var s = "";
      for (var i = 0; i < item.length - 1; i++) {
        s += item[i] + ", ";
      }
      s += item[i];
      return s;
    },
  },
};
</script>

<style>
.procurarTreino {
  background-color: #d3d3d2;
}
</style>