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

    public void criaExercicio(Exercicio e, int id_treinador, JSONArray media){
        Treinador ttt = treinadorDao.getOne(id_treinador);
        e.setCriador_exercicio(ttt);

        Set<ConteudoMedia> medias = new HashSet<>();
        for(int i = 0;i < media.length();i++){
            ConteudoMedia c = new ConteudoMedia();
            c.setConteudo(DatatypeConverter.parseBase64Binary(media.getString(i)));
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
