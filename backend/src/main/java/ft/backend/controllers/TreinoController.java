package ft.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    gestao_verificacoes verify;

    @GetMapping(value = "/pesquisar")
    public ResponseEntity<List<Treino>> pesquisar(@RequestParam Optional<Integer> id,@RequestHeader String token){
     
        if(verify.verifyUser(token)  != null  || verify.verifyTreinador(token)  != null || verify.verifyAdmin(token)  != null){
            List<Treino> res = new ArrayList<>();

            if(id.isPresent()){
             res.add(gt.pesquisarTreino(id.get()));
            }
            else res = gt.pesquisarTreino();  


            return ResponseEntity.ok().body(res);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping(value = "/avaliacao")
    public ResponseEntity<RespostaOk> comentar(@RequestHeader String token, @RequestBody String s){

        if(verify.verifyUser(token) != null){

            JSONObject obj = new JSONObject(s);

            String username = obj.getString("username");
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
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<String> getTreinos(@RequestHeader String token){

        if( verify.verifyUser(token)  != null  || verify.verifyTreinador(token)  != null || verify.verifyAdmin(token)  != null){
            return ResponseEntity.ok().body(gt.listarTreinos().toString());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping(value = "/listarPagina")
    public ResponseEntity<String> getTreinos(@RequestHeader String token,@RequestParam int pag){

        if( verify.verifyUser(token)  != null  || verify.verifyTreinador(token)  != null || verify.verifyAdmin(token)  != null){
            return ResponseEntity.ok().body(gt.listarTreinos(pag).toString());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping(value = "/listarUtilizador")
    public ResponseEntity<String> getTreinos(@RequestHeader String token,@RequestParam String username){

        if(verify.verifyUser(token) != null){
            return ResponseEntity.ok().body(gt.listarTreinosByUsername(username).toString());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping(value="/favoritar")
    public ResponseEntity<String> favoritar(@RequestHeader String token,@RequestBody String body){
        String username = null;
        if((username = verify.verifyUser(token)) != null){
            JSONObject obj = new JSONObject(body);
            String cod_treino = obj.getString("treino");
            if(gt.favoritar(cod_treino,username)){
                return ResponseEntity.ok().body("");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping(value="/desfavoritar")
    public ResponseEntity<String> desfavoritar(@RequestHeader String token,@RequestBody String body){
        String username = null;
        if((username = verify.verifyUser(token)) != null){
            JSONObject obj = new JSONObject(body);
            String cod_treino = obj.getString("treino");
            if(gt.desfavoritar(cod_treino,username)){
                return ResponseEntity.ok().body("");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping(value = "/novoTreino")
    public ResponseEntity<String> novoTreino(@RequestHeader String token,@RequestBody String s){

        String username = null;
        boolean falg = false;
        if((username = verify.verifyUser(token)) == null){
            if((username = verify.verifyTreinador(token)) == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
            falg = true;
        }

        Treino treino = new Treino();
        JSONObject obj = new JSONObject(s);

        if(!falg){
            Utilizador u = gUtilizadores.getUserByUsername(username);
            if(u == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            treino.setCriador_u(u);
        }
        else{
            Treinador t = gTreinadores.getTreinadorByUsername(username);
            if(t == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            treino.setCriador_t(t);
        }

        treino.setDificuldade(obj.getString("dificuldade"));
        treino.setCodigo(Date_Utils.generateCode());
        treino.setNome(obj.getString("nome"));
        treino.setDescricao(obj.getString("descricao"));

        treino.setData_criacao(new Date());

        treino.setDuracao(obj.getFloat("duracao_treino"));

        Set<Categoria> cats = new HashSet<>();
        JSONArray arr = obj.getJSONArray("categorias");
        for(int i=0;i<arr.length();i++){
            Categoria c = new Categoria();
            c.setCategoria(arr.getString(i));
            cats.add(c);
        }
        treino.setCategorias(cats);

        List<Bloco> exs = new ArrayList<>();
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
    public ResponseEntity<String> getTreino(@RequestHeader String token,@RequestParam String codigo){

      if(verify.verifyUser(token) != null || verify.verifyTreinador(token) != null || verify.verifyAdmin(token) != null){

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

        if(t.getCriador_u()!=null){
            ret.put("criador", t.getCriador_u().getUsername());
            ret.put("criador_foto", "/api/assets/photo/" + t.getCriador_u().getFoto_perfil().getID());
        }else{
            if(t.getCriador_t()!=null){
                ret.put("criador", t.getCriador_t().getUsername());
                ret.put("criador_foto", "/api/assets/photo/" + t.getCriador_t().getFoto_perfil().getID());
            }
        }
        //falta a foto do gajo e o nome
        JSONArray exercicios = new JSONArray();
        for(Bloco c : t.getORM_Blocos_exercicios()){
            JSONObject ex_aux = new JSONObject();
            ex_aux.put("tipo",c.isTipo());
            ex_aux.put("nome", c.getExercicio().getNome());
            ex_aux.put("series", c.getSeries());
            ex_aux.put("repeticoes", c.getDuracao());
            ex_aux.put("descanso", c.getDescanso());
            ex_aux.put("descricao", c.getExercicio().getDescricao());
            ex_aux.put("duracao",c.getExercicio().getDuracao_media());
            ex_aux.put("material",c.getExercicio().getMaterial_necessario());
            ex_aux.put("id",c.getExercicio().getID());
            
            JSONArray fotos = new JSONArray();
            for(ConteudoMedia cm : c.getExercicio().getORM_ConteudoMedia()){
                if(!cm.getExtensao()){
                    fotos.put("/api/assets/photo/"+cm.getID());
                }
                else {
                    fotos.put("/api/assets/video/"+cm.getID());
                }
            }
            ex_aux.put("fotos",fotos);

            exercicios.put(ex_aux);
        }
        ret.put("exercicios", exercicios);
        JSONArray cm = new JSONArray();
        for(Avaliacao_Treino c : t.getORM_Avaliacoes_treino()){
            JSONObject ex_aux = new JSONObject();
            ex_aux.put("avaliacao",c.getClassificacao());
            ex_aux.put("comentario", c.getComentario());
            ex_aux.put("username", c.getUser().getUsername());
            ex_aux.put("foto_avaliador", "/api/assets/photo/" + c.getUser().getFoto_perfil().getID());
            cm.put(ex_aux);
        }
        ret.put("avaliacoes", cm);

        return ResponseEntity.ok().body(ret.toString());
     }
     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

    }

    @GetMapping(value = "/getCodigos")
    public ResponseEntity<String> getCodigos(@RequestHeader String token){

     if( verify.verifyUser(token)  != null  || verify.verifyTreinador(token)  != null || verify.verifyAdmin(token)  != null ){

        //verificar token aqui
        JSONArray codigos = gt.getCodigos();
        return ResponseEntity.ok().body(codigos.toString());
     }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
 
    }

    @GetMapping(value = "/getTreinoInfo")
    public ResponseEntity<String> getTreinoInfo(@RequestHeader String token,@RequestParam String codigo){

      if( verify.verifyUser(token)  != null  || verify.verifyTreinador(token)  != null || verify.verifyAdmin(token)  != null){
        return ResponseEntity.ok().body(gt.getTreinoInfo(codigo).toString());
      }

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping(value = "/getNomeTreino")
    public ResponseEntity<String> getNomeTreino(@RequestHeader String token,@RequestParam String codigo){

      if( verify.verifyUser(token)  != null  || verify.verifyTreinador(token)  != null || verify.verifyAdmin(token)  != null ){
        return ResponseEntity.ok().body(gt.getNomeTreino(codigo).toString());
      }

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @DeleteMapping(value="/deleteTreino")
    public ResponseEntity<String> deleteTreino(@RequestHeader String token,@RequestBody String cods){
        if(verify.verifyAdmin(token) != null){
            JSONArray arr = new JSONArray(cods);
            for(int i=0;i<arr.length();i++){
                String cod = arr.getString(i);
                gt.deleteTreino(cod);
            }
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

}
