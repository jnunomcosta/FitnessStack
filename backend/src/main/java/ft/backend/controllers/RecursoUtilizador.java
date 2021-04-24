package ft.backend.controllers;

import java.util.List;

import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ft.backend.repositories.*;
import ft.backend.entities.*;

@RestController
@RequestMapping(value = "/rest/utilizadores")
public class RecursoUtilizador {
    
    @Autowired
    RepositorioUtilizadores rep_users;

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

}
