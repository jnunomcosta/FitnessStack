package ft.backend.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ft.backend.beans.gestao_utilizadores;
import ft.backend.entities.*;
import ft.backend.repositories.UtilizadorDAO;
import ft.backend.requests.PedidoLogin;
import ft.backend.responses.RespostaLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/login")
public class LoginController {

    @Autowired
    UtilizadorDAO uDao;
    
    @PostMapping(value = "/user")
    public ResponseEntity<RespostaLogin> login_utilizador(@RequestBody PedidoLogin pl){

        //String email = pl.getEmail();
        //String password = pl.getPassword();
        Utilizador u = uDao.findUtilizador_Email_Password(pl.getEmail(), pl.getPassword());
        if(u==null){
            return ResponseEntity.badRequest().body(null);
        }

        Map<String, Object> claims = new HashMap<>();
        String token = 'U' +  Jwts.builder().setClaims(claims).setSubject(pl.getEmail()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) //valido por 10h
                .signWith(SignatureAlgorithm.HS256, "augustooooo").compact();

        RespostaLogin ret = new RespostaLogin();
        ret.setToken(token);

        return ResponseEntity.ok().body(ret);
    }

}
