package ft.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ft.backend.beans.gestao_treinos;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/exercicio")
public class ExercicioController {
    
    @Autowired
    gestao_treinos gt;

    @PostMapping(value = "/novo")
    public ResponseEntity<String> novoExercicio(@RequestBody String t){
        
        gt.criaExercicio(t);

        return ResponseEntity.ok().body("{\"great\":\"Success\"}");
    } 

}
