<template>
  <div class="profile">
    <NavBar />
    <div>
      <v-row
        style="
          margin-left: 75px;
          margin-top: 100px;
          margin-right: 25px;
          margin-bottom: 25px;
        "
      >
        <v-col cols="12" md="2">
          <SideBar />
          <v-card style="text-align: center">
            <div class="mx-auto text-center">
              <v-avatar class="mt-4" size="150">
                <v-img :src="'http://localhost:4576' + treinador.foto_perfil"></v-img>
              </v-avatar>
            </div>
            <v-card-title class="justify-center">{{treinador.nome}}</v-card-title>
            <v-card-subtitle> {{treinador.username}} </v-card-subtitle>
            <v-divider class="mx-4"></v-divider>
            <div class="mt-4 body-2">{{treinador.email}}</div>
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
                    <span class="headline">Alterar nome de utilizador</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col cols="12" sm="6" md="12">
                          Digite o novo nome de utilizador.
                          <v-text-field
                            color="#f95738"
                            prepend-icon="mdi-account-lock"
                            label="Nome de Utilizador"
                            required
                          ></v-text-field>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="#f95738" text @click="dialog1 = false">
                      Sair
                    </v-btn>
                    <v-btn color="#f95738" text @click="dialog1 = false">
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
                    <span class="headline">Alterar email</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col cols="12" sm="6" md="12">
                          Digite o novo email.
                          <v-text-field
                            color="#f95738"
                            prepend-icon="mdi-email"
                            label="Email"
                            required
                          ></v-text-field>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="#f95738" text @click="dialog2 = false">
                      Sair
                    </v-btn>
                    <v-btn color="#f95738" text @click="dialog2 = false">
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
                    <span class="headline">Alterar palavra-passe</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col cols="12" md="12">
                          Digite a palavra-passe antiga.
                          <v-text-field
                            color="#f95738"
                            prepend-icon="mdi-lock"
                            label="Palavra-Passe Antiga"
                            required
                          ></v-text-field>
                          Digite a palavra-passe nova.
                          <v-text-field
                            color="#f95738"
                            prepend-icon="mdi-lock-question"
                            label="Palavra-Passe Nova"
                            required
                          ></v-text-field>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="#f95738" text @click="dialog3 = false">
                      Sair
                    </v-btn>
                    <v-btn color="#f95738" text @click="dialog3 = false">
                      Atualizar
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-container>
          </v-card>
        </v-col>
      </v-row>
    </div>
    <Footer />
  </div>
</template>

<script>
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_Treinador.vue";
import axios from 'axios';

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
    dialog1: false,
    dialog2: false,
    dialog3: false,
    dialog4: false,
    dialog5: false,
    dialog6: false,
    treinador : {
      username: "",
      foto_perfil: "",
      nome: "",
      email: "",
    },
  }),
  mounted() {
    axios
      .get("http://localhost:4576/api/treinador/getTreinadorInfo",{headers: {'token': localStorage.getItem("token")}})
      .then((response) => {
        this.treinador = response.data;
      })
      .finally(() => (this.loading = false));
  },
  
};
</script>

};


<style>
.profile {
  background-color: #d3d3d2;
}
</style>
