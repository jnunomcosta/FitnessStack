package ft.backend.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ft.backend.beans.gestao_treinadores;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/treinador")
public class TreinadorController {
    
    @Autowired
    gestao_treinadores gestao_treinadores;

    @GetMapping(value = "/listar")
    public String getTreinadores(){
        //falta verificar o token
        JSONArray a = gestao_treinadores.getTreinadores();
        return a.toString();
    }
}
