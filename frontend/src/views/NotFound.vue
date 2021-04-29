<template>
  <div class="notFound">
    <v-app-bar color="#40444b" elevate-on-scroll dark>
      <div class="d-flex align-center">
        <router-link to="/">
          <v-img
            alt="Fitness Stack Logo"
            class="shrink mr-2 ml-8"
            contain
            src="@/assets/fitness_stack_logo.png"
            transition="scale-transition"
            width="180"
          />
        </router-link>
      </div>
    </v-app-bar>
    <v-container>
      <v-row>
        <v-col class="text-center my-16 py-16" cols="12" md="6">
          <p class="display-1 font-weight-bold" style="color: #f95738">
            Perdeu-se?
          </p>
          <div class="my-10 mx-16">
            <p>Não foi possível encontrar a página. Lamentamos o transtorno.</p>
          </div>
          <v-btn rounded color="#f95738" dark :href="'/'">Voltar à Home</v-btn>
        </v-col>
        <v-col cols="12" md="6">
          <v-img
            :src="require('../assets/page_not_found.png')"
            contain
            height="100%"
          />
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  name: "NotFound",
  created() {
    document.title = "404 Not Found";
  },
};
</script>

<style>
.notFound {
  background-color: #d3d3d2;
}
</style>