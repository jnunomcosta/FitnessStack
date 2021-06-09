package ft.backend.controllers;

import ft.backend.entities.Contrato;
import ft.backend.entities.InformacaoFisica;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ft.backend.beans.gestao_utilizadores;
import ft.backend.beans.gestao_verificacoes;
import ft.backend.beans.gestao_contratos;
import ft.backend.utils.Authorization;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/user")
public class UtilizadorController {
    

 

    @Autowired
    gestao_utilizadores gestao_utilizadores;
    @Autowired
    gestao_verificacoes verify;
    @Autowired
    gestao_contratos gestao_contratos;




    @GetMapping(value = "/getUser")
    public ResponseEntity<String> getInfoUser(@RequestHeader String token, @RequestParam String username){


        if( verify.verifyUser(token) !=null  || verify.verifyTreinador(token) !=null  || verify.verifyAdmin(token) !=null  ){

            JSONObject obj = gestao_utilizadores.getUserInformation(username);
            if(obj!=null){
                return ResponseEntity.ok().body(obj.toString());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping(value = "/getUserInfo")
    public ResponseEntity<String> getInfoUser(@RequestHeader String token){

        String username= verify.verifyUser(token);

        if( username !=null ){

            JSONObject obj = gestao_utilizadores.getUserInformation(username);
            if(obj!=null){
                return ResponseEntity.ok().body(obj.toString());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping(value = "/getSideBarUserInfo")
    public ResponseEntity<String> getSideBarInfoUser(@RequestHeader String token){
  
        String username= verify.verifyUser(token);

        if( username !=null ){

            JSONObject obj = gestao_utilizadores.getSideBarUserInformation(username);
            if(obj!=null){
             return ResponseEntity.ok().body(obj.toString());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        
    }

    @PostMapping(value = "/mudarEmail")
    public ResponseEntity<String> mudarEmail(@RequestHeader String token, @RequestBody String t){

        String username= verify.verifyUser(token);

        if( username !=null ){
            JSONObject obj = new JSONObject(t);
            boolean b = gestao_utilizadores.mudarEmail(username, obj.getString("email"));
            if (b){
             return ResponseEntity.ok().body("");
    
            }
        }
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping(value = "/mudarUsername")
    public ResponseEntity<String> mudarUsername(@RequestHeader String token, @RequestBody String t){
        
        if( verify.verifyUser(token) !=null ){


            JSONObject req=  new JSONObject(t);

            boolean b = gestao_utilizadores.mudarUsername(req.getString("username"), req.getString("username_novo"));
             if (b){

                    JSONObject rep=  new JSONObject();
                    rep.put("token", Authorization.generateToken(req.getString("username_novo"), 0));
                    return ResponseEntity.ok().body(rep.toString());
            }          
        }   
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
   
    @PostMapping(value = "/mudarPassword")
    public ResponseEntity<String> mudarPassword(@RequestHeader String token, @RequestBody String t){
        
        String username= verify.verifyUser(token);

        if( username  != null ){    
        
            JSONObject obj = new JSONObject(t);
            String oldP = obj.getString("old_password");
            String newP = obj.getString("new_password");

            boolean b = gestao_utilizadores.mudarPassword(username, oldP,newP);
            if (b){
                return ResponseEntity.ok().body(null);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
         
    }

    @PostMapping(value = "/novaInfoFisica")
    public ResponseEntity<String> novaInfoFisica(@RequestHeader String token,@RequestBody String t){

        String username= verify.verifyUser(token);

        if( username  != null ){

            JSONObject obj = new JSONObject(t);

            InformacaoFisica i = new InformacaoFisica();

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            i.setData(df.parse(obj.getString("data"),new ParsePosition(0)));
            i.setPeso(obj.getFloat("peso"));
            i.setM_muscular(obj.getFloat("m_muscular"));
            i.setM_gorda(obj.getFloat("m_gorda"));

        gestao_utilizadores.novaInfoFisica(username,i);
       
        return ResponseEntity.ok().body("");

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<String> getUsers(@RequestHeader String token){
        if(verify.verifyAdmin(token) != null){
            return ResponseEntity.ok().body(gestao_utilizadores.getUsers().toString());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping(value = "/getTreinadorResp")
    public ResponseEntity<String> getTreinador(@RequestHeader String token){
        String username= verify.verifyUser(token);
        if( username != null){

            Contrato c = gestao_contratos.getContratoUser(username);
            if(c!=null){
                JSONObject obj = new JSONObject();

                obj.put("utilizador:",c.getUtilizador().getUsername());
                obj.put("treinador",c.getTreinador_responsavel().getUsername());
                obj.put("estado",c.isEstado());
                obj.put("comentario",c.getComentario());

                return ResponseEntity.ok().body(obj.toString());

            }
                 
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }


    
}
