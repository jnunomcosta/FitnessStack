package ft.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ft.backend.repositories.*;
import ft.backend.entities.*;

@RestController
@RequestMapping(value = "/rest/treinadores")
public class RecursoTreinador {
    
    @Autowired
    RepositorioTreinadores rep_treinadores;

    @GetMapping(value = "/todos")
    public List<Treinador> getAll(){
        return rep_treinadores.findAll();
    }

    @PostMapping(value = "/carregar")
    public String persiste(@RequestBody Treinador t){
        rep_treinadores.save(t);
        return "{\"great\":\"Success\"}";
    }
    
}
