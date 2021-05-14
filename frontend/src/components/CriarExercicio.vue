<template>
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
      <v-toolbar color="#f95738" dark
        ><h3>Novo Exercício (Colocar só para o treinador depois)</h3>
        <v-spacer></v-spacer>
        <v-btn icon @click="dialog = false"><v-icon>mdi-close</v-icon></v-btn>
      </v-toolbar>
      <v-stepper v-model="e1">
        <v-stepper-header>
          <template v-for="n in steps">
            <v-stepper-step
              :key="`${n}-step`"
              :complete="e1 > n"
              :step="n"
              editable
              color="#f95738"
            >
                <span v-if="n==1">Informações</span>
                <span v-else>Imagem/Vídeo</span>
            </v-stepper-step>

            <v-divider v-if="n !== steps" :key="n"></v-divider>
          </template>
        </v-stepper-header>

        <v-stepper-items>
          <v-stepper-content v-for="n in steps" :key="`${n}-content`" :step="n">
            <v-card-text v-if="n == 1">
              <v-text-field
                v-model="nome"
                label="Nome do Exercício"
                :rules="rules"
                type="text"
                color="#f95738"
                required
              ></v-text-field>

              <v-text-field
                v-model="descricao"
                label="Descrição"
                :rules="rules"
                type="text"
                color="#f95738"
                required
              ></v-text-field>

              <v-text-field
                v-model="duracao"
                :rules="rules"
                label="Duração Média (segundos)"
                type="number"
                :min="minDuracao"
                :max="maxDuracao"
                color="#f95738"
                required
              ></v-text-field>

              <v-text-field
                v-model="material"
                label="Material Necessário"
                type="text"
                color="#f95738"
              ></v-text-field>
            </v-card-text>

            <v-card-text v-else-if="n == 2">
              <v-file-input
                :rules="mediaRules"
                accept="image/png, image/jpeg, image/bmp"
                placeholder="Selecione uma imagem ou vídeo"
                prepend-icon="mdi-paperclip"
                label="Demonstração do Exercício (só está a dar imagem para já)"
                color="#f95738"
              ></v-file-input>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                v-if="n == 1"
                color="#f95738"
                rounded
                text
                @click="nextStep(n)"
              >
                Continuar
              </v-btn>
              <v-btn color="#f95738" rounded dark @click="dialog = false">
                Confirmar
              </v-btn>
            </v-card-actions>
          </v-stepper-content>
        </v-stepper-items>
      </v-stepper>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: "CriarExercicio",
  data() {
    return {
      dialog: false,
      e1: 1,
      steps: 2,
      nome: "",
      descricao: "",
      duracao: 0,
      minDuracao: 0,
      maxDuracao: 86400,
      rules: [(v) => !!v || "Campo obrigatório"],
      mediaRules: [
        (value) =>
          !value ||
          value.size < 2000000 ||
          "O tamanho do ficheiro deve ser inferior a 2 MB",
      ],
    };
  },
  watch: {
    steps(val) {
      if (this.e1 > val) {
        this.e1 = val;
      }
    },
  },
  methods: {
    nextStep(n) {
      if (n === this.steps) {
        this.e1 = 1;
      } else {
        this.e1 = n + 1;
      }
    },
    updateTags() {
      this.$nextTick(() => {
        this.tags.push(...this.searchTags.split(","));
        this.$nextTick(() => {
          this.searchTags = "";
        });
      });
    },
  },
};
</script>