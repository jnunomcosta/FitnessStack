<template>
  <v-container>
    <h5 class="mb-4 ml-8" style="color: #5b5b5b">
      {{ titles.length }} treinadores
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
              :src="'https://picsum.photos/200'"
              class="white--text align-end"
              height="150px"
            >
            </v-img>
            <!--<v-card-title v-text="title.body"></v-card-title>-->
            <v-card-title>João Ratão</v-card-title>
            <v-card-subtitle>
              <v-row align="center" class="mx-0">
                <v-rating
                  :value="4.5"
                  color="amber"
                  dense
                  half-increments
                  readonly
                  size="14"
                ></v-rating>
                <div class="grey--text ml-4">4.5 (413)</div>
              </v-row>
            </v-card-subtitle>
            <v-card-actions>
              <v-spacer></v-spacer>

              <InfoTreinador />

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
import InfoTreinador from "./InfoTreinador.vue";

export default {
  name: "Treinador",
  components: {
    InfoTreinador,
  },
  data() {
    return {
      titles: [],
      page: 1,
    };
  },
  computed: {
    url() {
      return "https://jsonplaceholder.typicode.com/posts?_page=" + this.page;
    },
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      const response = await axios.get(this.url);
      this.titles = response.data;
    },
    /*infiniteScrolling(entries, observer, isIntersecting) {
      setTimeout(() => {
        this.page++;
        axios
          .get(this.url)
          .then((response) => {
            if (response.data.length > 1) {
              response.data.forEach((item) => this.titles.push(item));
            } 
          })
          .catch((err) => {
            console.log(err);
          });
      }, 500);
    },*/
  },
};
</script>
