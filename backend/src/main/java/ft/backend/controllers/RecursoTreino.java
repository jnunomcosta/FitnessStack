package ft.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ft.backend.repositories.*;
import ft.backend.entities.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/rest/treinos")
public class RecursoTreino {
    
    @Autowired
    TreinoDAO tDao;

    @GetMapping(value = "/todos")
    public List<Treino> getAll(){
        return tDao.findAll();
    }

    @PostMapping(value = "/carregar")
    public String persiste(@RequestBody Treino t){

        tDao.save(t);

        return "{\"great\":\"Success\"}";
    }

}
