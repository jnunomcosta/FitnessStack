<template>
  <div class="criar_treino_page">
    <NavBar />
    <v-row>
      <v-col v-if="getUsertype() == 1" cols="12" md="1">
        <SideBarTreinador />
      </v-col>
      <v-col v-else cols="12" md="1">
        <SideBar />
      </v-col>
      <v-col cols="12" md="5" style="padding-top: 100px">
        <v-card class="pb-8">
          <v-card-text>
            <h1
              class="text-center py-2"
              style="color: #f95738; font-size: 34px"
            >
              <b>Novo Treino</b>
            </h1>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-text>
            <v-form class="pt-4 px-16 mx-8">
              <v-text-field
                label="Nome do Treino"
                v-model="input.nome_treino"
                type="text"
                required
                color="#f95738"
              />
              <v-text-field
                label="Descrição"
                v-model="input.descricao"
                type="text"
                color="#f95738"
              />
              <v-combobox
                v-model="select_categorias"
                :items="categorias"
                label="Categorias"
                multiple
                chips
                required
                color="#f95738"
              >
              </v-combobox>
              <v-combobox
                v-model="select_dificuldade"
                :items="dificuldades"
                label="Dificuldade"
                required
                color="#f95738"
              >
              </v-combobox>
            </v-form>
          </v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12" md="5" style="padding-top: 100px">
        <v-card v-scroll.self="onScroll" class="overflow-y-auto">
          <v-card-text>
            <h1
              class="text-center py-2"
              style="color: #f95738; font-size: 34px"
            >
              Exercícios
            </h1>
          </v-card-text>
        </v-card>

        <draggable
          :list="blocos"
          :animation="200"
          ghost-class="moving-card"
          group="users"
          filter=".action-button"
          class="w-full max-w-md mt-2"
          tag="ul"
        >
          <v-card v-for="(bloco, i) in blocos" :key="i" class="mr-6 mb-2">
            <div class="flex justify-between cursor-move border border-white">
              <v-toolbar flat dense rounded color="#000314" dark
                ><v-icon>mdi-drag</v-icon>
                <div class="ml-2 title">{{ bloco.nome }}</div>
                <v-spacer></v-spacer>
                <v-btn icon @click="apagar(i)"
                  ><v-icon>mdi-close</v-icon></v-btn
                >
              </v-toolbar>

              <v-list class="transparent">
                <v-list-item class="mt-0">
                  <v-list-item-subtitle>
                    Séries: {{ bloco.series }}
                  </v-list-item-subtitle>
                  <v-list-item-subtitle>
                    {{ bloco.valor_repeticao }}
                  </v-list-item-subtitle>
                  <v-list-item-subtitle>
                    Descanso: {{ bloco.valor_descanso }}
                  </v-list-item-subtitle>
                </v-list-item>
              </v-list>
            </div>
          </v-card>
        </draggable>
      </v-col>
      <v-col cols="12" md="1"></v-col>
    </v-row>
    <v-row style="padding-bottom: 60px">
      <v-col cols="12" md="12">
        <div class="text-center my-10">
          <v-btn v-on:click="confirmar()" color="#f95738" dark>Confirmar</v-btn>
        </div>
      </v-col>
    </v-row>
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          fab
          dark
          large
          color="#f95738"
          fixed
          right
          top
          style="margin-top: 75px"
          v-bind="attrs"
          v-on="on"
        >
          <v-icon>mdi-plus</v-icon>
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Adicionar Bloco</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-autocomplete
                  label="Exercício*"
                  v-model="exercicio"
                  :items="items"
                  color="#f95738"
                  required
                >
                </v-autocomplete>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  v-model="series"
                  color="#f95738"
                  label="Séries*"
                  type="number"
                  @click:append-outer="increment"
                  @click:prepend="decrement"
                >
                </v-text-field>
              </v-col>
              <v-col cols="12" sm="4">
                <v-combobox
                  v-model="modo"
                  :items="['Repetições', 'Duração']"
                  label="Modo"
                  color="#f95738"
                  required
                >
                </v-combobox>
              </v-col>
              <v-col cols="12" sm="4">
                <v-text-field
                  v-model="valor_modo"
                  label="Duração (segundos)*"
                  color="#f95738"
                  type="number"
                >
                </v-text-field>
              </v-col>
              <v-col cols="12" sm="4">
                <v-text-field
                  v-if="modo == 'Repetições'"
                  v-model="n_repeticoes"
                  label="Repetições (unidades)*"
                  color="#f95738"
                  type="number"
                >
                </v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  v-model="valor_descanso"
                  color="#f95738"
                  label="Descanso (segundos)*"
                  type="number"
                >
                </v-text-field>
              </v-col>
            </v-row>
          </v-container>
          <small>* campos obrigatórios</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="#f95738" text @click="dialog = false"> Fechar </v-btn>
          <v-btn
            color="#f95738"
            text
            v-on:click="criar()"
            @click="dialog = false"
          >
            Criar
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import SideBar from "@/components/SideBar_User.vue";
import SideBarTreinador from "@/components/SideBar_Treinador.vue";
import draggable from "vuedraggable";

import axios from "axios";

export default {
  name: "CriarTreino",
  components: {
    NavBar,
    SideBar,
    SideBarTreinador,
    draggable,
  },
  created() {
    document.title = "Novo Treino";
  },
  data() {
    return {
      dialog: false,
      items: [],
      exercicio: "",
      modo: "",
      valor: "",
      series: 0,
      valor_modo: 0,
      n_repeticoes: 0,
      valor_descanso: 0,
      duracao_treino: 0,
      input: {
        nome_treino: "",
        descricao: "",
        categorias: "",
        dificuldade: "",
      },

      select_categorias: [],
      categorias: ["Pernas", "Braços", "Peito", "Cardio", "Força"],
      select_dificuldade: "",
      dificuldades: ["Iniciante", "Intermédio", "Avançado"],
      blocos: [],
    };
  },
  mounted() {
    axios
      .get(process.env.VUE_APP_BASELINK + "/api/exercicio/getNomesExercicios", {
        headers: { token: localStorage.getItem("token") },
      })
      .then((response) => {
        console.log(response);
        if (response.status == "200") {
          this.items = response.data;
        }
      });
  },
  methods: {
    getDuracao() {
      var duracao = 0.0;
      for (var i = 0; i < this.blocos.length; i++) {
        duracao +=
          parseFloat(this.blocos[i].duracao_repeticao) +
          parseFloat(this.blocos[i].descanso);
        console.log(this.blocos[i].duracao_repeticao);
        console.log(this.blocos[i].descanso);
      }
      console.log(duracao);
      return duracao;
    },
    getUsertype() {
      return localStorage.getItem("usertype");
    },
    apagar(i) {
      this.blocos.splice(i, 1);
    },
    confirmar() {
      var exs = [];
      for (var i = 0; i < this.blocos.length; i++) {
        exs.push({
          nome_exercicio: this.blocos[i].nome,
          series: this.blocos[i].series,
          descanso: this.blocos[i].descanso,
          duracao: this.blocos[i].repeticao,
          tipo: this.blocos[i].tipo,
        });
      }
      var post_body = {
        nome: this.input.nome_treino,
        descricao: this.input.descricao,
        categorias: this.select_categorias,
        dificuldade: this.select_dificuldade,
        duracao_treino: this.getDuracao(),
        exercicios: exs,
      };
      axios
        .post(
          process.env.VUE_APP_BASELINK + "/api/treinos/novoTreino",
          post_body,
          {
            headers: { token: localStorage.getItem("token") },
          }
        )
        .then((response) => {
          console.log(response);
          if (response.status == "200") {
            if (localStorage.getItem("usertype") == 0) {
              this.$router.push("/treinos");
            } else if (localStorage.getItem("usertype") == 1) {
              this.$router.push("/treinador/treinos");
            }
          }
        });
    },
    criar() {
      var rep_valor = "";
      var valor_repeticoes = "";
      var tipo = true;
      switch (this.modo) {
        case "Repetições":
          rep_valor = "Repetições: " + this.n_repeticoes + " reps";
          valor_repeticoes = this.n_repeticoes;
          tipo = true;
          break;
        case "Duração":
          rep_valor = "Duração: " + this.valor_modo + "s";
          valor_repeticoes = this.valor_modo;
          tipo = false;
          break;
        default:
          rep_valor = "";
          break;
      }
      var bloco_exercicios = {
        nome: this.exercicio,
        series: this.series,
        modo: this.modo,
        valor_repeticao: rep_valor,
        repeticao: valor_repeticoes,
        duracao_repeticao: this.valor_modo,
        descanso: this.valor_descanso,
        tipo: tipo,
        valor_descanso: this.valor_descanso + "s",
      };
      this.blocos.push(bloco_exercicios);
      this.exercicio = "";
      this.series = "";
      this.modo = "";
      this.valor_modo = "";
      this.n_repeticoes = "";
      this.valor_descanso = "";
      (this.duracao_repeticao = ""), (this.valor = "");
      this.descanso = "";
      console.log(this.blocos);
    },
    increment() {
      this.series = parseInt(this.series, 10) + 1;
    },
    decrement() {
      this.series = parseInt(this.series, 10) - 1;
    },
  },
};
</script>

<style>
.criar_treino_page {
  background-color: #d3d3d2;
}
</style>