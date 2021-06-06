package ft.backend.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ft.backend.beans.gestao_utilizadores;
import ft.backend.utils.Authorization;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/user")
public class UtilizadorController {
    
    @Autowired
    gestao_utilizadores gestao_utilizadores;

    @PostMapping(value = "/mudarEmail")
    public ResponseEntity<String> mudarEmail(@RequestHeader String token, @RequestBody String t){
        JSONObject obj;
        String username = null;
        try{
            obj = new JSONObject(Authorization.extractClaims(token));
            if(!obj.getBoolean("treinador_utilizador")){
                username = obj.getString("username");
                if(!gestao_utilizadores.usernameExisteU(username)){
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
        obj = new JSONObject(t);
        boolean b = gestao_utilizadores.mudarEmail(username, obj.getString("email"));
        if (b){
            return ResponseEntity.ok().body("");
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    
    @PostMapping(value = "/mudarUsername")
    public ResponseEntity<String> mudarUsername(@RequestHeader String token, @RequestBody String t){
        JSONObject obj;
        String username = null;
        try{
            obj = new JSONObject(Authorization.extractClaims(token));
            if(!obj.getBoolean("treinador_utilizador")){
                username = obj.getString("username");
                if(!gestao_utilizadores.usernameExisteU(username)){
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
        obj = new JSONObject(t);
        String u_novo = obj.getString("username_novo");
        boolean b = gestao_utilizadores.mudarUsername(username, u_novo);
        if (b){
            obj = new JSONObject();
            obj.put("token", Authorization.generateToken(u_novo, false));
            return ResponseEntity.ok().body(obj.toString());
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
