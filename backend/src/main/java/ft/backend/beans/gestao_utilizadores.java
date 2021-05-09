package ft.backend.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ft.backend.entities.Utilizador;
import ft.backend.repositories.UtilizadorDAO;

@Service
public class gestao_utilizadores {
    
    @Autowired
    UtilizadorDAO uDao; 

    public Utilizador loginUser(String username,String password){
        Utilizador u = uDao.findUtilizador_Username(username);
        //verificar se a password e correta
        return u;
    }

    public boolean registerUser(Utilizador u){
        uDao.save(u);
        return true;
    }

}
