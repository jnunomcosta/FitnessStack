package ft.backend.controllers;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ft.backend.repositories.*;
import ft.backend.entities.*;

@RestController
@RequestMapping(value = "/rest/blocos_exercicios")
public class RecursoBloco {
    
    @Autowired
    BlocoDAO bDao;
    @Autowired
    TreinoDAO tDao;
    @Autowired
    ExercicioDAO eDao;

    @GetMapping(value = "/todos")
    public List<Bloco> getAll(){
        return bDao.findAll();
    }

    @PostMapping(value = "/carregar")
    public String persiste(@RequestBody String t){

        JSONObject obj = new JSONObject(t);

        Treino tre = tDao.getOne(obj.getInt("treino"));
        Exercicio e = eDao.getOne(obj.getInt("exercicio"));

        Bloco b = new Bloco();
        b.setExercicio(e);b.setDuracao(obj.getInt("duracao"));b.setDescanso(obj.getFloat("descanso"));
        b.setSeries(obj.getInt("series"));
        
        tre.getORM_Blocos_exercicios().add(b);
        tDao.save(tre);

        return "{\"great\":\"Success\"}";
    }

}
