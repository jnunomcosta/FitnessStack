package ft.backend.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
    @PostMapping(value = "/user")
    public ResponseEntity<RespostaLogin> login_utilizador(@RequestBody PedidoLogin pl){

        //VER A CENA DA PASSWORD!

        Utilizador u = gu.loginUser(pl.getUsername(), pl.getPassword());
        if(u==null){
            return ResponseEntity.badRequest().body(null);
        }

        RespostaLogin ret = new RespostaLogin();
        ret.setToken(Authorization.generateToken(u.getUsername(), false));

        return ResponseEntity.ok().body(ret);
    }

    @PostMapping(value = "/treinador")
    public ResponseEntity<RespostaLogin> login_treinador(@RequestBody PedidoLogin pl){

        //VER A CENA DA PASSWORD!

        Treinador u = gu.loginTreinador(pl.getUsername(), pl.getPassword());
        if(u==null){
            return ResponseEntity.badRequest().body(null);
        }

        RespostaLogin ret = new RespostaLogin();
        ret.setToken(Authorization.generateToken(u.getUsername(), true));

        return ResponseEntity.ok().body(ret);
    }

}
