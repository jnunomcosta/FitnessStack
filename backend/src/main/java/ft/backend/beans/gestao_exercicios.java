package ft.backend.beans;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

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

    public void criaExercicio(Exercicio e, int id_treinador, JSONArray media){
        Treinador ttt = treinadorDao.getOne(id_treinador);
        e.setCriador_exercicio(ttt);

        Set<ConteudoMedia> medias = new HashSet<>();
        for(int i = 0;i < media.length();i++){
            ConteudoMedia c = new ConteudoMedia();
            c.setConteudo(DatatypeConverter.parseBase64Binary(media.getString(i)));
            c.setExtensao(true);//isto e imagem, falta ver se e video
            medias.add(c);
        }
        e.setORM_ConteudoMedia(medias);
        e.setCriador_exercicio(ttt);
        eDao.save(e);
    }

    public Exercicio getExercicio(int id_exercicio){
        return exercicioDAO.getOne(id_exercicio);
    }

    public Exercicio getExercicio(String nome_exercicio){
        return exercicioDAO.findExercicioByNome(nome_exercicio);
    }

    public JSONArray getExercicios(){

        JSONArray res = new JSONArray();
        
        List<Exercicio> l = eDao.findAll();
        for(Exercicio e: l){
            JSONObject exe = new JSONObject();
            exe.put("nome", e.getNome());
            exe.put("duracao",e.getDuracao_media());
            exe.put("material", e.getMaterial_necessario());
            exe.put("descricao", e.getDescricao());
            JSONArray imgs = new JSONArray();
            for(ConteudoMedia c : e.getORM_ConteudoMedia()){
                if(c.getExtensao()){
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

}
