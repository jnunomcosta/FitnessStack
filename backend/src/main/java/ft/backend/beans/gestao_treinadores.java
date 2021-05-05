package ft.backend.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ft.backend.entities.Treinador;
import ft.backend.repositories.TreinadorDAO;

@Service
public class gestao_treinadores {
    
    @Autowired
    TreinadorDAO tDao;

    public Treinador loginTreinador(String email, String password){
        return tDao.findTreinador_Email_Password(email, password);
    }

    public boolean registerTreinador(Treinador u){
        tDao.save(u);
        return true;
    }

}
