package ft.backend.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ft.backend.entities.Treinador;
import ft.backend.repositories.TreinadorDAO;

@Service
public class gestao_treinadores {
    
    @Autowired
    TreinadorDAO tDao;
    
    public Treinador loginTreinador(String username, String password){
        Treinador t = tDao.findTreinador_Username(username);
        //Verificar a password aqui
        return t;
    }

    public boolean registerTreinador(Treinador u){
        if(tDao.findTreinador_Username(u.getUsername())!=null || tDao.findTreinador_Email(u.getEmail()) != null){
            return false;
        }
        tDao.save(u);
        return true;
    }

    public boolean usernameExisteT(String username){
        if(tDao.findTreinador_Username(username) != null){
            return true;
        }
        return false;
    }
}
