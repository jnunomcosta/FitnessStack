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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ft.backend.beans.gestao_treinadores;
import ft.backend.beans.gestao_utilizadores;
import ft.backend.entities.*;
import ft.backend.requests.PedidoLogin;
import ft.backend.responses.RespostaLogin;
import ft.backend.utils.Authorization;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/login")
public class LoginController {

    @Autowired
    gestao_utilizadores gu;

    @Autowired
    gestao_treinadores gt;
    
    @PostMapping(value = "/user")
    public ResponseEntity<String> login_utilizador(@RequestBody PedidoLogin pl){

        if(!gu.loginUser(pl.getUsername(), pl.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("null");
        }
        JSONObject obj = new JSONObject();
        obj.put("token", Authorization.generateToken(pl.getUsername(), false));

        return ResponseEntity.ok().body(obj.toString());
    }

    @PostMapping(value = "/treinador")
    public ResponseEntity<String> login_treinador(@RequestBody PedidoLogin pl){

        if(!gt.loginTreinador(pl.getUsername(), pl.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("null");
        }
        JSONObject obj = new JSONObject();
        obj.put("token", Authorization.generateToken(pl.getUsername(), true));

        return ResponseEntity.ok().body(obj.toString());
    }

}
