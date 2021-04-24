package ft.backend.controllers;

import java.util.List;

import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ft.backend.entities.*;
import ft.backend.repositories.*;

@RestController
@RequestMapping(value = "/rest/exercicios")
public class RecursoExercicio {
    
    @Autowired
    ExercicioDAO rep_exercicios;
    @Autowired
    TreinadorDAO rep_treinadores;

    @GetMapping(value = "/todos")
    public List<Exercicio> getAll(){
        return rep_exercicios.findAll();
    }

    @PostMapping(value = "/carregar")
    public String persiste(@RequestBody String t){
        JSONObject obj = new JSONObject(t);

        int id_treinador = obj.getInt("criador_exercicio");
        Treinador ttt = rep_treinadores.getOne(id_treinador);

        Exercicio e = new Exercicio();
        e.setCriador_exercicio(ttt);e.setNome(obj.getString("nome"));e.setDescricao("descricao");
        e.setDuracao_media(obj.getFloat("duracao_media"));e.setMaterial_necessario(obj.getString("material_necessario"));
        
        rep_exercicios.save(e);
        return "{\"great\":\"Success\"}";
    }
}
