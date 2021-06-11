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
              item-key="username"
              :search="search"
            >
            </v-data-table>
          </v-card>
        </v-container>
      </v-col>

      <v-dialog v-model="dialog1" persistent max-width="600px">
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
            style="margin-bottom: 100px"
          >
            <v-icon>mdi-plus</v-icon>
          </v-btn>
        </template>
        <v-card>
          <v-card-title> Adicionar treinador </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col cols="12" md="12">
                  <v-form class="px-16  mt-6">
                    <v-text-field
                      label="Nome Completo*"
                      name="Name"
                      v-model="input_register.nome"
                      prepend-icon="mdi-account"
                      type="text"
                      color="#f95738"
                      required
                    />
                    <v-menu
                      ref="menu"
                      v-model="menu"
                      :close-on-content-click="false"
                      transition="scale-transition"
                      offset-y
                      min-width="auto"
                    >
                      <template v-slot:activator="{ on, attrs }">
                        <v-text-field
                          v-model="input_register.date"
                          label="Data de Nascimento*"
                          prepend-icon="mdi-calendar"
                          readonly
                          v-bind="attrs"
                          v-on="on"
                          color="#f95738"
                          required
                        ></v-text-field>
                      </template>
                      <v-date-picker
                        v-model="input_register.date"
                        no-title
                        scrollable
                        color="#7189ff"
                      >
                        <v-spacer></v-spacer>
                        <v-btn text color="#f95738" @click="menu = false">
                          Cancelar
                        </v-btn>
                        <v-btn
                          text
                          color="#f95738"
                          @click="$refs.menu.save(input_register.date)"
                        >
                          OK
                        </v-btn>
                      </v-date-picker>
                    </v-menu>
                    <v-text-field
                      label="Email*"
                      name="Email"
                      v-model="input_register.email"
                      prepend-icon="mdi-email"
                      type="text"
                      color="#f95738"
                      required
                    />
                    <v-text-field
                      label="Nome de Utilizador*"
                      name="Username"
                      v-model="input_register.username"
                      prepend-icon="mdi-account-lock"
                      type="text"
                      color="#f95738"
                      required
                    />
                    <v-text-field
                      id="password"
                      label="Palavra-Passe*"
                      name="password"
                      v-model="input_register.password"
                      prepend-icon="mdi-lock"
                      type="password"
                      color="#f95738"
                      required
                    /><v-select
                      :items="['Masculino', 'Feminino']"
                      v-model="input_register.genero"
                      label="Género"
                      color="#7189ff"
                      required
                    ></v-select>
                    <v-text-field
                      label="Breve descrição"
                      name="descricao"
                      v-model="input_register.descricao"
                      type="text"
                      color="#f95738"
                      required
                    />
                    <v-file-input
                      id="imagem"
                      v-model="input_register.imagem"
                      label="Imagem de Perfil"
                      name="imagem_perfil"
                      prepend-icon="mdi-camera"
                    />
                    
                  </v-form>
                </v-col>
              </v-row>
            </v-container>
            <small>* campos obrigatórios</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="#f95738" text @click="dialog1 = false"> Fechar </v-btn>
            <v-btn
              color="#f95738"
              text
              @click="dialog1 = false"
              v-on:click="registar()"
            >
              Registar
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <v-btn
        fab
        dark
        large
        color="#f95738"
        fixed
        right
        bottom
        v-bind="attrs"
        v-on:click="apagarTreinador()"
      >
        <v-icon>mdi-delete</v-icon>
      </v-btn>
    </v-row>
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_Administrador.vue";
import axios from "axios";
import sjcl from "sjcl";

export default {
  name: "ProcurarTreinador",
  components: {
    NavBar,
    SideBar,
  },
  created() {
    document.title = "Treinadores";
  },
  mounted() {
    axios
      .get(process.env.VUE_APP_BASELINK+"/api/treinador/listar", {
        headers: { token: localStorage.getItem("token") },
      })
      .then((response) => {
        this.rows = response.data;
        //console.log("adasdsadsadhhhhhhhhhhhh" + response.data);
      })
      .catch((e) => console.log("erro" + e));

    //console.log("dasjdamsjdsnajdzzzzzzzzzzzz");
  },
  data() {
    return {
      dialog1: false,
      dialog: false,
      menu: false,
      singleSelect: false,
      selected: [],
      search: "",
      columns: [
        { text: "Nome", value: "nome" },
        { text: "Username", value: "username" },
        { text: "Email", value: "email" },
        { text: "Descrição", value: "descricao" },
      ],
      rows: [],
      input_register: {
        username: "",
        password: "",
        email: "",
        nome: "",
        idade: 0,
        genero: "",
        foto_perfil: "",
        descricao: ""
      },
    };
  },
  methods: {
    apagarTreinador(){
      let deletbody = [];
      this.selected.forEach(element => {
        deletbody.push(element.username);
      });
      console.log(deletbody);
      axios 
        .delete(process.env.VUE_APP_BASELINK+'/api/treinador/deleteTreinador',{headers:{token: localStorage.getItem("token")},data:deletbody})
        .then(response => {
          //ERRO 500 -> nao conseguiu eliminar, pode ter feito um comentario ou ter marcacoes ou um treinador associado
          if(response.status == 200){
            this.$router.push("/administrador/treinadores/");
          }
        }) 
    },
    async registar() {
      function carrega_foto(x) {
        return new Promise((resolve) => {
          let blob = new Blob([x]),
            fileReader = new FileReader();
          fileReader.readAsArrayBuffer(blob);
          fileReader.onload = function () {
            resolve(Buffer.from(this.result).toString("base64"));
          };
        });
      }

      //FALTA VERIFICAR SE O EMAIL E A PASS SAO CORRETOS OU EXISTEM OU QQ COISA ASSIM

      let genero_registo = true;
      if (this.input_register.genero != "Masculino") {
        genero_registo = false;
      }
      var registoInfo = {
        email: this.input_register.email,
        password: sjcl.codec.hex.fromBits(
          sjcl.hash.sha256.hash(this.input_register.password)
        ),
        username: this.input_register.username,
        nome: this.input_register.nome,
        data: this.input_register.date,
        genero: genero_registo,
        descricao: this.input_register.descricao
      };
      registoInfo.foto_perfil = await carrega_foto(this.input_register.imagem);
      console.log(registoInfo);
      axios
        .post(process.env.VUE_APP_BASELINK+"/api/register/treinador", registoInfo,{headers: {'token': localStorage.getItem("token")}})
        .then((response) => {
          //const status = JSON.parse(response.status);
          console.log(response);
        }); 
    },

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