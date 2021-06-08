package ft.backend.controllers;

import ft.backend.entities.InformacaoFisica;
import ft.backend.entities.Utilizador;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ft.backend.beans.gestao_utilizadores;
import ft.backend.utils.Authorization;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Base64;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/user")
public class UtilizadorController {
    
    @Autowired
    gestao_utilizadores gestao_utilizadores;

    @GetMapping(value = "/getUserInfo")
    public ResponseEntity<String> getInfoUser(@RequestHeader String token){
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

        obj = gestao_utilizadores.getUserInformation(username);
        if(obj!=null){
            return ResponseEntity.ok().body(obj.toString());
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(value = "/getSideBarUserInfo")
    public ResponseEntity<String> getSideBarInfoUser(@RequestHeader String token){
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

        obj = gestao_utilizadores.getSideBarUserInformation(username);
        if(obj!=null){
            return ResponseEntity.ok().body(obj.toString());
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

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
    @PostMapping(value = "/mudarPassword")
    public ResponseEntity<String> mudarPassword(@RequestHeader String token, @RequestBody String t){
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
        String oldP = obj.getString("old_password");
        String newP = obj.getString("new_password");

        boolean b = gestao_utilizadores.mudarPassword(username, oldP,newP);
        if (b){
            return ResponseEntity.ok().body(null);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping(value = "/novaInfoFisica")
    public ResponseEntity<String> novaInfoFisica(@RequestHeader String token,@RequestBody String t){
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

        InformacaoFisica i = new InformacaoFisica();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        i.setData(df.parse(obj.getString("data"),new ParsePosition(0)));
        i.setPeso(obj.getFloat("peso"));
        i.setM_muscular(obj.getFloat("m_muscular"));
        i.setM_gorda(obj.getFloat("m_gorda"));

        gestao_utilizadores.novaInfoFisica(username,i);
        return ResponseEntity.ok().body("");

    }

    @GetMapping(value = "/listar")
    public String getUsers(){

        return gestao_utilizadores.getUsers().toString();
    }

}
