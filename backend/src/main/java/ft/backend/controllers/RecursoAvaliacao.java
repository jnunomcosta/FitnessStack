package ft.backend.controllers;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ft.backend.repositories.*;
import io.jsonwebtoken.*;
import ft.backend.entities.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/rest/avaliacoes")
public class RecursoAvaliacao {
    
    @Autowired
    Avaliacao_TreinadorDAO rep_eval;
    @Autowired
    Avaliacao_TreinoDAO rep_eval_treino;
    @Autowired
    TreinadorDAO rep_trainers;
    @Autowired
    TreinoDAO rep_treino;

    @GetMapping(value = "/treinadores/todos")
    public List<Avaliacao_Treinador> getAll_aval_treinadores(){
        
        return rep_eval.findAll();
    }

    @GetMapping(value = "/treinos/todos")
    public List<Avaliacao_Treino> getAll_aval_treinos(){
        return rep_eval_treino.findAll();
    }

    @PostMapping(value = "/treinadores/carregar")
    public String persiste(@RequestBody String t){
        JSONObject obj = new JSONObject(t);
        
        Treinador tre = rep_trainers.getOne(obj.getInt("treinador"));
        Avaliacao_Treinador a = new Avaliacao_Treinador();
        a.setClassificacao(obj.getFloat("classificacao"));a.setComentario(obj.getString("comentario"));
        tre.getORM_Avaliacoes_t().add(a);
        rep_trainers.save(tre);

        return "{\"great\":\"Success\"}";
    }

    @PostMapping(value = "/treinos/carregar")
    public String persiste_treinos(@RequestBody String t){
        JSONObject obj = new JSONObject(t);
        
        Treino tre = rep_treino.getOne(obj.getInt("treino"));
        Avaliacao_Treino a = new Avaliacao_Treino();
        a.setClassificacao(obj.getFloat("classificacao"));a.setComentario(obj.getString("comentario"));
        tre.getORM_Avaliacoes_treino().add(a);
        rep_treino.save(tre);

        return "{\"great\":\"Success\"}";
    }

}
