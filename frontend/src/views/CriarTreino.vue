<template>
  <div class="criar_treino_page">
    <NavBar />
    <v-row style="padding-bottom: 60px">
      <v-col cols="12" md="1">
        <SideBar />
      </v-col>
      <v-col cols="12" md="5" style="padding-top: 100px">
        <v-card class="pt-10">
          <v-card-text>
            <h1 class="text-center display-2 mb-6" style="color: #f95738">
              Criar Treino
            </h1>
            <v-form class="px-16 mx-8">
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
            <div class="text-center my-10">
              <v-btn v-on:click="confirmar()" color="#f95738" dark
                >Confirmar</v-btn
              >
            </div>
          </v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12" md="5" style="padding-top: 100px">
        <v-card v-scroll.self="onScroll" class="overflow-y-auto">
          <v-card-text>
            <h1 class="text-center display-1" style="color:#f95738">Exercícios</h1>
          </v-card-text>
        </v-card>
        <!-- <v-data-table
            :headers="blocosHeaders"
            :items="blocos"
            item-key="name"
            hide-default-footer
            class="elevation-1"
          >
        <template v-slot:expanded-item="{ headers, item }">
            <td :colspan="headers.length">More info about {{ item.name }}</td>
        </template>
          </v-data-table> -->
        <draggable
          :list="blocos"
          :animation="200"
          ghost-class="moving-card"
          group="users"
          filter=".action-button"
          class="w-full max-w-md mt-2"
          tag="ul"
        >
          <v-card v-for="bloco in blocos" :key="bloco.nome" class="mr-6 mb-2">
            <div class="flex justify-between cursor-move border border-white">
              <v-icon>mdi-drag</v-icon>
              <v-list class="transparent">
                <v-list-item
                  ><v-list-item-title class="title">{{
                    bloco.nome
                  }}</v-list-item-title></v-list-item
                >
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
              <v-col cols="12" sm="6">
                <v-combobox
                  v-model="modo"
                  :items="['Repetições', 'Duração']"
                  label="Modo"
                  color="#f95738"
                  required
                >
                </v-combobox>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field
                  v-model="valor_modo"
                  label="Valor (unidades ou segundos)*"
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
import draggable from "vuedraggable";

import axios from "axios";

export default {
  name: "CriarTreino",
  components: {
    NavBar,
    SideBar,
    draggable,
  },
  created() {
    document.title = "Criar Treino";
  },
  data() {
    return {
      dialog: false,
      items: [],
      exercicio: "",
      modo: "",
      valor: "",
      series: "",
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
      // blocosHeaders: [
      //   {
      //     text: "Nome Exercício",
      //     align: "start",
      //     sortable: false,
      //     value: "nome",
      //   },
      //   { text: "Séries", value: "series" },
      //   { text: "Repetições/Duração", value: "valor_repeticao" },
      //   { text: "Descanso", value: "valor_descanso" },
      // ],
      blocos: [],
    };
  },
  mounted() {
    axios
      .get("http://localhost:4576/api/exercicio/getNomesExercicios", {
        headers: { token: localStorage.getItem("token") },
      })
      .then((response) => {
        console.log(response);
        if (response.status == "200") {
          this.items = response.data;
          console.log("GR8 SUCC");
        }
      });
  },
  methods: {
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
        exercicios: exs,
      };
      axios
        .post("http://localhost:4576/api/treinos/novoTreino", post_body, {
          headers: { token: localStorage.getItem("token") },
        })
        .then((response) => {
          console.log(response);
          if (response.status == "200") {
            console.log("GR8 SUCC");
            this.$router.push("/treinos");
            //VER SE ELE E TREINADOR OU UTILIZADOR ANTES DO PUSH
            //SENDO QUE O TREINADOR TEM DE IR PARA /treinador/treinos
          }
        });
    },
    criar() {
      var rep_valor = "",
        tipo = true;
      switch (this.modo) {
        case "Repetições":
          rep_valor = "Repetições: " + this.valor_modo + " reps";
          tipo = true;
          break;
        case "Duração":
          rep_valor ="Duração: " + this.valor_modo + "s";
          tipo = false;
          break;
        default:
          rep_valor = "";
          break;
      }
      var bloco_exercicios = {
        nome: this.exercicio,
        series: this.series,
        repeticao: this.valor_modo,
        valor_repeticao: rep_valor,
        descanso: this.valor_descanso,
        tipo: tipo,
        valor_descanso: this.valor_descanso + "s",
      };
      this.blocos.push(bloco_exercicios);
      this.exercicio = "";
      this.modo = "";
      this.valor_modo = "";
      this.valor = "";
      this.series = "";
      this.descanso = "";
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