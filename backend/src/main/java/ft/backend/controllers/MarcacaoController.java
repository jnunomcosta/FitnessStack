package ft.backend.controllers;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ft.backend.beans.gestao_marcacoes;
import ft.backend.responses.RespostaAgenda;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/agenda")
public class MarcacaoController {

    @Autowired
    gestao_marcacoes gm;
    
    @GetMapping(value = "/getAgenda")
    public ResponseEntity<String> getAgenda(@RequestParam String username){
        return ResponseEntity.ok().body(gm.getAgendaUtilizador(username).toString());
    }

    @PostMapping(value = "/novaMarcacao")
    public ResponseEntity<String> novaMarcacao(@RequestBody String marc){

        JSONObject obj = new JSONObject(marc);
        String cod_treino = obj.getString("treino");
        String username = obj.getString("utilizador");
        String cor = obj.getString("cor");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date td = df.parse(obj.getString("data_hora"),new ParsePosition(0));

        if(gm.novaMarcacao(td, username, cod_treino, cor)){
            return ResponseEntity.ok().body("{\"Great\":\"Success\"}");
        }

        return ResponseEntity.badRequest().body(null);
    }

}
