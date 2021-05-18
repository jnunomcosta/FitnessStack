package ft.backend.controllers;

import ft.backend.entities.Exercicio;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ft.backend.beans.gestao_treinos;
import ft.backend.beans.gestao_utilizadores;
import ft.backend.responses.RespostaOk;
import ft.backend.utils.Authorization;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/exercicio")
public class ExercicioController {
    
    @Autowired
    gestao_treinos gt;

    @Autowired 
    gestao_utilizadores gu;

    @PostMapping(value = "/novo")
    public ResponseEntity<RespostaOk> novoExercicio(@RequestHeader String token, @RequestBody String t){
        JSONObject obj;
        RespostaOk ret = new RespostaOk();
        try{
            obj = new JSONObject(Authorization.extractClaims(token));
            if(obj.getBoolean("treinador_utilizador")){
                String username = obj.getString("username");
                if(!gu.usernameExisteT(username)){
                    ret.setEstado("UNAUTHORIZED");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ret);
                }
            }
            else{
                ret.setEstado("UNAUTHORIZED");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ret);
            }
        }
        catch (Exception e){
            ret.setEstado("UNAUTHORIZED");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ret);
        }
        obj = new JSONObject(t);

        int id_treinador = obj.getInt("criador_exercicio");
        Exercicio e = new Exercicio();
        e.setNome(obj.getString("nome"));
        e.setDescricao("descricao");
        e.setDuracao_media(obj.getFloat("duracao_media"));
        e.setMaterial_necessario(obj.getString("material_necessario"));

        gt.criaExercicio(e,id_treinador,obj.getJSONArray("conteudo_media"));
        
        return ResponseEntity.ok().body(ret);
    } 

}
