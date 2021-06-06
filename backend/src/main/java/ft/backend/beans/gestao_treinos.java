package ft.backend.beans;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ft.backend.repositories.*;
import ft.backend.entities.*;

@Service
public class gestao_treinos {
    
    @Autowired
    TreinoDAO tDao;
    @Autowired 
    ExercicioDAO eDao;
    @Autowired
    TreinadorDAO treinadorDao;

    public Treino getTreino(String codigo) {return tDao.findbyCodigo(codigo);}

    public Treino pesquisarTreino(int id){
        return tDao.findbyId(id);
    }

    public List<Treino> pesquisarTreino(){
        return tDao.findAllTreinos();
    }

    public boolean comentar(int id, Avaliacao_Treino at){
        boolean b = false;
        Treino t = tDao.findbyId(id);
        if(t!=null){
            t.getORM_Avaliacoes_treino().add(at);
            tDao.save(t);
            b = true;
        }
        return b;
    }

    public JSONArray listarTreinos(){
        JSONArray ret = new JSONArray();

        List<Treino> treinos = tDao.findAll();
        for(Treino t : treinos){
            JSONObject exe = new JSONObject();
            exe.put("nome", t.getNome());
            exe.put("duracao",t.getDuracao()/60);
            JSONArray a = new JSONArray();
            for(Categoria c : t.getCategorias()){
                a.put(c.getCategoria());
            }
            exe.put("categoria", a);
            exe.put("dificuldade", t.getDificuldade());
            exe.put("data", t.getData_criacao());
            exe.put("codigo", t.getCodigo());
            ret.put(exe);
        }

        return ret;
    }

    public boolean guardaTreino(Treino t){
        Treino tr = tDao.save(t);
        if(tr==null){
            return false;
        }
        return true;
    }

    public JSONArray getCodigos(){
        List<String> a = tDao.getCodigos();
        JSONArray arr = new JSONArray();
        for(int i = 0;i<a.size();i++){
            arr.put(a.get(i));
        }
        return arr;
    }

    public JSONObject getTreinoInfo(String codigo){
        Treino t = tDao.findbyCodigo(codigo);
        JSONObject obj = new JSONObject();
        obj.put("nome",t.getNome());
        obj.put("duracao",t.getDuracao());
        obj.put("dificuldade",t.getDificuldade());
        obj.put("codigo",t.getCodigo());
        return obj;
    }

    public JSONObject getNomeTreino(String codigo){
        JSONObject ret = new JSONObject();
        ret.put("nome",codigo + " - " + tDao.getTreinoNome(codigo));
        return ret;
    }

}
