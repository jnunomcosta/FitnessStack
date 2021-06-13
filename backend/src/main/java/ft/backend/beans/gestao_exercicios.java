package ft.backend.beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

import ft.backend.utils.Date_Utils;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ft.backend.repositories.*;
import ft.backend.entities.*;

@Service
public class gestao_exercicios {
    
    @Autowired 
    ExercicioDAO eDao;
    @Autowired
    TreinadorDAO treinadorDao;
    @Autowired
    ExercicioDAO exercicioDAO;

    public void criaExercicio(Exercicio e, Treinador t, JSONArray media){

        e.setCriador_exercicio(t);
        List<ConteudoMedia> medias = new ArrayList<>();
        for(int i = 0;i < media.length();i++){
            JSONObject obj = media.getJSONObject(i);

            ConteudoMedia c = new ConteudoMedia();
            c.setID(Date_Utils.generateCode());
            c.setConteudo(DatatypeConverter.parseBase64Binary(obj.getString("conteudo")));
            c.setExtensao(obj.getBoolean("tipo"));
            medias.add(c);
        }
        e.setORM_ConteudoMedia(medias);
        eDao.save(e);
    }

    public Exercicio getExercicio(int id_exercicio){
        return exercicioDAO.getOne(id_exercicio);
    }

    public Exercicio getExercicio(String nome_exercicio){
        return exercicioDAO.findExercicioByNome(nome_exercicio);
    }

    public JSONArray getNomeExercicios(){
        return new JSONArray(exercicioDAO.listNomeExercicios());
        //return exercicioDAO.listNomeExercicios();
    }

    public JSONArray getExercicios(){

        JSONArray res = new JSONArray();
        
        List<Exercicio> l = eDao.findAll();
        for(Exercicio e: l){
            JSONObject exe = new JSONObject();
            exe.put("id",e.getID());
            exe.put("nome", e.getNome());
            exe.put("duracao",e.getDuracao_media());
            exe.put("material", e.getMaterial_necessario());
            exe.put("descricao", e.getDescricao());
            JSONArray imgs = new JSONArray();
            for(ConteudoMedia c : e.getORM_ConteudoMedia()){
                if(!c.getExtensao()){
                    imgs.put("/api/assets/photo/"+c.getID());
                }
                else{
                    imgs.put("/api/assets/video/"+c.getID());
                }
            }
            exe.put("conteudo_media", imgs);
            res.put(exe);
        }

        return res;
    }

    public boolean deleteExercicio(int id){
        Exercicio e = exercicioDAO.getOne(id);
        exercicioDAO.delete(e);
        return true;
    }

    public JSONArray getExercicios(String filtro){
        JSONArray res = new JSONArray();
        List<Exercicio> l = exercicioDAO.listExerciciosByFiltro(filtro);
        for(Exercicio e: l){
            JSONObject exe = new JSONObject();
            exe.put("id",e.getID());
            exe.put("nome", e.getNome());
            exe.put("duracao",e.getDuracao_media());
            exe.put("material", e.getMaterial_necessario());
            exe.put("descricao", e.getDescricao());
            JSONArray imgs = new JSONArray();
            for(ConteudoMedia c : e.getORM_ConteudoMedia()){
                if(!c.getExtensao()){
                    imgs.put("/api/assets/photo/"+c.getID());
                }
                else{
                    imgs.put("/api/assets/video/"+c.getID());
                }
            }
            exe.put("conteudo_media", imgs);
            res.put(exe);
        }

        return res;
    }

    public JSONArray getExercicios(int pag){
        JSONArray res = new JSONArray();
        List<Exercicio> l = exercicioDAO.listExerciciosByFiltro(pag,pag+10);
        for(Exercicio e: l){
            JSONObject exe = new JSONObject();
            exe.put("id",e.getID());
            exe.put("nome", e.getNome());
            exe.put("duracao",e.getDuracao_media());
            exe.put("material", e.getMaterial_necessario());
            exe.put("descricao", e.getDescricao());
            JSONArray imgs = new JSONArray();
            for(ConteudoMedia c : e.getORM_ConteudoMedia()){
                if(!c.getExtensao()){
                    imgs.put("/api/assets/photo/"+c.getID());
                }
                else{
                    imgs.put("/api/assets/video/"+c.getID());
                }
            }
            exe.put("conteudo_media", imgs);
            res.put(exe);
        }

        return res;
    }

    public int getNumExercicios(){
        return exercicioDAO.num_exercicios();
    }

}
