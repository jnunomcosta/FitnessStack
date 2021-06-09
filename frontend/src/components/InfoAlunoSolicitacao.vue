<template>
  <v-dialog transition="dialog-bottom-transition" max-width="600">
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        class="mx-2 my-2"
        v-bind="attrs"
        v-on="on"
        small
        color="#f95738"
        dark
      >
        VER MAIS
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

        <div class="d-flex flex-no-wrap justify-space-between">
          <div>
            <v-avatar class="ma-3" width="250" height="300" tile>
              <v-img
                 :src="'http://localhost:4576' + utilizador.foto_perfil"
              ></v-img>
            </v-avatar>
          </div>
          <div class="ma-2">
            <v-card-title
              >{{utilizador.nome}}<v-icon dense color="#f95738" class="ml-2"
                >mdi-weight-lifter</v-icon
              ></v-card-title
            >
            <v-card-subtitle>{{ utilizador.username  }} </v-card-subtitle>

            <v-card-text>
              <v-row align="center" class="mx-0">
                <div class="body-2">{{ utilizador.email }} </div>
              </v-row>


              <v-divider></v-divider>
              <div class="my-4">
                {{ data.comentario}} 
              </div>
            </v-card-text>
          </div>
        </div>
        
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="#f95738" text @click="dialog2 = false">
                      Recusar contrato
                    </v-btn>
                    <v-btn color="#f95738" text @click="dialog2 = false">
                      Aceitar contrato
                    </v-btn>
                  </v-card-actions>
          
              
      </v-card>
    </template>
  </v-dialog>
</template>




<script>
import axios from "axios";  
export default {
  name: "InfoAlunoSolicitacao",
  props:["data"],
  data: () => ({

    utilizador:null
  }),
  mounted(){
   
    axios
      .get("http://localhost:4576/api/user/getUser?username="+this.data.utilizador,{headers: {'token': localStorage.getItem("token")}})
      .then((response) => {
        
        this.utilizador = response.data;
console.log("hellooo"+JSON.stringify(this.data))
       console.log("dkansdjnsadjnsa"+ JSON.stringify(this.utilizador))
      })
      .finally(() => (this.loading = false));

       
  }

};
</script>