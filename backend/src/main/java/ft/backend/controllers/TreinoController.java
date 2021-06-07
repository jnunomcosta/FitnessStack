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

    @PostMapping(value = "/avaliacao")
    public ResponseEntity<RespostaOk> comentar(@RequestHeader String token, @RequestBody String s){
        JSONObject obj;
        String username = null;
        try{
            obj = new JSONObject(Authorization.extractClaims(token));
            if(!obj.getBoolean("treinador_utilizador")){
                username = obj.getString("username");
                if(!gUtilizadores.usernameExisteU(username)){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
                }
            }
            else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        obj = new JSONObject(s);

        String treino = obj.getString("cod_treino");
        float aval = obj.getFloat("avaliacao");
        String comentario = obj.getString("comentario");

        Avaliacao_Treino at = new Avaliacao_Treino();
        at.setUser(gUtilizadores.getUserByUsername(username));
        at.setClassificacao(aval);
        at.setComentario(comentario);

        if(gt.avaliacao(treino, at)){
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
        boolean flag = true;
        String username;
        try{
            obj = new JSONObject(Authorization.extractClaims(token));
            if(obj.getBoolean("treinador_utilizador")){
                username = obj.getString("username");
                if(!gTreinadores.usernameExisteT(username)){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
                }
            }
            else{
                flag = false;
                username = obj.getString("username");
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

        if(!flag){
            Utilizador u = gUtilizadores.getUserByUsername(username);
            if(u == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
            }
            treino.setCriador_u(u);
        }
        else{
            Treinador t = gTreinadores.getTreinadorByUsername(username);
            if(t == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
            }
            treino.setCriador_t(t);
        }

        /* if(obj.has("criador_utilizador")){
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
        } */

        treino.setDificuldade(obj.getString("dificuldade"));
        treino.setCodigo(Date_Utils.generateCode());
        treino.setNome(obj.getString("nome"));
        treino.setDescricao(obj.getString("descricao"));

        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //Date dn = df.parse(obj.getString("data_criacao"),new ParsePosition(0));
        treino.setData_criacao(new Date());

        //treino.setDuracao(obj.getFloat("duracao"));
        treino.setDuracao(10000);

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
            b.setExercicio(gExercicios.getExercicio(aux.getString("nome_exercicio")));
            b.setDescanso(aux.getFloat("descanso"));
            b.setDuracao(aux.getInt("duracao"));
            b.setTipo(aux.getBoolean("tipo"));
            b.setSeries(aux.getInt("series"));
            exs.add(b);
        }
        treino.setORM_Blocos_exercicios(exs);
        gt.guardaTreino(treino);

        return ResponseEntity.ok().body("{\"codigo\":\""+treino.getCodigo()+"\"}");
    }

    @GetMapping(value="/getTreino")
    public ResponseEntity<String> getTreino(/*@RequestHeader String token,*/@RequestParam String codigo){
        Treino t = gt.getTreino(codigo);
        JSONObject ret = new JSONObject();
        ret.put("nome", t.getNome());
        ret.put("descricao", t.getDescricao());
        JSONArray categoria = new JSONArray();
        for(Categoria c : t.getCategorias()){
            categoria.put(c.getCategoria());
        }
        ret.put("categorias", categoria);
        ret.put("codigo", codigo);
        ret.put("data", t.getData_criacao());
        ret.put("dificuldade", t.getDificuldade());
        ret.put("duracao", t.getDuracao());
        //falta a foto do gajo e o nome
        JSONArray exercicios = new JSONArray();
        for(Bloco c : t.getORM_Blocos_exercicios()){
            JSONObject ex_aux = new JSONObject();
            ex_aux.put("nome", c.getExercicio().getNome());
            ex_aux.put("series", c.getSeries());
            ex_aux.put("repeticoes", c.getDuracao());
            ex_aux.put("descanso", c.getDescanso());
            exercicios.put(ex_aux);
        }
        ret.put("exercicios", exercicios);
        JSONArray cm = new JSONArray();
        for(Avaliacao_Treino c : t.getORM_Avaliacoes_treino()){
            JSONObject ex_aux = new JSONObject();
            ex_aux.put("avaliacao",c.getClassificacao());
            ex_aux.put("comentario", c.getComentario());
            ex_aux.put("username", c.getUser().getUsername());
            cm.put(ex_aux);
        }
        ret.put("avaliacoes", cm);

        return ResponseEntity.ok().body(ret.toString());

    }

    @GetMapping(value = "/getCodigos")
    public ResponseEntity<String> getCodigos(/*@RequestHeader String token,*/){

        //verificar token aqui
        JSONArray codigos = gt.getCodigos();
        return ResponseEntity.ok().body(codigos.toString());
    }

    @GetMapping(value = "/getTreinoInfo")
    public ResponseEntity<String> getTreinoInfo(/*@RequestHeader String token,*/@RequestParam String codigo){

        //verificar token aqui
        //JSONArray codigos = gt.getCodigos();
        return ResponseEntity.ok().body(gt.getTreinoInfo(codigo).toString());
    }

    @GetMapping(value = "/getNomeTreino")
    public ResponseEntity<String> getNomeTreino(/*@RequestHeader String token,*/@RequestParam String codigo){
        return ResponseEntity.ok().body(gt.getNomeTreino(codigo).toString());
    }

}
