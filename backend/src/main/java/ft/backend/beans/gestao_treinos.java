package ft.backend.beans;

import java.util.*;

import javax.xml.bind.DatatypeConverter;
import org.json.*;
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
    
    public void criaExercicio(String exercicio){
        JSONObject obj = new JSONObject(exercicio);

        int id_treinador = obj.getInt("criador_exercicio");
        Treinador ttt = treinadorDao.getOne(id_treinador);

        Exercicio e = new Exercicio();
        e.setCriador_exercicio(ttt);e.setNome(obj.getString("nome"));e.setDescricao("descricao");
        e.setDuracao_media(obj.getFloat("duracao_media"));e.setMaterial_necessario(obj.getString("material_necessario"));
        
        Set<ConteudoMedia> medias = new HashSet<>();
        JSONArray media = obj.getJSONArray("conteudo_media");
        for(int i = 0;i < media.length();i++){
            ConteudoMedia c = new ConteudoMedia();
            c.setConteudo(DatatypeConverter.parseBase64Binary(media.getString(0)));
            medias.add(c);
        }
        e.setORM_ConteudoMedia(medias);
        e.setCriador_exercicio(ttt);
        eDao.save(e);
    }

    public Treino pesquisarTreino(int id){

        return  tDao.findbyId(id);
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

}
