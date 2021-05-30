package ft.backend.controllers;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

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
import ft.backend.entities.ConteudoMedia;
import ft.backend.entities.Treinador;
import ft.backend.entities.Utilizador;
import ft.backend.responses.RespostaRegister;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/register")
public class RegisterController {
    
    @Autowired
    gestao_utilizadores gestao_utilizadores;

    @Autowired
    gestao_treinadores gestao_treinadores;

    @PostMapping(value = "/user")
    public ResponseEntity<String> register_utilizador(@RequestBody String pl){
        Utilizador u = new Utilizador();
        JSONObject obj = new JSONObject(pl);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dn = df.parse(obj.getString("data"),new ParsePosition(0));

        u.setEmail(obj.getString("email"));u.setPassword(obj.getString("password"));
        u.setNome(obj.getString("nome"));u.setAltura(obj.getFloat("altura"));
        u.setGenero(obj.getBoolean("genero"));u.setPeso(obj.getFloat("peso"));
        u.setDataNascimento(dn);u.setUsername(obj.getString("username"));

        if(obj.has("foto_perfil")){
            ConteudoMedia foto = new ConteudoMedia();
            foto.setConteudo(Base64.getDecoder().decode(obj.getString("foto_perfil")));
            u.setFoto_perfil(foto);
        }
        
        if(!gestao_utilizadores.registerUser(u)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        } 

        return ResponseEntity.ok().body("");
    }

    @PostMapping(value = "/treinador")
    public ResponseEntity<RespostaRegister> register_treinador(@RequestBody String pl){
        Treinador u = new Treinador();
        JSONObject obj = new JSONObject(pl);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dn = df.parse(obj.getString("data"),new ParsePosition(0));

        u.setEmail(obj.getString("email"));u.setPassword(obj.getString("password"));
        u.setNome(obj.getString("nome"));u.setAltura(obj.getFloat("altura"));
        u.setGenero(obj.getBoolean("genero"));u.setPeso(obj.getFloat("peso"));
        u.setDataNascimento(dn);u.setUsername(obj.getString("username"));
        gestao_treinadores.registerTreinador(u);

        RespostaRegister ret = new RespostaRegister();

        if(!gestao_treinadores.registerTreinador(u)){
            ret.setSucess(false);
            return ResponseEntity.badRequest().body(ret);
        }

        ret.setSucess(true);
        return ResponseEntity.ok().body(ret);
    }

}
