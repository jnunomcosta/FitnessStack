package ft.backend.controllers;

import ft.backend.entities.Contrato;
import ft.backend.entities.InformacaoFisica;

import org.json.JSONArray;
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
@CrossOrigin("*")
//@CrossOrigin(origins = "*",allowCredentials = "true",methods = {RequestMethod.GET,RequestMethod.POST}, maxAge = 3600,allowedHeaders = "Content-Type, Accept, X-Requested-With, remember-me, Authorization, token")
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
        if(verify.verifyUser(token) !=null || verify.verifyTreinador(token) != null || verify.verifyAdmin(token) != null){
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
        String username = null;
        if((username = verify.verifyUser(token)) != null){
            JSONObject obj = new JSONObject(t);
            if (gestao_utilizadores.mudarEmail(username, obj.getString("email"))){
                return ResponseEntity.ok().body("");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping(value = "/mudarUsername")
    public ResponseEntity<String> mudarUsername(@RequestHeader String token, @RequestBody String t){
        String username = null;
        if((username = verify.verifyUser(token)) != null){
            JSONObject req =  new JSONObject(t);
             if (gestao_utilizadores.mudarUsername(username, req.getString("username_novo"))){
                    JSONObject rep = new JSONObject();
                    rep.put("token", Authorization.generateToken(req.getString("username_novo"), 0));
                    return ResponseEntity.ok().body(rep.toString());
            }          
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
   
    @PostMapping(value = "/mudarPassword")
    public ResponseEntity<String> mudarPassword(@RequestHeader String token, @RequestBody String t){
        String username = null;
        if((username = verify.verifyUser(token)) != null){
            JSONObject obj = new JSONObject(t);
            String oldP = obj.getString("old_password");
            String newP = obj.getString("new_password");
            if (gestao_utilizadores.mudarPassword(username, oldP,newP)){
                return ResponseEntity.ok().body("");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping(value="/mudarImagem")
    public ResponseEntity<String> mudarImagem(@RequestHeader String token, @RequestBody String t){
        String username = null;
        if((username = verify.verifyUser(token)) != null){
            JSONObject obj = new JSONObject(t);
            if(gestao_utilizadores.mudarImagem(username,obj.getString("nova_foto"))){
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

            if(obj.has("altura")){
                gestao_utilizadores.mudarAltura(username, obj.getFloat("altura"));
            }

        gestao_utilizadores.novaInfoFisica(username,i);
       
        return ResponseEntity.ok().body("");

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping(value = "/getHistoricoFisico")
    public ResponseEntity<String> getHistoricoFisico(@RequestHeader String token){
        String username = null;
        if((username = verify.verifyUser(token)) != null){
            return ResponseEntity.ok().body(gestao_utilizadores.getUserFisicalHistory(username).toString());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping(value = "/getPlaylist")
    public ResponseEntity<String> getPlaylist(@RequestHeader String token){
        String username = null;
        if((username = verify.verifyUser(token)) != null){
            return ResponseEntity.ok().body(gestao_utilizadores.getPlaylist(username).toString());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping(value = "/changePlaylist")
    public ResponseEntity<String> changePlaylist(@RequestHeader String token,@RequestBody String t){
        String username = null;
        if((username = verify.verifyUser(token)) != null){
            JSONObject tobj = new JSONObject(t);
            if(gestao_utilizadores.changePlaylist(username, tobj.getString("nova_playlist"))){
                return ResponseEntity.ok().body("");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping(value = "/getHistoricoFisicoTreinador")
    public ResponseEntity<String> getHistoricoFisicoTreinador(@RequestHeader String token,@RequestParam String username){
        if(verify.verifyTreinador(token) != null){
            return ResponseEntity.ok().body(gestao_utilizadores.getUserFisicalHistory(username).toString());
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
        String username = verify.verifyUser(token);
        if(username != null){
            Contrato c = gestao_contratos.getContratoUser(username);
            if(c!=null){
                JSONObject obj = new JSONObject();
                obj.put("utilizador",c.getUtilizador().getUsername());
                obj.put("treinador",c.getTreinador_responsavel().getUsername());
                obj.put("estado",c.isEstado());
                obj.put("comentario",c.getComentario());
                return ResponseEntity.ok().body(obj.toString());
            }   
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping(value = "/criarContrato")
    public ResponseEntity<String> criarContrato(@RequestHeader String token, @RequestBody String t){
        
        String username = verify.verifyUser(token);

        if(username!=null){

          JSONObject obj = new JSONObject(t);
          
          if( gestao_contratos.criarContrato(username,obj.getString("treinador"),obj.getString("comentario")))          
            return ResponseEntity.ok().body("");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }


    @PostMapping(value = "/removerContrato")
    public ResponseEntity<String> removerContrato(@RequestHeader String token){
        
        String username = verify.verifyUser(token);

        if(username!=null){

          
          if( gestao_contratos.removeContratoUser(username) )       
            return ResponseEntity.ok().body("");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @DeleteMapping(value="/deleteUtilizador")
    public ResponseEntity<String> deleteUtilizador(@RequestHeader String token,@RequestBody String cods){
        if(verify.verifyAdmin(token) != null){
            JSONArray arr = new JSONArray(cods);
            for(int i=0;i<arr.length();i++){
                String cod = arr.getString(i);
                gestao_utilizadores.deleteUtilizador(cod);
            }
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    
}
