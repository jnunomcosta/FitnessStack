var axios = require('axios');

function generateRandomIntegerInRange(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

async function envia(post_body){
    let resp = await axios.post("http://34.91.184.185/api/treinos/novoTreino", post_body, {headers: { token: "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJ0aXBvXCI6MSxcInVzZXJuYW1lXCI6XCJ6ZXplcmVmaXRcIn0iLCJpYXQiOjE2MjM3NjE5ODEsImV4cCI6MTYyMzc5Nzk4MX0.-Es7B7-4ORmSnFbPB9PMV_tqfqo-FPbdraxDw-1iDAU"}});
    if(resp.status == 200){
        console.log("correu tudo bem");
    }
}

var exercicios = ["Flexões","Agachamentos","Prancha","Crunches"];

for(var j = 0;j<100;j++){
    var exs = [];
for (var i = 0; i < generateRandomIntegerInRange(2,12); i++) {
    exs.push({
      nome_exercicio: exercicios[Math.floor(Math.random() * 3)],
      series: Math.floor(Math.random() * 10),
      descanso: Math.floor(Math.random() * 30),
      duracao: Math.floor(Math.random() * 30),
      tipo: Math.floor(Math.random() * 10) % 2 == 0 ? true : false,
    });
  }

var post_body = {
    nome: Math.random().toString(36).replace(/[^a-z]+/g, '').substr(0, 10),
    descricao: Math.random().toString(36).replace(/[^a-z]+/g, '').substr(0, 20),
    categorias: [Math.random().toString(36).replace(/[^a-z]+/g, '').substr(0, 5),Math.random().toString(36).replace(/[^a-z]+/g, '').substr(0, 5),Math.random().toString(36).replace(/[^a-z]+/g, '').substr(0, 5),Math.random().toString(36).replace(/[^a-z]+/g, '').substr(0, 5)],
    dificuldade: Math.random().toString(36).replace(/[^a-z]+/g, '').substr(0, 5),
    duracao_treino: generateRandomIntegerInRange(60,1200),
    exercicios: exs,
};

envia(post_body);
    
} 


//console.log(post_body);