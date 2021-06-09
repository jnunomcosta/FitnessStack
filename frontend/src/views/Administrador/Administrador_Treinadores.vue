<template>
  <div class="procurarTreinadores">
    <NavBar />
    <v-row>
      <v-col cols="12" md="1">
        <SideBar />
      </v-col>
      <v-col cols="12" md="10">
        <h1 style="padding-top: 100px; text-align: center; font-size: 40px">
          Procure um treinador
        </h1>

        <v-container>
          <v-row align="center" justify="space-around">
            
            <v-col cols="12" md="8">
              <v-toolbar dense rounded class="mx-6">
                <v-text-field
                  v-model="search"
                  hide-details
                  prepend-icon="mdi-magnify"
                  label="Procurar treinador"
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
            </v-data-table>
          </v-card>
        </v-container>
      </v-col>

      <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on, attrs }">
      <v-btn
        fab
        dark
        large
        fixed
        right
        bottom
        color="#f95738"
        v-bind="attrs"
        v-on="on"
        style="margin-bottom:100px"
      >
        <v-icon>mdi-plus</v-icon>
      </v-btn>
    </template>
    <v-card>
      <v-card-title>
        Adicionar treinador
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-row>
            <v-col cols="12" md="12">
              <v-text-field
              v-model="data.nome"
              label="Nome*" 
              color="#f95738"
              required
              ></v-text-field>
            
              <v-text-field
                v-model="data.username"
                label="Nome de utilizador*"
                color="#f95738"
              ></v-text-field>

              <v-text-field
                v-model="data.email"
                label="Email*"
                color="#f95738"
              ></v-text-field>
            
              <v-text-field
                v-model="data.password"
                label="Password*"
                type="password"
                color="#f95738"
                persistent-hint
                required
              ></v-text-field>
        
            
              <v-text-field
                v-model="data.descricao"
                label="Descrição"
                color="#f95738"
                required
              ></v-text-field>
            </v-col>
            
      
           
          </v-row>
        </v-container>
        <small>* campos obrigatórios</small>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="#f95738" text @click="dialog = false">
          Fechar
        </v-btn>
        <v-btn color="#f95738" text @click="dialog = false"> Criar </v-btn>
      </v-card-actions>
    </v-card>



      </v-dialog>

      <v-dialog v-model="dialog" persistent max-width="600px">
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
        
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_Administrador.vue";
import axios from "axios";

export default {
  name: "ProcurarTreinador",
  components: {
    NavBar,
    SideBar,
  },
  created() {
    document.title = "Treinadores";
  },
   mounted(){
    axios
      .get("http://localhost:4576/api/treinador/listar",{headers: {'token': localStorage.getItem("token")}})      
      .then(response => {
        this.rows= response.data;
        console.log("adasdsadsadhhhhhhhhhhhh"+response.data)

      })
      .catch(e => console.log("erro" +e))

      console.log("dasjdamsjdsnajdzzzzzzzzzzzz");
  
  },
  data() {
    return {
      singleSelect: false,
      selected: [],
      search: "",
      columns: [
        { text: "Nome", value: "nome" },
        { text: "Username", value: "username" },
        { text: "Email", value: "email" },
        { text: "Descrição", value: "descricao" },
      ],
      rows: [

      ],
      data: {
        nome:"",
        username:"",
        email:"",
        password:"",
        descricao:""
      }
    };
  },
  methods: {
    //verTreino: function (value) {
    //  console.log("ROW VALUES:", value);
    // this.$router.push("/treinos/" + value.codigo);
    //},
  },
};
</script>

<style>
.procurarTreinadores {
  background-color: #d3d3d2;
}
</style>