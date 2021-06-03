<template>
  <v-dialog v-model="dialog" persistent max-width="600px">
    <template v-slot:activator="{ on, attrs }">
        <v-btn fab dark large color="#f95738" fixed right bottom v-bind="attrs" v-on="on">
        <v-icon>mdi-plus</v-icon>
      </v-btn>
    </template>
    <v-card>
      <v-card-title>
        <span class="headline">Criar Bloco</span>
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-row>
            <v-col cols="12">
              <v-autocomplete 
                label="Exercício*"
                v-model="exercicio"
                :items="items"
                required>
                </v-autocomplete>
            </v-col>
            <v-col cols="12">
              <v-text-field 
                v-model="series" 
                label="Séries*" 
                type="number" 
                @click:append-outer="increment" 
                @click:prepend="decrement">
                </v-text-field>
            </v-col>
                <v-col cols="12" sm="6">
                    <v-combobox
                        v-model="modo"
                        :items='["Repetições","Duração"]'
                        label="Modo"
                        required
                    >
                    </v-combobox>
                </v-col>
                <v-col cols="12" sm="6">
                    <v-text-field 
                        v-model="valor_modo" 
                        label="Valor (unidades ou segundos)*" 
                        type="number" >
                    </v-text-field>
                </v-col>
            <v-col cols="12" >
              <v-text-field 
                    v-model="valor_descanso" 
                    label="Descanso (segundos)*" 
                    type="number" >
                </v-text-field>
            </v-col>
          </v-row>
        </v-container>
        <small>* campos obrigatórios</small>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="#f95738" text @click="dialog = false">
          Fechar
        </v-btn>
        <v-btn color="#f95738" text @click="dialog = false"> Criar </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: "CriarBloco",
  data: () => ({
    dialog: false,
    items: ["Flexões","Prancha","Agachamentos"],
    exercicio: "",
    modo: "",
    valor: "",
    series: ""
  }),
  methods: {
    increment () {
      this.series = parseInt(this.series,10) + 1
    },
    decrement () {
      this.series = parseInt(this.series,10) - 1
    },
  }
};
</script>
