package ft.backend.controllers;

import ft.backend.utils.Authorization;
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

    @GetMapping(value = "/getTreinadorInfo")
    public ResponseEntity<String> getInfoTreinador(@RequestHeader String token){
        JSONObject obj;
        String username = null;
        try{
            obj = new JSONObject(Authorization.extractClaims(token));
            if(obj.getBoolean("treinador_utilizador")){
                username = obj.getString("username");
                if(!gestao_treinadores.usernameExisteT(username)){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
                }
            }
            else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        obj = gestao_treinadores.getTreinadorInformation(username);
        if(obj!=null){
            return ResponseEntity.ok().body(obj.toString());
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(value = "/getSideBarTreinadorInfo")
    public ResponseEntity<String> getSideBarInfoUser(@RequestHeader String token) {
        JSONObject obj;
        String username = null;
        try {
            obj = new JSONObject(Authorization.extractClaims(token));
            if (obj.getBoolean("treinador_utilizador")) {
                username = obj.getString("username");
                if (!gestao_treinadores.usernameExisteT(username)) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        obj = gestao_treinadores.getSideBarTreinadorInformation(username);
        if (obj != null) {
            return ResponseEntity.ok().body(obj.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
