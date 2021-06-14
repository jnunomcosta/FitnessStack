<template>
  <div class="profile">
    <NavBar />
    <div>
      <SideBar />
      <v-row
        style="
          margin-left: 75px;
          padding-top: 100px;
          padding-bottom: 100px;
          margin-right: 25px;
        "
      >
        <v-col cols="12" md="5">
          <v-card style="text-align: center" class="mb-6">
            <div class="mx-auto text-center">
              <v-avatar class="mt-4" size="150">
                <v-img :src="linkapi() + treinador.foto_perfil"></v-img>
              </v-avatar>
            </div>
            <v-card-title class="justify-center">{{
              treinador.nome
            }} <v-icon dense color="#f95738" class="ml-2">mdi-weight-lifter</v-icon></v-card-title>
            <v-card-subtitle> {{ treinador.username }} </v-card-subtitle>
            <v-divider class="mx-4"></v-divider>
            <v-card-text class="mb-0 pb-0">
              <div class="text-center">
                <v-rating
                  readonly
                  dense
                  v-model="treinador.classsificacao"
                  background-color="black"
                  color="#f95738"
                ></v-rating>

                <div class="grey--text body-2">
                  {{ parseFloat(treinador.classsificacao).toFixed(1) }} ({{
                    treinador.numero_classsificacao
                  }})
                </div>
              </div>
            </v-card-text>
            <v-card-text class="my-1 black--text"><v-icon small class="mr-1">mdi-email</v-icon>{{ treinador.email }}</v-card-text>
            <v-container class="justify-center">
              <v-dialog v-model="dialog1" persistent max-width="400px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="#f95738"
                    dark
                    small
                    class="mb-1"
                    v-bind="attrs"
                    v-on="on"
                  >
                    <v-icon dense> mdi-account-edit-outline </v-icon>
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span>Alterar nome de utilizador</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col cols="12" sm="6" md="12">
                          Digite o novo nome de utilizador.
                          <v-form ref="usernameForm">
                          <v-text-field
                            v-model="new_username"
                            :rules="usernameRules"
                            color="#f95738"
                            prepend-icon="mdi-account-lock"
                            label="Nome de Utilizador"
                            required
                          ></v-text-field>
                          </v-form>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="#f95738" text @click="dialog1 = false">
                      Sair
                    </v-btn>
                    <v-btn
                      color="#f95738"
                      dark
                      @click="validateUsername()"
                    >
                      Atualizar
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog v-model="dialog2" persistent max-width="400px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="#f95738"
                    dark
                    small
                    class="ml-1 mb-1"
                    v-bind="attrs"
                    v-on="on"
                  >
                    <v-icon dense> mdi-email-edit-outline </v-icon>
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span>Alterar email</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col cols="12" sm="6" md="12">
                          Digite o novo email.
                          <v-form ref="emailForm">
                          <v-text-field
                            v-model="input_email"
                            color="#f95738"
                            :rules="emailRules"
                            prepend-icon="mdi-email"
                            label="Email"
                            required
                          ></v-text-field>
                          </v-form>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="#f95738" text @click="dialog2 = false">
                      Sair
                    </v-btn>
                    <v-btn
                      color="#f95738"
                      dark
                      @click="validateEmail()"
                    >
                      Atualizar
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog v-model="dialog3" persistent max-width="400px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="#f95738"
                    dark
                    class="ml-1 mb-1"
                    small
                    v-bind="attrs"
                    v-on="on"
                  >
                    <v-icon dense> mdi-lock-reset</v-icon>
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span>Alterar palavra-passe</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col cols="12" md="12">
                          <v-form ref="passwordForm">
                          Digite a palavra-passe antiga.
                          <v-text-field
                            v-model="old_password"
                            color="#f95738"
                            type="password"
                            counter
                            :rules="passwordRules"
                            prepend-icon="mdi-lock"
                            label="Palavra-Passe Antiga"
                            required
                          ></v-text-field>
                          Digite a palavra-passe nova.
                          <v-text-field
                            v-model="new_password"
                            color="#f95738"
                            type="password"
                            counter
                            :rules="passwordRules"
                            prepend-icon="mdi-lock-question"
                            label="Palavra-Passe Nova"
                            required
                          ></v-text-field>
                          </v-form>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="#f95738" text @click="dialog3 = false">
                      Sair
                    </v-btn>
                    <v-btn
                      color="#f95738"
                      dark
                      @click="validatePassword()"
                    >
                      Atualizar
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog v-model="dialog4" persistent max-width="400px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="#f95738"
                    dark
                    class="ml-1 mb-1"
                    small
                    v-bind="attrs"
                    v-on="on"
                  >
                    <v-icon dense> mdi-camera</v-icon>
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span>Alterar foto de perfil</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-form ref="avatarForm">
                      <v-file-input
                        id="imagem"
                        color="#f95738"
                        v-model="imagem_input"
                        :rules="avatarRules"
                        label="Imagem de Perfil"
                        name="imagem_perfil"
                        prepend-icon="mdi-camera"
                      />
                      </v-form>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="#f95738" text @click="dialog4 = false">
                      Sair
                    </v-btn>
                    <v-btn
                      color="#f95738"
                      dark
                      @click="validateAvatar()"
                    >
                      Atualizar
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-container>
          </v-card>
          <v-alert
            border="left"
            v-if="success"
            text
            dismissible
            elevation="2"
            type="success"
          >
            Informação atualizada com sucesso
          </v-alert>
          <v-alert
            border="left"
            v-if="error"
            text
            dismissible
            elevation="2"
            type="error"
          >
            Erro ao atualizar informação
          </v-alert>
        </v-col>
        <v-col cols="12" md="7"><v-card
          v-scroll.self="onScroll"
          style="text-align: center; margin-right: 90px; max-width:700px"
          class="overflow-y-auto"
          max-height="800"
        >
          <v-card-title class="justify-center" style="color: #f95738">Avaliações</v-card-title>
          <v-divider></v-divider>
          <div
            class="text-center"
            v-for="(item, i) in treinador.comentarios"
            :key="i"
          >
            <v-list three-line>
              <v-list-item class="ml-8">
                <v-list-item-avatar size="50">
                <v-img :src="linkapi() + item.foto_user"></v-img>
              </v-list-item-avatar>

                <v-list-item-content>
                  <v-card-title class="subtitle-2 my-0 py-0 pb-2">{{
                    item.username
                  }}</v-card-title>
                  <v-card-text>
                    <v-row align="center" class="mx-0">
                      <v-rating
                        readonly
                        dense
                        v-model="item.nota"
                        background-color="black"
                        color="#f95738"
                        size="14"
                      ></v-rating>
                    </v-row>
                  </v-card-text>
                  <v-card-text>
                    <v-row align="center" class="mx-0">{{
                      item.comentario
                    }}</v-row></v-card-text
                  >
                </v-list-item-content>
              </v-list-item>
              <v-divider
                v-if="i + 1 < Object.keys(treinador.comentarios).length"
                class="mx-8"
              ></v-divider>
            </v-list>
          </div>
        </v-card> </v-col>
      
      
        </v-row>
    </div>
    <Footer />
  </div>
</template>

<script>
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_Treinador.vue";
import axios from "axios";
import sjcl from "sjcl";

export default {
  name: "Progresso",
  components: {
    NavBar,
    SideBar,
  },
  created() {
    document.title = "Fitness Stack";
  },
  data: () => ({
    usernameRules: [
      (v) =>
        v == "" ||
        /^[a-zA-Z0-9.-]{2,}$/.test(v) ||
        "Nome de utilizador inválido",
    ],
    emailRules: [
      (v) => v == "" || /.+@.+/.test(v) || "Email inválido",
    ],
    passwordRules: [
      (v) => !!v || "Campo obrigatório",
      (v) =>
        /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/.test(v) ||
        "A palavra-passe deve ter no mínimo 8 caracteres, pelo menos 1 maiúscula, 1 minúscula e 1 número",
    ],
    avatarRules: [
      (value) =>
        !value ||
        value.size < 5000000 ||
        "O tamanho da imagem deve ser inferior a 5 MB",
    ],
    error: false,
    success: false,
    dialog1: false,
    dialog2: false,
    dialog3: false,
    dialog4: false,
    dialog5: false,
    dialog6: false,
    imagem_input: null,
    new_username: "",
    input_email: "",
    old_password: "",
    new_password: "",
    treinador: {
      username: "",
      foto_perfil: "",
      nome: "",
      email: "",
      classificacao: 0,
      comentarios:[
        {
          nota: 0,
          comentario: "",
          username: "",
          foto_user: ""
        }
      ]
    },
  }),
  mounted() {
    axios
      .get(
        process.env.VUE_APP_BASELINK +
          "/api/treinador/getTreinadorInfo?username=" +
          localStorage.getItem("username"),
        { headers: { token: localStorage.getItem("token") } }
      )
      .then((response) => {
        this.treinador = response.data;
        console.log(this.treinador);
      })
  },
  methods: {
    validateUsername() {
      if (this.$refs.usernameForm.validate()) {
        if (this.new_username != "") {
          this.setUsername(this.new_username);
          this.dialog1 = false;
        }
        else {
          this.dialog1 = false;
        }
      }
    },
    validateEmail() {
      if (this.$refs.emailForm.validate()) {
        if (this.input_email != "") {
          this.setEmail(this.input_email);
          this.dialog2 = false;
        }
        else {
          this.dialog2 = false;
        }
      }
    },
    validatePassword() {
      if (this.$refs.passwordForm.validate()) {
        if (this.old_password != this.new_password) {
          this.setPassword(this.old_password, this.new_password);
          this.dialog3 = false;
        }
        else {
          this.dialog3 = false;
        }
      }      
    },
    validateAvatar() {
      if (this.$refs.avatarForm.validate()) {
        if (this.imagem_input != null) {
          this.setImagem(this.imagem_input);
          this.dialog4 = false;
        }
        else {
          this.dialog4 = false;
        }
      }      
    },
    linkapi() {
      return process.env.VUE_APP_BASELINK;
    },
    setEmail(new_email) {
      axios
        .post(
          process.env.VUE_APP_BASELINK + "/api/treinador/mudarEmail",
          {
            email: new_email,
          },
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response) => {
          const status = JSON.parse(response.status);
          this.treinador.email = new_email;

          if (status == "200") {
          this.success = true;
            setTimeout(() => {
              this.success = false;
            }, 5000);
          }
        })
        .catch((error) => {
          if (error.response != null) {
            this.error = true;
            setTimeout(() => {
              this.error = false;
            }, 5000);
          }
        });
    },
    setPassword(oldP, newP) {
      axios
        .post(
          process.env.VUE_APP_BASELINK + "/api/treinador/mudarPassword",
          {
            new_password: sjcl.codec.hex.fromBits(sjcl.hash.sha256.hash(newP)),
            old_password: sjcl.codec.hex.fromBits(sjcl.hash.sha256.hash(oldP)),
          },
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response) => {
          const status = JSON.parse(response.status);
          if (status == "200") {
            this.success = true;
            setTimeout(() => {
              this.success = false;
            }, 5000);
          }
        })
        .catch((error) => {
          if (error.response != null) {
            this.error = true;
            setTimeout(() => {
              this.error = false;
            }, 5000);
          }
        });
    },
    setUsername(new_username) {
      axios
        .post(
          process.env.VUE_APP_BASELINK + "/api/treinador/mudarUsername",
          {
            username_novo: new_username,
          },
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response) => {
          const status = JSON.parse(response.status);

          if (status == "200") {
            this.treinador.username = new_username;
            localStorage.setItem("token", response.data.token);
            localStorage.setItem("username", new_username);
            
            this.success = true;
            setTimeout(() => {
              this.success = false;
            }, 5000);
          }
        })
        .catch((error) => {
          if (error.response != null) {
            this.error = true;
            setTimeout(() => {
              this.error = false;
            }, 5000);
          }
        });
    },
    async setImagem(imagem) {
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

      let foto = await carrega_foto(imagem);
      axios
        .post(
          process.env.VUE_APP_BASELINK + "/api/treinador/mudarImagem",
          { nova_foto: foto },
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response) => {
          const status = JSON.parse(response.status);

          if (status == "200") {
            this.success = true;
            setTimeout(() => {
              this.success = false;
              this.$router.go();
            }, 5000);
          }
        })
        .catch((error) => {
          if (error.response != null) {
            this.error = true;
            setTimeout(() => {
              this.error = false;
            }, 5000);
          }
        });
    },
  },
};
</script>

};


<style>
.profile {
  background-color: #d3d3d2;
}
</style>
