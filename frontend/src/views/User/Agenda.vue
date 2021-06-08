<template>
  <div class="agenda">
    <NavBar />
    <v-row>
      <v-col cols="12" md="1">
         <SideBar />
      </v-col>
      <v-col cols="12" md="10" style="padding-top: 100px; padding-bottom: 100px">
        <AgendarTreino />
      </v-col>
    </v-row>
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_User.vue";
import AgendarTreino from "@/components/AgendarTreino.vue";

export default {
  name: "Agenda",
  components: {
    NavBar,
    SideBar,
    AgendarTreino,
  },
  created() {
    document.title = "Agenda";
  },
  data: () => ({
  }),
  mounted() {
  },
  methods: {
    
  },
};
</script>

<style>
.agenda {
  background-color: #d3d3d2;
}
</style>