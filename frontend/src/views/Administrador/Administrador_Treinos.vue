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

        <v-alert
          border="left"
          v-if="successDelete"
          text
          dismissible
          elevation="2"
          type="success"
        >
          Treino(s) eliminado(s) com sucesso
        </v-alert>

        <v-alert
          border="left"
          v-if="registerServerError"
          text
          dismissible
          elevation="2"
          type="error"
        >
          Erro do servidor
        </v-alert>

         <v-alert
          border="left"
          v-if="forbiddenError"
          text
          dismissible
          elevation="2"
          type="error"
        >
          Não é permitido apagar este(s) treino(s)
        </v-alert>

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
              v-model="selected"
              :headers="columns"
              :items="rows"
              :single-select="singleSelect"
              show-select
              item-key="codigo"
              :search="search"
              >
              <template v-slot:item.categoria="props">
                <v-chip
                    class="mr-1"
                    v-for="categoria in props.item.categoria"
                    :key="categoria"
                    >{{ categoria }}</v-chip>
              </template>

              <template v-slot:item.classificacao="props">
                <div>
                  {{ parseFloat(props.item.classificacao).toFixed(1)
                  }}/5<v-icon color="#f95738">mdi-star</v-icon>
                </div>
              </template>
            </v-data-table>
            
          </v-card>
        </v-container>
        
      </v-col>
      
            <v-dialog v-model="dialogDelete" persistent max-width="300">
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            fab
            dark
            large
            color="#f95738"
            fixed
            right
            bottom
            v-bind="attrs"
            v-on="on"
          >
            <v-icon>mdi-delete</v-icon>
          </v-btn>
        </template>
        <v-card>
          <v-card-title class="error white--text">
            Apagar Treino(s)
          </v-card-title>
          <v-card-text class="mt-4"
            >Deseja apagar o(s) treino(s)? Esta ação é
            irreversível!</v-card-text
          >
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="error" text @click="dialogDelete = false">
              Cancelar
            </v-btn>
            <v-btn color="error" dark @click="confirmar_apagar()">
              Confirmar
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    
     
    </v-row>
    
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_Administrador.vue";
import axios from 'axios';

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
      dialogDelete: false,
      successDelete: false,
      registerServerError: false,
      forbiddenError: false,
      singleSelect: false,
      dialog: false,
      selected: [],
      search: "",
      columns: [
        { text: "Nome do Treino", value: "nome" },
        { text: "Duração", value: "duracao" },
        { text: "Categoria", value: "categoria" },
        { text: "Dificuldade", value: "dificuldade" },
        { text: "Criador", value: "criador" },
        { text: "Publicado em", value: "data" },
        { text: "Classificação", value: "classificacao"},
        { text: "Código", value: "codigo" }
      ],
      rows: [
      ],
    };
  },
  mounted() {
    axios
      .get(process.env.VUE_APP_BASELINK+'/api/treinos/listar',{headers: {'token': localStorage.getItem("token")}})
      .then(response => {
        this.rows = response.data 
        console.log(JSON.stringify(this.rows))
      })
  },
  methods: {
    confirmar_apagar() {
      this.apagarTreino();
      this.dialogDelete = false;
    },
    apagarTreino(){
      let deletbody = [];
      this.selected.forEach(element => {
        deletbody.push(element.codigo);
      });
      axios 
        .delete(process.env.VUE_APP_BASELINK+'/api/treinos/deleteTreino',{headers:{token: localStorage.getItem("token")},data:deletbody})
        .then(response => {
          if(response.status == 200){
            this.successDelete = true;
            setTimeout(() => {
              this.successDelete = false;
            }, 5000);
            this.$router.go();
          }
        })
        .catch((error) => {
          if (error.response != null) {
            if (error.response.status == "500") {
              this.forbiddenError = true;
              setTimeout(() => {
                this.forbiddenError = false;
              }, 5000);
            } else {
              this.registerServerError = true;
              setTimeout(() => {
                this.registerServerError = false;
              }, 5000);
            }
          }
        });
    },
  },
};
</script>

<style>
.procurarTreino {
  background-color: #d3d3d2;
}
</style>