package ft.backend.controllers;

import ft.backend.utils.Authorization;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ft.backend.beans.gestao_administradores;
import ft.backend.beans.gestao_verificacoes;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/admin")
public class AdministradorController {

    @Autowired
    gestao_administradores ga;
    @Autowired
    gestao_verificacoes verify;


    @GetMapping(value = "/listar")
    public ResponseEntity<String> getAdmins(@RequestHeader String token){
        String username = verify.verifyAdmin(token);

        if(username !=null){
            return ResponseEntity.ok().body(ga.getAdmins().toString());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping(value = "/getAdminInfo/{username}")
    public ResponseEntity<String> getAdminInfo(@PathVariable String username,@RequestHeader String token){ //@RequestHeader String token,
        String usernamet = verify.verifyAdmin(token);

        if(usernamet != null){
            JSONObject obj = ga.getAdminInformation(username);
            if(obj!=null){
                return ResponseEntity.ok().body(obj.toString());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); 
    }

    @GetMapping(value = "/getSideBarAdminInfo/{username}")
    public ResponseEntity<String> getSideBarInfoUser(@PathVariable String username,@RequestHeader String token){
      
        String usernamet = verify.verifyAdmin(token);

        if( usernamet !=null ){
      
            JSONObject obj = ga.getSideBarAdminInformation(username);
            if(obj!=null)
                return ResponseEntity.ok().body(obj.toString());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        
    }


    @PostMapping(value = "/mudarEmail")
    public ResponseEntity<String> mudarEmail(@RequestBody String t,@RequestHeader String token){
        String username = null;
        if((username = verify.verifyAdmin(token)) != null){
            JSONObject obj = new JSONObject(t);
            if (ga.mudarEmail(username, obj.getString("email"))) {
                return ResponseEntity.ok().body("");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        
    }

    @PostMapping(value = "/mudarUsername")
    public ResponseEntity<String> mudarUsername(@RequestBody String t,@RequestHeader String token){
        String username = null;
        if((username = verify.verifyAdmin(token)) !=null){
            JSONObject obj = new JSONObject(t);
            if (ga.mudarUsername(username, obj.getString("username_novo"))){
                JSONObject rep = new JSONObject();
                rep.put("token", Authorization.generateToken(obj.getString("username_novo"), 2));
                return ResponseEntity.ok().body(rep.toString());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    
    @PostMapping(value = "/mudarPassword")
    public ResponseEntity<String> mudarPassword( @RequestBody String t,@RequestHeader String token){
        String username = null;
        if((username = verify.verifyAdmin(token)) != null){
            JSONObject obj = new JSONObject(t);
            String oldP = obj.getString("old_password");
            String newP = obj.getString("new_password");
            if (ga.mudarPassword(username, oldP,newP)) {
                return ResponseEntity.ok().body("");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        
    }

}
