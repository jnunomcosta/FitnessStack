var axios = require('axios');

var colors = ["blue","indigo","deep-purple","cyan","green","orange","grey darken-1"];

function generateRandomIntegerInRange(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

function randomDate(start, end) {
    return new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));
}

//randomDate(new Date(2012, 0, 1), new Date())

async function tudo(){
    var cods_treinos = [];
    let response = await axios.get("http://34.91.184.185/api/treinos/getCodigos",{headers: { token: "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJ0aXBvXCI6MCxcInVzZXJuYW1lXCI6XCJUb3plNDIwXCJ9IiwiaWF0IjoxNjIzNzY3NDk3LCJleHAiOjE2MjM4MDM0OTd9.SPU_K9q4UKxDTK-T2EPRSej60rVp2fhia0Cg2eEhGJM"}})
    if(response.status == 200){
        cods_treinos = response.data;
    } 
    //console.log(cods_treinos);
    //console.log(cods_treinos.length);

    for(var i = 0;i<250;i++){
        var data = randomDate(new Date(2021, 1, 1), new Date(2021,12,31));
        var marcacao = {
            treino: cods_treinos[generateRandomIntegerInRange(0,cods_treinos.length-1)],
            data_hora: data.getFullYear()+'-'+data.getMonth()+'-'+data.getDay()+' '+generateRandomIntegerInRange(0,23)+':'+generateRandomIntegerInRange(0,59),
            utilizador: "Toze420",
            cor: colors[generateRandomIntegerInRange(0,colors.length-1)],
        }

        console.log(marcacao);
        response = await axios.post("http://34.91.184.185/api/agenda/novaMarcacao",marcacao,{headers: { token: "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJ0aXBvXCI6MCxcInVzZXJuYW1lXCI6XCJUb3plNDIwXCJ9IiwiaWF0IjoxNjIzNzY3NDk3LCJleHAiOjE2MjM4MDM0OTd9.SPU_K9q4UKxDTK-T2EPRSej60rVp2fhia0Cg2eEhGJM"}});
        if(response.status == 200){
            console.log("correu mt bem");
        }
    }

    
}

tudo();




