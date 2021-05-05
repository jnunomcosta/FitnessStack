package ft.backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import ft.backend.repositories.*;
import ft.backend.responses.RespostaOk;
import ft.backend.entities.*;
import ft.backend.beans.gestao_treinos;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/rest/treinos")
public class RecursoTreino {
    
    @Autowired
    TreinoDAO tDao;
    @Autowired 
    gestao_treinos gt;

    @GetMapping(value = "/todos")
    public List<Treino> getAll(){
        return tDao.findAll();
    }

    @PostMapping(value = "/carregar")
    public String persiste(@RequestBody Treino t){

        tDao.save(t);

        return "{\"great\":\"Success\"}";
    }

    @GetMapping(value = "/pesquisar")
    public ResponseEntity<List<Treino>> pesquisar(@RequestParam Optional<Integer> id){
        List<Treino> res= new ArrayList<>();

        if(id.isPresent()){
            res.add(gt.pesquisarTreino(id.get()));
            
        }
        else res=gt.pesquisarTreino();  

        return ResponseEntity.ok().body(res);
    }

    @PostMapping(value = "/comentar")
    public ResponseEntity<RespostaOk> comentar(@RequestParam int IdTreino, @RequestBody Avaliacao_Treino s){
    
        if(gt.comentar(IdTreino, s) == true){
            return ResponseEntity.ok().body(new RespostaOk());
        }
        return ResponseEntity.badRequest().body(null);
    }





}
