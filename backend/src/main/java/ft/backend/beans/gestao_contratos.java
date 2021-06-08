package ft.backend.beans;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ft.backend.entities.Contrato;
import ft.backend.entities.Treinador;
import ft.backend.entities.Utilizador;
import ft.backend.repositories.ContratoDAO;
import ft.backend.repositories.TreinadorDAO;
import ft.backend.repositories.UtilizadorDAO;

@Service
public class gestao_contratos {
    
    @Autowired
    ContratoDAO contratoDAO;
    @Autowired
    UtilizadorDAO utilizadorDAO;
    @Autowired
    TreinadorDAO treinadorDAO;


    public Contrato getContratoUser(String username){
        Contrato ret =null;
        Utilizador u = utilizadorDAO.findUtilizador_Username(username);
        if (u!=null){
            ret = contratoDAO.findContratoIdUser(u.getID());
           
        }

        return ret;
    }

    public List<Contrato> getContratoTreinador(String username){
        List<Contrato> ret =null;
        Treinador u = treinadorDAO.findTreinador_Username(username);
        if (u!=null){
            ret = contratoDAO.findContratoIdTreinador(u.getID());
           
        }
        
        return ret;
    }
}
