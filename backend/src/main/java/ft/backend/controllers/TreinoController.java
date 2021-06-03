package ft.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import ft.backend.beans.*;
import ft.backend.responses.RespostaOk;
import ft.backend.utils.*;
import ft.backend.entities.*;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/treinos")
public class TreinoController {
    
    @Autowired 
    gestao_treinos gt;

    @Autowired
    gestao_treinadores gTreinadores;

    @Autowired
    gestao_utilizadores gUtilizadores;

    @Autowired
    gestao_categorias gCategorias;

    @Autowired
    gestao_exercicios gExercicios;

    @GetMapping(value = "/pesquisar")
    public ResponseEntity<List<Treino>> pesquisar(@RequestParam Optional<Integer> id){
        List<Treino> res = new ArrayList<>();

        if(id.isPresent()){
            res.add(gt.pesquisarTreino(id.get()));
        }
        else res = gt.pesquisarTreino();  

        return ResponseEntity.ok().body(res);
    }

    @PostMapping(value = "/comentar")
    public ResponseEntity<RespostaOk> comentar(@RequestParam int IdTreino, @RequestBody Avaliacao_Treino s){

        if(gt.comentar(IdTreino, s) == true){
            return ResponseEntity.ok().body(new RespostaOk());
        }

        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<String> getTreinos(){
        return ResponseEntity.ok().body(gt.listarTreinos().toString());
    }

    @PostMapping(value = "/novoTreino")
    public ResponseEntity<String> novoTreino(@RequestHeader String token,@RequestBody String s){
        JSONObject obj;
        try{
            obj = new JSONObject(Authorization.extractClaims(token));
            if(obj.getBoolean("treinador_utilizador")){
                String username = obj.getString("username");
                if(!gTreinadores.usernameExisteT(username)){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
                }
            }
            else{
                String username = obj.getString("username");
                if(!gUtilizadores.usernameExisteU(username)){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
                }
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }

        Treino treino = new Treino();
        obj = new JSONObject(s);

        if(obj.has("criador_utilizador")){
            Utilizador u = gUtilizadores.getUserByUsername(obj.getString("criador_utilizador"));
            if(u == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
            }
            treino.setCriador_u(u);
        }
        else{
            if(obj.has("criador_treinador")){
                Treinador t = gTreinadores.getTreinadorByUsername(obj.getString("criador_treinador"));
                if(t == null){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
                }
                treino.setCriador_t(t);
            }
        }

        treino.setDificuldade(obj.getString("dificuldade"));
        treino.setCodigo(Date_Utils.generateCode());
        treino.setNome(obj.getString("nome"));
        treino.setDescricao(obj.getString("descricao"));

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dn = df.parse(obj.getString("data_criacao"),new ParsePosition(0));
        treino.setData_criacao(dn);

        treino.setDuracao(obj.getFloat("duracao"));
        Set<Categoria> cats = new HashSet<>();
        JSONArray arr = obj.getJSONArray("categorias");
        for(int i=0;i<arr.length();i++){
            Categoria c = new Categoria();
            c.setCategoria(arr.getString(i));
            cats.add(c);
        }
        treino.setCategorias(cats);

        Set<Bloco> exs = new HashSet<>();
        arr = obj.getJSONArray("exercicios");
        for(int i=0;i<arr.length();i++){
            Bloco b = new Bloco();
            JSONObject aux = arr.getJSONObject(i);
            b.setExercicio(gExercicios.getExercicio(aux.getInt("exercicio_id")));
            b.setDescanso(aux.getFloat("descanso"));
            b.setDuracao(aux.getInt("duracao"));
            b.setSeries(aux.getInt("series"));
            exs.add(b);
        }
        treino.setORM_Blocos_exercicios(exs);
        gt.guardaTreino(treino);

        return ResponseEntity.ok().body("{\"codigo\":\""+treino.getCodigo()+"\"}");
    }

}
