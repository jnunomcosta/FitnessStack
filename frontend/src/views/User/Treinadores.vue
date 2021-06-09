<template>
  <div class="treinadores">
    <NavBar />
    <v-row>
      <v-col cols="12" md="1">
        <SideBar />
      </v-col>
      <v-col cols="12" md="10" style="padding-top: 90px">
        <v-dialog transition="dialog-bottom-transition" max-width="700">
          <template v-slot:activator="{ on, attrs }">
            <v-btn 
              v-model="butao_treinador"
              v-show="treinador!=null"
              class="mx-6"
              v-bind="attrs"
              v-on="on"
              color="#f95738"
              dark>
              O meu treinador
            </v-btn>
          </template>
          <template v-slot:default="dialog">
            <v-card>
              <v-toolbar color="#f95738" dark
                ><h3>{{ treinador.nome}}</h3>
                <v-spacer></v-spacer>
                <v-btn icon @click="dialog.value = false"
                  ><v-icon>mdi-close</v-icon></v-btn
                >
              </v-toolbar>

              <div class="d-flex flex-no-wrap justify-space-between">
                <div>
                  <v-avatar class="ma-3" width="250" height="300" tile>
                    <v-img
                  :src="'http://localhost:4576' + treinador.foto_perfil"
                ></v-img>
                  </v-avatar>
                </div>
                <div class="ma-2">
                  <v-card-title>{{ treinador.nome}}
                    <v-icon dense color="#f95738" class="ml-2">mdi-weight-lifter</v-icon>
                  </v-card-title>
                  <v-card-subtitle>{{ treinador.username}}</v-card-subtitle>

                  <v-card-text>
                    <v-row align="center" class="mx-0">
                      <div class="body-2">{{ treinador.email}}</div>
                      <v-spacer></v-spacer>
                    </v-row>
                    <div style="margin-top: 14px">
                      <v-rating
                        v-model="rating"
                        color="yellow darken-3"
                        background-color="grey darken-1"
                        empty-icon="$ratingFull"
                        half-increments
                        hover
                        readonly
                      ></v-rating>
                      <v-spacer></v-spacer>
                    </div>

                    <v-divider></v-divider>
                    <div class="my-4">
                      {{treinador.descricao}}
                    </div>
                  </v-card-text>
                </div>
              </div>
              <v-card-actions>
                <v-spacer></v-spacer>

                <v-card-title>Estado: 
                  <span  v-if="!estado">  Pendente</span>
                  <span v-else>   Ativo </span>
                  </v-card-title>
                <v-btn  color="#f95738" text @click="cancelar();dialog.value = false">
                  Cancelar contrato
                </v-btn>

              </v-card-actions>
            </v-card>
          </template>
        </v-dialog>

        <v-container class="pa-0 ma-0">
          <v-row align="center" justify="space-around">
            <v-col cols="12" md="12">
              <v-toolbar dense rounded class="mx-6" style="margin-top: 40px">
                <v-text-field
                  hide-details
                  prepend-icon="mdi-magnify"
                  label="Procurar treinador"
                  single-line
                  color="#f95738"
                ></v-text-field>
                <v-btn icon>
                  <v-icon>mdi-filter</v-icon>
                </v-btn>
              </v-toolbar>
            </v-col>
          </v-row>
          <Treinador
        /></v-container>
      </v-col>
    </v-row>
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_User.vue";
import Treinador from "@/components/Treinador.vue";


import axios from "axios";


export default {
  name: "Treinadores",
  data: () => ({
      contrato:{
       
        "utilizador": "",
        "estado": false,
        "treinador": "",
        "comentario": ""

      },
      treinador:null
      /*treinador:{
          "foto_perfil": "",
          "nome": "",
          "email":"",
          "username": "",
          "descricao":""

      }*/
  }),
  components: {
    NavBar,
    SideBar,
    Treinador,
  },
  created() {
    document.title = "Treinadores";
  },
  mounted(){
    
    
      axios
        .get(
          "http://localhost:4576/api/user/getTreinadorResp", {headers: {'token': localStorage.getItem("token")}}
        )
        .then((response) => {
              this.contrato=response.data
              console.log(JSON.stringify(this.contrato));

            axios
              .get(
              "http://localhost:4576/api/treinador/getTreinadorInfo?username="+this.contrato.treinador, {headers: {'token': localStorage.getItem("token")}}
              )
              .then( (responsetreinador) =>{

                  this.treinador=responsetreinador.data
                  console.log("abatatatas"+JSON.stringify(responsetreinador.data))
              })




        })
        .finally(() => console.log("hi"));//msg erro a mudar email));*/



  }
  ,methods: {

    cancelar(){

      axios
      .post("http://localhost:4576/api/user/removerContrato",
      {}
      ,
      {headers: {'token': localStorage.getItem("token")}})

      .then((response) => {
        
      console.log("sucesso")

       console.log("dkansdjnsadjnsa"+ JSON.stringify(response.data))
      })
      .finally(() => (console.log("dsadsa")));
    }
  }
};
</script>

<style>
.treinadores {
  background-color: #d3d3d2;
}
</style>