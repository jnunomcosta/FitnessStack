<template>
  <v-container>
    <h5 class="mb-4 ml-8" style="color: #5b5b5b">
      {{ titles.length }} exercícios
    </h5>
    <v-row class="fill-height overflow-y-auto" v-if="titles.length">
      <v-col
        lg="3"
        md="4"
        sm="6"
        cols="12"
        v-for="(title, index) in titles"
        :key="index"
      >
        <v-sheet min-height="150px" class="fill-height" color="transparent">
          <v-card hover class="white">
            <v-img
              :src="'http://localhost:4576' + title.conteudo_media[0]"
              class="white--text align-end"
              height="150px"
            >
            </v-img>
            <v-card-title>{{ title.nome }}</v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>

              <v-dialog transition="dialog-bottom-transition" width=1000>
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    class="mx-2 my-2"
                    v-bind="attrs"
                    v-on="on"
                    small
                    color="#f95738"
                    dark
                  >
                    <v-icon>mdi-text-box-search</v-icon>
                  </v-btn>
                </template>
                <template v-slot:default="dialog">
                  <v-card >
                    <v-toolbar color="#f95738" dark
                      ><h3>{{ title.nome }}</h3>
                      <v-spacer></v-spacer>
                      <v-btn icon @click="dialog.value = false"
                        ><v-icon>mdi-close</v-icon></v-btn
                      >
                    </v-toolbar>
                    <v-card-text>
                      <v-row class="w-100">
                      <v-col cols="12" md="3">   
                      <div class="d-flex flex-no-wrap justify-space-between">
                        <div class="mt-6">
                          <v-list-item two-line>
                            <v-list-item-content>
                              <v-list-item-title>Descrição</v-list-item-title>
                              <v-list-item-subtitle>{{
                                title.descricao
                              }}</v-list-item-subtitle>
                            </v-list-item-content>
                          </v-list-item>

                          <v-list-item two-line>
                            <v-list-item-content>
                              <v-list-item-title
                                >Duração média</v-list-item-title
                              >
                              <v-list-item-subtitle
                                >{{ title.duracao }}s</v-list-item-subtitle
                              >
                            </v-list-item-content>
                          </v-list-item>

                          <v-list-item two-line>
                            <v-list-item-content>
                              <v-list-item-title
                                >Material necessário</v-list-item-title
                              >
                              <v-list-item-subtitle>{{
                                title.material
                              }}</v-list-item-subtitle>
                            </v-list-item-content>
                          </v-list-item>
                        </div>
                        </div>
                        </v-col>
                        
                        <v-col md="9">
                        <div>
                          <v-carousel :show-arrows="false" class="mt-6" height=400 width=700>
                            <v-carousel-item
                              v-for="(item,i) in title.conteudo_media"
                              :key="i"
                            >
                            <template v-if="item.includes('photo')">
                              <img :src="'http://localhost:4576' + item">
                            </template>
                            <template v-else>
                              <video controls>
                                <source :src="'http://localhost:4576' + item" type="video/mp4">
                              </video>
                            </template>
                            </v-carousel-item>
                          </v-carousel> 
                        </div>
                        </v-col>
                        </v-row>
                    </v-card-text>
                  </v-card>
                </template>
              </v-dialog>
            </v-card-actions>
          </v-card>
          <v-card v-intersect="infiniteScrolling"></v-card>
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  components: {
    
  }, 
  name: "Exercicio",
  data() {
    return {
      titles: [],
      page: 1,
      
    };
  },
  computed: {
  },
  mounted() {
    axios
      .get("http://localhost:4576/api/exercicio/listar",{headers: {'token': localStorage.getItem("token")}})
      .then(response => {
        this.titles = response.data;
      })
  },
  methods: {
  },
};
</script>
