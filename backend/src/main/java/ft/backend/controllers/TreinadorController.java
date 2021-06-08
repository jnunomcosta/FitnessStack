package ft.backend.controllers;

import ft.backend.utils.Authorization;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ft.backend.beans.gestao_treinadores;
import ft.backend.beans.gestao_verificacoes;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/treinador")
public class TreinadorController {
    
    @Autowired
    gestao_treinadores gestao_treinadores;
    @Autowired
    gestao_verificacoes verify;

    @GetMapping(value = "/listar")
    public ResponseEntity<String> getTreinadores(@RequestHeader String token){


        if( verify.verifyTreinador(token) !=null || verify.verifyAdmin(token) !=null || verify.verifyUser(token) !=null ){
            JSONArray a = gestao_treinadores.getTreinadores();
            return ResponseEntity.ok().body(a.toString());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    

    @GetMapping(value = "/getTreinadorInfo")
    public ResponseEntity<String> getInfoTreinador(@RequestHeader String token,@RequestParam String username){
       
        if( verify.verifyTreinador(token) !=null || verify.verifyAdmin(token) !=null || verify.verifyUser(token) !=null ){

           JSONObject obj = gestao_treinadores.getTreinadorInformation(username);
            if(obj!=null){
                return ResponseEntity.ok().body(obj.toString());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping(value = "/getSideBarTreinadorInfo")
    public ResponseEntity<String> getSideBarInfoUser(@RequestHeader String token) {
        String username = verify.verifyTreinador(token);

        if( username !=null ){

            JSONObject obj = gestao_treinadores.getSideBarTreinadorInformation(username);
            if (obj != null) {
                return ResponseEntity.ok().body(obj.toString());
            } 
           
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}
