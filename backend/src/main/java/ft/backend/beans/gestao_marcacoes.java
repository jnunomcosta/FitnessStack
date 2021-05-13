package ft.backend.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ft.backend.repositories.*;
import ft.backend.responses.RespostaAgenda;
import ft.backend.entities.*;

@Service
public class gestao_marcacoes {
    
    @Autowired
    UtilizadorDAO uDao;
    @Autowired
    MarcacaoDAO mDao;
    @Autowired
    TreinoDAO tDAO;

    public List<RespostaAgenda> getAgendaUtilizador(int id){
        Utilizador u = uDao.getOne(id);
        Set<Marcacao> arr = u.getORM_Agenda();
        List<RespostaAgenda> ret = new ArrayList<>();
        for(Marcacao m : arr){
            RespostaAgenda ra = new RespostaAgenda();
            ra.setId_treino(m.getTreino().getID());
            ra.setData(m.getData());
            ra.setHora(m.getHora());
            ret.add(ra);
        }
        return ret;
    }

    public boolean novaMarcacao(Date m,int id_user,int id_treino){
        Utilizador u = uDao.getOne(id_user);
        Treino t = tDAO.getOne(id_treino);
        Marcacao mar = new Marcacao();
        mar.setData(m);
        mar.setHora(m);
        mar.setTreino(t);
        u.getORM_Agenda().add(mar);
        uDao.save(u);
        return true;
    }

}
