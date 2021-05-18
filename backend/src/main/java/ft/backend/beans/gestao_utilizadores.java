package ft.backend.beans;

import ft.backend.entities.Treinador;
import ft.backend.repositories.TreinadorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ft.backend.entities.Utilizador;
import ft.backend.repositories.UtilizadorDAO;

@Service
public class gestao_utilizadores {
    
    @Autowired
    UtilizadorDAO uDao;
    @Autowired
    TreinadorDAO tDao;

    public Utilizador loginUser(String username,String password){
        Utilizador u = uDao.findUtilizador_Username(username);
        //verificar se a password e correta
        return u;
    }

    public boolean registerUser(Utilizador u){
        if(uDao.findUtilizador_Username(u.getUsername()) != null || uDao.findUtilizador_Email(u.getEmail()) != null){
            return false;
        }
        uDao.save(u);
        return true;
    }

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

    public boolean usernameExisteU(String username){
        if(uDao.findUtilizador_Username(username) != null){
            return true;
        }
        return false;
    }

    public boolean usernameExisteT(String username){
        if(tDao.findTreinador_Username(username) != null){
            return true;
        }
        return false;
    }

}
