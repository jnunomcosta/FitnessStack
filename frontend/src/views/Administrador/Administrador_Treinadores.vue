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

        <v-alert
          border="left"
          v-if="success"
          text
          dismissible
          elevation="2"
          type="success"
        >
          Treinador registado com sucesso
        </v-alert>
        <v-alert
          border="left"
          v-if="successDelete"
          text
          dismissible
          elevation="2"
          type="success"
        >
          Treinador(es) eliminado(s) com sucesso
        </v-alert>
        <v-alert
          border="left"
          v-if="registerAuthError"
          text
          dismissible
          elevation="2"
          type="warning"
        >
          Email e/ou Nome de Utilizador já existem
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
          Não é permitido apagar este(s) treinador(es)
        </v-alert>

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
          <v-card-title> Novo treinador </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col cols="12" md="12">
                  <v-form ref="register_form" class="px-16 mt-6">
                    <v-text-field
                      label="Nome Completo"
                      name="Name"
                      v-model="input_register.nome"
                      prepend-icon="mdi-account"
                      :rules="nameRules"
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
                          label="Data de Nascimento"
                          prepend-icon="mdi-calendar"
                          readonly
                          :rules="birthdateRules"
                          :max="getMaxDate"
                          hint="Deve ter no mínimo 18 anos"
                          persistent-hint
                          v-bind="attrs"
                          v-on="on"
                          color="#f95738"
                          required
                        ></v-text-field>
                      </template>
                      <v-date-picker
                        v-model="input_register.date"
                        no-title
                        :rules="birthdateRules"
                        :max="getMaxDate"
                        scrollable
                        color="#7189ff"
                      >
                        <v-spacer></v-spacer>
                        <v-btn text color="#7189ff" @click="menu = false">
                          Cancelar
                        </v-btn>
                        <v-btn
                          text
                          color="#7189ff"
                          @click="$refs.menu.save(input_register.date)"
                        >
                          OK
                        </v-btn>
                      </v-date-picker>
                    </v-menu>
                    <v-text-field
                      label="Email"
                      name="Email"
                      v-model="input_register.email"
                      prepend-icon="mdi-email"
                      :rules="emailRules"
                      type="text"
                      color="#f95738"
                      required
                    />
                    <v-text-field
                      label="Nome de Utilizador"
                      name="Username"
                      v-model="input_register.username"
                      prepend-icon="mdi-account-lock"
                      :rules="usernameRules"
                      type="text"
                      color="#f95738"
                      required
                    />
                    <v-text-field
                      id="password"
                      label="Palavra-Passe"
                      name="password"
                      v-model="input_register.password"
                      prepend-icon="mdi-lock"
                      :rules="passwordRules"
                      type="password"
                      color="#f95738"
                      required
                    /><v-select
                      :rules="nameRules"
                      :items="['Masculino', 'Feminino']"
                      v-model="input_register.genero"
                      prepend-icon="mdi-gender-male-female"
                      label="Género"
                      color="#f95738"
                      required
                    ></v-select>
                    <v-text-field
                      label="Descrição"
                      name="descricao"
                      :rules="nameRules"
                      v-model="input_register.descricao"
                      prepend-icon="mdi-card-text"
                      type="text"
                      color="#f95738"
                      required
                    />
                    <v-file-input
                      id="imagem"
                      v-model="input_register.imagem"
                      :rules="avatarRules"
                      label="Imagem de Perfil"
                      color="#f95738"
                      name="imagem_perfil"
                      prepend-icon="mdi-camera"
                    />
                  </v-form>
                </v-col>
              </v-row>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="#f95738" text @click="dialog1 = false">
              Fechar
            </v-btn>
            <v-btn color="#f95738" dark v-on:click="validate_register()">
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
            Apagar Treinador(es)
          </v-card-title>
          <v-card-text class="mt-4"
            >Deseja apagar o(s) treinador(es)? Esta ação é
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
      .get(process.env.VUE_APP_BASELINK + "/api/treinador/listar", {
        headers: { token: localStorage.getItem("token") },
      })
      .then((response) => {
        this.rows = response.data;
      })
      .catch((e) => console.log("erro" + e));
  },
  data() {
    return {
      date: new Date(),
      dialogDelete: false,
      success: false,
      successDelete: false,
      registerAuthError: false,
      registerServerError: false,
      forbiddenError: false,
      nameRules: [(v) => !!v || "Campo obrigatório"],
      birthdateRules: [
        (v) => !!v || "Campo obrigatório",
        (v) =>
          Math.abs(new Date().getFullYear() - new Date(v).getFullYear()) > 17 ||
          "Data de nascimento inválida",
      ],
      emailRules: [
        (v) => !!v || "Campo obrigatório",
        (v) => /.+@.+/.test(v) || "Email inválido",
      ],
      usernameRules: [
        (v) => !!v || "Campo obrigatório",
        (v) => /^[a-zA-Z0-9.-]{2,}$/.test(v) || "Nome de utilizador inválido",
      ],
      passwordRules: [
        (v) => !!v || "Campo obrigatório",
        (v) =>
          /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/.test(v) ||
          "A palavra-passe deve ter no mínimo 8 caracteres, pelo menos 1 maiúscula, 1 minúscula e 1 número",
      ],
      avatarRules: [
        (value) => !!value || "Campo obrigatório",
        (value) =>
          !value ||
          value.size < 5000000 ||
          "O tamanho da imagem deve ser inferior a 5 MB",
      ],
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
        descricao: "",
      },
    };
  },
  methods: {
    validate_register() {
      if (this.$refs.register_form.validate()) {
        this.registar();
        this.dialog1 = false;
      }
    },
    confirmar_apagar() {
      this.apagarTreinador();
      this.dialogDelete = false;
    },
    apagarTreinador() {
      let deletbody = [];
      this.selected.forEach((element) => {
        deletbody.push(element.username);
      });
      console.log(deletbody);
      axios
        .delete(
          process.env.VUE_APP_BASELINK + "/api/treinador/deleteTreinador",
          { headers: { token: localStorage.getItem("token") }, data: deletbody }
        )
        .then((response) => {
          if (response.status == 200) {
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
        descricao: this.input_register.descricao,
      };
      registoInfo.foto_perfil = await carrega_foto(this.input_register.imagem);
      console.log(registoInfo);
      axios
        .post(
          process.env.VUE_APP_BASELINK + "/api/register/treinador",
          registoInfo,
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response2) => {
          const status2 = JSON.parse(response2.status);
          if (status2 == "200") {
            this.success = true;
            setTimeout(() => {
              this.success = false;
              this.$router.go();

            }, 5000);
          }
        })
        .catch((error) => {
          if (error.response != null) {
            if (error.response.status == "401") {
              this.registerAuthError = true;
              setTimeout(() => {
                this.registerAuthError = false;
              }, 5000);
            }
          } else {
            this.registerServerError = true;
            setTimeout(() => {
              this.registerServerError = false;
            }, 5000);
          }
        });
    },
  },
  computed: {
    getMaxDate() {
      var maxDate = new Date(
        this.date.getFullYear() - 18,
        this.date.getMonth(),
        this.date.getDay()
      );
      return maxDate.toISOString().substr(0, 10);
    },
  },
};
</script>

<style>
.procurarTreinadores {
  background-color: #d3d3d2;
}
</style>