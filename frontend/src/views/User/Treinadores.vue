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
                  :src="linkapi() + treinador.foto_perfil"
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
                       :value=treinador.classsificacao
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
                  <span  v-if="!contrato.estado">  Pendente</span>
                  <span v-else>   Ativo </span>
                  </v-card-title>
                <v-btn  color="#f95738" text @click="cancelar();dialog.value = false">
                  Cancelar contrato
                </v-btn>


          <v-container class="text-center">
            <v-dialog v-model="dialog1" persistent max-width="380px">
              <template v-slot:activator="{ on, attrs }">

                <v-btn  v-if="contrato.estado" v-bind="attrs" v-on="on" color="#f95738" text >
                  Avaliar Treinador
                </v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <span class="headline text-center"
                    >Dê-nos o seu feedback acerca deste treinador!</span
                  >
                </v-card-title>
                <v-card-text>
                  <div class="text-center mt-12">
                    <v-rating
                      v-model="rating"
                      color="#f95738"
                      background-color="grey darken-1"
                      empty-icon="$ratingFull"
                      half-increments
                      hover
                      large
                    ></v-rating>
                    <v-text-field
                      color="#f95738"
                      v-model="comentario"
                      label="Deixe-nos o seu comentário."
                      required
                    >
                    </v-text-field>
                  </div>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="#f95738" text @click="dialog1 = false">Sair</v-btn>
                  <v-btn color="#f95738" text @click="dialog1 = false" v-on:click="submit()">
                    Confirmar
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-container>
         
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

      dialog1:false,
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
          process.env.VUE_APP_BASELINK+"/api/user/getTreinadorResp", {headers: {'token': localStorage.getItem("token")}}
        )
        .then((response) => {
              this.contrato=response.data
              console.log(JSON.stringify(this.contrato));

            axios
              .get(
              process.env.VUE_APP_BASELINK+"/api/treinador/getTreinadorInfo?username="+this.contrato.treinador, {headers: {'token': localStorage.getItem("token")}}
              )
              .then( (responsetreinador) =>{

                  this.treinador=responsetreinador.data
                  console.log("abatatatas"+JSON.stringify(responsetreinador.data))
              })




        })
        .finally(() => console.log("hi"));//msg erro a mudar email));*/



  }
  ,methods: {
    linkapi(){
      return process.env.VUE_APP_BASELINK
    },
    cancelar(){

      axios
      .post(process.env.VUE_APP_BASELINK+"/api/user/removerContrato",
      {}
      ,
      {headers: {'token': localStorage.getItem("token")}})

      .then((response) => {
        
      console.log("sucesso")

       console.log("dkansdjnsadjnsa"+ JSON.stringify(response.data))
      })
      .finally(() => (console.log("dsadsa")));
    },
     submit(){
      //var cod_treino = this.$route.params.codigo;
      console.log(this.rating);
      console.log(this.comentario);
      var post_body = {
        username: localStorage.getItem("username"),
        treinador: this.contrato.treinador,
        avaliacao: this.rating,
        comentario: this.comentario,
      };


  console.log("hdsaihdsaidddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd")
      axios
        .post(process.env.VUE_APP_BASELINK + "/api/treinador/avaliacao",post_body,{headers: {'token': localStorage.getItem("token")}})
        .then(response => {
          console.log("ORA BOM DIA"+JSON.stringify(response.data));
        });
      

      this.treino.avaliacoes.push({
        username: localStorage.getItem("username"),
        avaliacao: this.rating,
        comentario: this.comentario,
      });

      
     }
  }
};
</script>

<style>
.treinadores {
  background-color: #d3d3d2;
}
</style>