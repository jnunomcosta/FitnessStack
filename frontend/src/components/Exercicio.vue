<template>
  <div>
    <v-toolbar dense rounded class="mx-6 mt-6">
      <v-text-field
        v-model="searchValue"
        @input="searchbarInput()"
        hide-details
        prepend-icon="mdi-magnify"
        label="Procurar exercício"
        single-line
        color="#f95738"
      ></v-text-field>
      <v-btn icon>
        <v-icon>mdi-filter</v-icon>
      </v-btn>
    </v-toolbar>
    <h5 class="mb-4 ml-8" style="color: #5b5b5b">
      <span v-text="visibleExercicios"></span> de
      <span v-text="total"></span> exercícios
    </h5>
    <v-row class="fill-height overflow-y-auto" v-if="exercicios.length">
      <v-col
        lg="3"
        md="4"
        sm="6"
        cols="12"
        v-for="(ex, index) in exercicios"
        :key="index"
      >
        <v-sheet min-height="150px" class="fill-height" color="transparent">
          <v-lazy
            v-model="medicine.isActive"
            :options="{ threshold: 0.5 }"
            class="fill-height"
          >
          <v-card hover class="white">
            <v-img
              :src="linkfoto() + ex.conteudo_media[0]"
              class="white--text align-end"
              height="150px"
            >
            </v-img>
            <v-card-title>{{ ex.nome }}</v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>

              <v-dialog transition="dialog-bottom-transition" width="1000">
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
                  <v-card>
                    <v-toolbar color="#f95738" dark
                      ><h3>{{ ex.nome }}</h3>
                      <v-spacer></v-spacer>
                      <v-btn icon @click="dialog.value = false"
                        ><v-icon>mdi-close</v-icon></v-btn
                      >
                    </v-toolbar>
                    <v-card-text>
                      <v-row class="w-100">
                        <v-col cols="12" md="3">
                          <div
                            class="d-flex flex-no-wrap justify-space-between"
                          >
                            <div class="mt-6">
                              <v-list-item two-line>
                                <v-list-item-content>
                                  <v-list-item-title
                                    >Descrição</v-list-item-title
                                  >
                                  <v-list-item-subtitle>{{
                                    ex.descricao
                                  }}</v-list-item-subtitle>
                                </v-list-item-content>
                              </v-list-item>

                              <v-list-item two-line>
                                <v-list-item-content>
                                  <v-list-item-title
                                    >Duração média</v-list-item-title
                                  >
                                  <v-list-item-subtitle
                                    >{{ ex.duracao }}s</v-list-item-subtitle
                                  >
                                </v-list-item-content>
                              </v-list-item>

                              <v-list-item two-line>
                                <v-list-item-content>
                                  <v-list-item-title
                                    >Material necessário</v-list-item-title
                                  >
                                  <v-list-item-subtitle>{{
                                    ex.material
                                  }}</v-list-item-subtitle>
                                </v-list-item-content>
                              </v-list-item>
                            </div>
                          </div>
                        </v-col>

                        <v-col md="9">
                          <div>
                            <v-carousel
                              :show-arrows="false"
                              class="mt-6"
                              height="400"
                              width="700"
                            >
                              <v-carousel-item
                                v-for="(item, i) in ex.conteudo_media"
                                :key="i"
                              >
                                <template v-if="item.includes('photo')">
                                  <img :src="linkfoto() + item" />
                                </template>
                                <template v-else>
                                  <video controls>
                                    <source
                                      :src="linkfoto() + item"
                                      type="video/mp4"
                                    />
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
          </v-lazy
          >
        </v-sheet>
      </v-col>
    </v-row>
    <v-row v-else-if="total == 0">
      <v-col class="text-center">
        <h5 style="padding-top: 150px; padding-bottom: 400px; color: #5b5b5b">
          Ainda não foi registado nenhum exercício
        </h5>
      </v-col>
    </v-row>
    <v-row v-else>
      <v-col class="text-center">
        <h5 style="padding-top: 150px; padding-bottom: 400px; color: #5b5b5b">
          Não foi encontrado nenhum exercício
        </h5>
      </v-col>
    </v-row>
  </div>
</template>

<script>
//import axios from "axios";

export default {
  components: {},
  name: "Exercicio",
  data() {
    return {
      total: 0,
      skip: 0,
      searchValue: "",
      totalExercicios: 0,
      exercicios: [],
      exerciciosBackup: [],
      page: 1,
    };
  },
  computed: {
    visibleExercicios() {
      return this.exercicios.filter((p) => p.isActive).length;
    },
  },
  mounted() {
    this.pullData();
    this.getDataTotal();
  },
  // mounted() {
  //   axios
  //     .get(process.env.VUE_APP_BASELINK + "/api/exercicio/listar", {
  //       headers: { token: localStorage.getItem("token") },
  //     })
  //     .then((response) => {
  //       this.titles = response.data;
  //     });
  // },
  methods: {
    linkfoto() {
      return process.env.VUE_APP_BASELINK;
    },
    // pullData() {
    //   this.skip = this.totalMedicines == 0 ? 0 : this.skip + 10;

    //   Medicines.getMine(this.skip, this.searchValue)
    //     .then((response) => {
    //       if (response.status == 400) {
    //         this.generalErrors = response.data.general_errors;
    //       } else {
    //         response.data.box.forEach((element) => {
    //             element.box.due_date = element.box.due_date.substr(0, 10);
    //           if (element.box.open_date) {
    //             element.box.open_date = element.box.open_date.substr(0, 10);
    //           }
    //           this.medicines.push(element);
    //           this.medicinesBackup.push(element);
    //         });
    //         this.totalMedicines = response.data.total;
    //       }
    //     })
    //     .catch((error) => {
    //       console.log(error);
    //     });
    // },
    // getDataTotal() {
    //   this.skip = this.totalMedicines == 0 ? 0 : this.skip + 10;

    //   Medicines.getMine(this.skip, "")
    //     .then((response) => {
    //       if (response.status == 400) {
    //         this.generalErrors = response.data.general_errors;
    //       } else {
    //         this.total = response.data.box.length;
    //       }
    //     })
    //     .catch((error) => {
    //       console.log(error);
    //     });
    // },
    // searchbarInput(event) {
    //   const searchContent = this.searchValue;
    //   this.medicines = this.medicinesBackup;
    //   this.totalMedicines = 0;
    //   this.medicines = [];
    //   this.medicinesBackup = [];
    //   this.searchValue = searchContent;
    //   this.pullData();
    //   if (!searchContent) {
    //     return;
    //   }
    //   if (searchContent && searchContent.trim() !== "") {
    //     this.medicines = this.medicines.filter((c) => {
    //       if (c.box.name && searchContent) {
    //         return (
    //           c.box.name.toLowerCase().indexOf(searchContent.toLowerCase()) > -1
    //         );
    //       }
    //     });
    //   }
    // },
  },
};
</script>
