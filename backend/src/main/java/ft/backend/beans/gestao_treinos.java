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

}
