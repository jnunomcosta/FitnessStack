<template>
  <v-app>
    <div class="criar_treino_page">
      <NavBar />
      <v-row class="mt-10">
        <v-col cols="12" md="1">
          <SideBar />
        </v-col>
        <v-col cols="12" md="5">
          <v-card-text class="mt-16 pt-10" style="margin-bottom: 180px">
            <h1 class="text-center display-2" style="color: #f95738">
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
            <div class="text-center my-16">
              <v-btn v-on:click="confirmar()" color="#f95738" dark
                >Confirmar</v-btn
              >
            </div>
          </v-card-text>
        </v-col>
        <v-col cols="12" md="5">
          <v-card-text class="mt-16">
            <h1 class="text-center display-1">Exercícios</h1>
          </v-card-text>
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
            class="w-full max-w-md"
            tag="ul">
            <v-card v-for="bloco in blocos" :key="bloco.nome">
                <li class="p-4 mb-3 flex justify-between items-center bg-white shadow rounded-lg cursor-move border border-white">
                    <div class="flex items-center">
                        <p class="ml-2 text-gray-700 font-semibold font-sans tracking-wide">{{bloco.nome}}</p>
                        <label>{{bloco.series}}</label>
                        <p>{{bloco.repeticoes}}</p>
                        <p>{{bloco.descanso}}</p>
                    </div>
                </li>
            </v-card>
        </draggable>
        </v-col>
      </v-row>
      <CriarBloco />
    </div>
  </v-app>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import SideBar from "@/components/SideBar_User.vue";
import CriarBloco from "@/components/CriarBloco.vue";
import draggable from "vuedraggable";

//import axios from 'axios';

export default {
  name: "CriarTreino",
  components: {
    NavBar,
    SideBar,
    draggable,
    CriarBloco
  },
  created() {
    document.title = "Criar Treino";
  },
  data() {
    return {
      input: {
        nome_treino: "",
        descricao: "",
        categorias: "",
        dificuldade: "",
      },
      select_categorias: [],
      categorias: ["Pernas", "Braços", "Peito", "Cardio"],
      select_dificuldade: [],
      dificuldades: ["Iniciante", "Intermédio", "Avançado"],
      blocosHeaders: [
        {
          text: "Nome Exercício",
          align: "start",
          sortable: false,
          value: "nome",
        },
        { text: "Séries", value: "series" },
        { text: "Repetições", value: "repeticoes" },
        { text: "Descanso", value: "descanso" },
      ],
      blocos: [
        {
          nome: "Flexões",
          series: 3,
          repeticoes: "15reps",
          descanso: "30s",
        },
        {
          nome: "Prancha",
          series: 3,
          repeticoes: "30seg",
          descanso: "30s",
        },
      ],
    };
  },
  mounted() {},
  methods: {
    confirmar() {},
  },
};
</script>