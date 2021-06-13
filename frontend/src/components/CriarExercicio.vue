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
        ><h3>Novo Exercício</h3>
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
                v-model="conteudos"
                multiple
                accept="image/png, image/jpeg, image/bmp, video/mp4"
                placeholder="Selecione imagens e/ou vídeos"
                prepend-icon="mdi-paperclip"
                label="Demonstração do Exercício"
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
              <v-btn color="#f95738" rounded dark @click="dialog = false" v-on:click="confirmarTreino()" >
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
import axios from 'axios';

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
      material: "",
      minDuracao: 0,
      maxDuracao: 86400,
      conteudos: [],
      rules: [(v) => !!v || "Campo obrigatório"],
      /* mediaRules: [
        (value) =>
          !value ||
          value.size < 2000000 ||
          "O tamanho do ficheiro deve ser inferior a 2 MB",
      ], */
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
    async confirmarTreino(){

      function carrega_conteudomedia(x) {
        return new Promise((resolve) => {
          let blob = new Blob([x]),fileReader = new FileReader();
          fileReader.readAsArrayBuffer(blob);
          fileReader.onload = function () {
            let falg = false;
            let splitted = x.type.split("/");
            if(splitted[0] == "video"){
              falg = true;
            }
            resolve({
              tipo: falg,
              conteudo: Buffer.from(this.result).toString("base64"),
            });
          };
        });
      }

      let conteudos_lindos = [];
      for(let i = 0;i<this.conteudos.length;i++){
        var elou = await carrega_conteudomedia(this.conteudos[i]);
        conteudos_lindos.push(elou)
      }

      var exercicio_post = {
        nome: this.nome,
        descricao: this.descricao,
        duracao_media: this.duracao,
        material_necessario: this.material,
        conteudo_media: conteudos_lindos,
      };
      
      var response = await axios.post(process.env.VUE_APP_BASELINK+'/api/exercicio/novo',exercicio_post,{headers:{'token':localStorage.getItem("token")}})
      if(response.status==200){
        this.nome = "";
        this.descricao = "";
        this.duracao = "";
        this.material = "";
        this.conteudos = [];

        this.$router.go();
      } 

      //VER SE O POST CORREU BEM  
      
      
    },
  },
};
</script>