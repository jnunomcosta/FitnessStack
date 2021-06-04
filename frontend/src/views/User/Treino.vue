<template>
  <div class="profile">
    <NavBar />
    <h1 class="text-center display-2" style="color: #f95738; margin-top: 90px">
      Titulo do treino
    </h1>
    <v-row class="mt-4">
      <v-col cols="12" md="1">
        <SideBar />
      </v-col>
      <v-col cols="12" md="5">
        <v-card>
            <v-list-item-avatar>
              <v-avatar class="mt-4" size="50">
                <v-img
                  src="https://randomuser.me/api/portraits/men/93.jpg"
                ></v-img>
              </v-avatar>
            </v-list-item-avatar>

            <v-list-item-content>
              <v-list-item-title>grhtjy</v-list-item-title>

              <v-list-item-subtitle>teryuio</v-list-item-subtitle>
            </v-list-item-content>
            <div class="mx-auto">
              <v-avatar class="mt-4" size="50">
                <v-img
                  src="https://randomuser.me/api/portraits/men/93.jpg"
                ></v-img>
              </v-avatar>
              <v-card-subtitle> {{ $route.params.codigo }} </v-card-subtitle>
            </div>

            <v-divider class="mx-4"></v-divider>
        </v-card>
      </v-col>
      <v-col cols="12" md="5"> </v-col>
         <v-card>
            <v-list-item-content>
              <v-list-item-title>grhtjy</v-list-item-title>

              <v-list-item-subtitle>teryuio</v-list-item-subtitle>
            </v-list-item-content>
            <div class="mx-auto">
              <v-avatar class="mt-4" size="50">
                <v-img
                  src="https://randomuser.me/api/portraits/men/93.jpg"
                ></v-img>
              </v-avatar>
              <v-card-subtitle> {{ $route.params.codigo }} </v-card-subtitle>
            </div>

            <v-divider class="mx-4"></v-divider>
        </v-card>
    </v-row>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_User.vue";

export default {
  name: "Treino",
  components: {
    NavBar,
    SideBar,
  },
  data: () => ({
    codigo: this.route.params.codigo,
  }),
};
</script>