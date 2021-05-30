package ft.backend.controllers.recursos;

import java.util.Base64;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ft.backend.repositories.*;
import ft.backend.entities.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/rest/utilizadores")
public class RecursoUtilizador {
    
    @Autowired
    UtilizadorDAO rep_users;

    @GetMapping(value = "/todos")
    public List<Utilizador> getAll(){
        
        return rep_users.findAll();
    }

    @GetMapping(value = "/user")
    public List<Utilizador> getUser(@RequestParam String nome){
        return rep_users.findByJavardice(nome);
    }

    @PostMapping(value = "/carregar")
    public List<Utilizador> persiste(@RequestBody final Utilizador ut){

        rep_users.save(ut);
        return rep_users.findAll();
    } 

    @GetMapping(value = "/getImagem")
    public String getImagem(@RequestParam String username){
        Utilizador u = rep_users.findUtilizador_Username(username);
        if(u!=null){
            return "{\"imagem\":\""+ Base64.getEncoder().encodeToString(u.getFoto_perfil().getConteudo()) + "\"}";
        }
        return "{\"imagem\":null}";
    }

    @DeleteMapping(value = "/user")
    public String delet(@RequestParam String username){
        Utilizador u = rep_users.findUtilizador_Username(username);
        rep_users.delete(u);
        return "{\"great\":success}";
    }

    @GetMapping(value = "/getUserInfo")
    public String getInfoUser(@RequestParam String username){
        Utilizador u = rep_users.findUtilizador_Username(username);
        JSONObject obj = new JSONObject();
        obj.put("email", u.getEmail());
        obj.put("datanascimento", u.getDataNascimento());
        obj.put("nome",u.getNome());
        obj.put("username", username);
        obj.put("peso", u.getPeso());
        obj.put("m_gorda", 20);
        obj.put("m_muscular", 78);
        obj.put("altura", u.getAltura());
        obj.put("genero", u.getGenero() ? "Masculino" : "Feminino");
        obj.put("foto_perfil", Base64.getEncoder().encodeToString(u.getFoto_perfil().getConteudo()));
        //ver se a imagem e null
    
        return obj.toString();
    }

}
