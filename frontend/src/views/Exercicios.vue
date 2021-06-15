<template>
  <div class="procurarExercicio">
    <NavBar />
    <v-row>
      <v-col v-if="getUsertype() == 1" cols="12" md="1">
        <SideBarTreinador />
      </v-col>
      <v-col v-else cols="12" md="1">
        <SideBar />
      </v-col>
      <v-col
        cols="12"
        md="10"
        style="padding-top: 120px; padding-bottom: 100px"
      >
        <h1 style="text-align: center; font-size: 40px">
          Procure um exercício
        </h1>

        <v-alert
          border="left"
          v-if="success"
          text
          dismissible
          elevation="2"
          type="success"
        >
          Exercício registado com sucesso
        </v-alert>
        <v-alert
          border="left"
          v-if="error"
          text
          dismissible
          elevation="2"
          type="error"
        >
          Erro ao registar exercício
        </v-alert>

        <v-container class="pa-0 ma-0">
          <Exercicio />
        </v-container>
      </v-col>
    </v-row>
    <div v-if="getUsertype() == 1">
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
            <v-icon>mdi-plus</v-icon>
          </v-btn>
        </template>
        <v-card>
          <v-toolbar color="#f95738" dark
            ><h3>Novo Exercício</h3>
            <v-spacer></v-spacer>
            <v-btn icon @click="dialog = false"
              ><v-icon>mdi-close</v-icon></v-btn
            >
          </v-toolbar>
          <v-stepper v-model="e1">
            <v-stepper-header>
              <template v-for="n in steps">
                <v-stepper-step
                  :key="`${n}-step`"
                  :complete="e1 > n"
                  :step="n"
                  editable
                  color="#f95738"
                >
                  <span v-if="n == 1">Informações</span>
                  <span v-else>Imagem/Vídeo</span>
                </v-stepper-step>

                <v-divider v-if="n !== steps" :key="n"></v-divider>
              </template>
            </v-stepper-header>

            <v-stepper-items>
              <v-stepper-content
                v-for="n in steps"
                :key="`${n}-content`"
                :step="n"
              >
                <v-form ref="exercicio_form">
                  <v-card-text v-if="n == 1">
                    <v-text-field
                      v-model="nome"
                      label="Nome do Exercício"
                      :rules="rules"
                      type="text"
                      color="#f95738"
                      required
                    ></v-text-field>

                    <v-text-field
                      v-model="descricao"
                      label="Descrição"
                      :rules="rules"
                      type="text"
                      color="#f95738"
                      required
                    ></v-text-field>

                    <!-- <v-text-field
                  v-model="duracao"
                  :rules="rules"
                  label="Duração Média (segundos)"
                  type="number"
                  :min="minDuracao"
                  :max="maxDuracao"
                  color="#f95738"
                  required
                ></v-text-field> -->

                    <v-text-field
                      v-model="material"
                      label="Material Necessário"
                      type="text"
                      color="#f95738"
                    ></v-text-field>
                  </v-card-text>

                  <v-card-text v-else-if="n == 2">
                    <v-file-input
                      v-model="conteudos"
                      multiple
                      accept="image/png, image/jpeg, image/bmp, video/mp4"
                      placeholder="Selecione imagens e/ou vídeos"
                      prepend-icon="mdi-paperclip"
                      label="Demonstração do Exercício"
                      color="#f95738"
                    ></v-file-input>
                  </v-card-text>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      v-if="n == 1"
                      color="#f95738"
                      text
                      @click="nextStep(n)"
                    >
                      Continuar
                    </v-btn>
                    <v-btn color="#f95738" dark @click="validateForm()">
                      Confirmar
                    </v-btn>
                  </v-card-actions>
                </v-form>
              </v-stepper-content>
            </v-stepper-items>
          </v-stepper>
        </v-card>
      </v-dialog>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_User.vue";
import SideBarTreinador from "@/components/SideBar_Treinador.vue";
import Exercicio from "@/components/Exercicio.vue";
import axios from "axios";

export default {
  name: "ProcurarExercicio",
  components: {
    NavBar,
    SideBar,
    SideBarTreinador,
    Exercicio,
  },
  created() {
    document.title = "Exercícios";
  },
  data() {
    return {
      success: false,
      error: false,
      dialog: false,
      e1: 1,
      steps: 2,
      nome: "",
      descricao: "",
      duracao: 0,
      material: "",
      //minDuracao: 0,
      //maxDuracao: 86400,
      conteudos: [],
      rules: [(v) => !!v || "Campo obrigatório"],
    };
  },
  watch: {
    steps(val) {
      if (this.e1 > val) {
        this.e1 = val;
      }
    },
  },
  methods: {
    getUsertype() {
      return localStorage.getItem("usertype");
    },
    validateForm() {
      if (this.$refs.exercicio_form[0].validate()) {
        this.confirmarExercicio();
        this.dialog = false;
      }
    },
    nextStep(n) {
      if (n === this.steps) {
        this.e1 = 1;
      } else {
        this.e1 = n + 1;
      }
    },
    updateTags() {
      this.$nextTick(() => {
        this.tags.push(...this.searchTags.split(","));
        this.$nextTick(() => {
          this.searchTags = "";
        });
      });
    },
    async confirmarExercicio() {
      function carrega_conteudomedia(x) {
        return new Promise((resolve) => {
          let blob = new Blob([x]),
            fileReader = new FileReader();
          fileReader.readAsArrayBuffer(blob);
          fileReader.onload = function () {
            let falg = false;
            let splitted = x.type.split("/");
            if (splitted[0] == "video") {
              falg = true;
            }
            resolve({
              tipo: falg,
              conteudo: Buffer.from(this.result).toString("base64"),
            });
          };
        });
      }

      let conteudos_lindos = [];
      for (let i = 0; i < this.conteudos.length; i++) {
        var elou = await carrega_conteudomedia(this.conteudos[i]);
        conteudos_lindos.push(elou);
      }

      console.log(this.descricao)
      var exercicio_post = {
        nome: this.nome,
        descricao: this.descricao,
        duracao_media: this.duracao,
        material_necessario: this.material,
        conteudo_media: conteudos_lindos,
      };

      axios
        .post(
          process.env.VUE_APP_BASELINK + "/api/exercicio/novo",
          exercicio_post,
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response) => {
          const status = JSON.parse(response.status);
          if (status == "200") {
            this.nome = "";
            this.descricao = "";
            this.duracao = "";
            this.material = "";
            this.conteudos = [];

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

<style>
.procurarExercicio {
  background-color: #f4f5f5;
}
</style>