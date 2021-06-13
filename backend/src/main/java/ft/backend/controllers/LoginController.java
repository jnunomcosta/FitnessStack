package ft.backend.controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

import ft.backend.beans.*;
import ft.backend.requests.PedidoLogin;
import ft.backend.utils.Authorization;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/login")
public class LoginController {

    @Autowired
    gestao_utilizadores gu;

    @Autowired
    gestao_treinadores gt;

    @Autowired
    gestao_administradores ga;

    @Autowired
    gestao_verificacoes gv;
    
    @PostMapping(value = "/user")
    public ResponseEntity<String> login_utilizador(@RequestBody PedidoLogin pl){

        if(!gu.loginUser(pl.getUsername(), pl.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("null");
        }
        JSONObject obj = new JSONObject();
        obj.put("token", Authorization.generateToken(pl.getUsername(), 0));

        return ResponseEntity.ok().body(obj.toString());
    }

    @PostMapping(value = "/treinador")
    public ResponseEntity<String> login_treinador(@RequestBody PedidoLogin pl){

        if(!gt.loginTreinador(pl.getUsername(), pl.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("null");
        }
        JSONObject obj = new JSONObject();
        obj.put("token", Authorization.generateToken(pl.getUsername(), 1));

        return ResponseEntity.ok().body(obj.toString());
    }

    @PostMapping(value = "/administrador")
    public ResponseEntity<String> login_administrador(@RequestBody PedidoLogin pl){

        if(!ga.loginAdministrador(pl.getUsername(), pl.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("null");
        }

        JSONObject obj = new JSONObject();
        obj.put("token", Authorization.generateToken(pl.getUsername(), 2));

        return ResponseEntity.ok().body(obj.toString());
    }

    @GetMapping(value = "/validateToken")
    public ResponseEntity<String> validateToken(@RequestHeader String token){
        if(gv.verifyUser(token) != null || gv.verifyTreinador(token) != null || gv.verifyAdmin(token) != null) {
            return ResponseEntity.ok().body("");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}
