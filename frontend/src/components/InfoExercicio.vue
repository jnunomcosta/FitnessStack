<template>
  <v-dialog transition="dialog-bottom-transition" max-width="600">
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        class="mx-2 my-2"
        v-bind="attrs"
        v-on="on"
        small
        color="#f95738"
        dark
      >
        VER MAIS
      </v-btn>
    </template>
    <template v-slot:default="dialog">
      <v-card>
        <v-toolbar color="#f95738" dark
          ><h3>Agachamentos</h3>
          <v-spacer></v-spacer>
          <v-btn icon @click="dialog.value = false"
            ><v-icon>mdi-close</v-icon></v-btn
          >
        </v-toolbar>
        <v-card-text>
          <div class="d-flex flex-no-wrap justify-space-between">
            <div class="mt-6">
              <v-list-item two-line>
                <v-list-item-content>
                  <v-list-item-title>Descrição</v-list-item-title>
                  <v-list-item-subtitle
                    >Dobrar as pernas e descer o rabo.</v-list-item-subtitle
                  >
                </v-list-item-content>
              </v-list-item>

              <v-list-item two-line>
                <v-list-item-content>
                  <v-list-item-title>Duração média</v-list-item-title>
                  <v-list-item-subtitle>3s</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>

              <v-list-item two-line>
                <v-list-item-content>
                  <v-list-item-title>Material necessário</v-list-item-title>
                  <v-list-item-subtitle>Nada.</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
            </div>
            <div>
              <v-avatar tile rounded size="200" class="mt-8 mb-4">
                <v-img
                  max-height="200"
                  max-width="200"
                  src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
                ></v-img>
              </v-avatar>
            </div>
          </div>
        </v-card-text>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
export default {
  name: "InfoExercicio",
};
</script>