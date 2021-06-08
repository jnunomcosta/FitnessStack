package ft.backend.controllers;

import ft.backend.entities.Exercicio;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ft.backend.beans.gestao_exercicios;
import ft.backend.beans.gestao_treinadores;
import ft.backend.beans.gestao_verificacoes;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/exercicio")
public class ExercicioController {
    
    @Autowired
    gestao_exercicios ge;

    @Autowired 
    gestao_treinadores gu;

    @Autowired
    gestao_verificacoes verify;

    @PostMapping(value = "/novo")
    public ResponseEntity<String> novoExercicio(@RequestHeader String token, @RequestBody String t){
        
        String username = verify.verifyTreinador(token);

        if(username!=null){

            JSONObject obj = new JSONObject(t);

            Exercicio e = new Exercicio();
            e.setNome(obj.getString("nome"));
            e.setDescricao("descricao");
            e.setDuracao_media(obj.getFloat("duracao_media"));
            e.setMaterial_necessario(obj.getString("material_necessario"));

            ge.criaExercicio(e,gu.getTreinadorByUsername(username),obj.getJSONArray("conteudo_media"));
        
            return ResponseEntity.ok().body("");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping(value = "/getNomesExercicios")
    public ResponseEntity<String> getNomesExercicios(@RequestHeader String token){
        if(verify.verifyTreinador(token) != null || verify.verifyUser(token) != null  || verify.verifyAdmin(token)!=null) {
            return ResponseEntity.ok().body(ge.getNomeExercicios().toString());
        }       
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<String> getExercicios(@RequestHeader String token){
        if(verify.verifyTreinador(token) != null || verify.verifyUser(token) != null || verify.verifyAdmin(token)!=null) {
            return ResponseEntity.ok().body(ge.getExercicios().toString());
         } 
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

}
