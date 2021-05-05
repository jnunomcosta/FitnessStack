package ft.backend.beans;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ft.backend.entities.Utilizador;
import ft.backend.repositories.UtilizadorDAO;

@Service
public class gestao_utilizadores {
    
    @Autowired
    UtilizadorDAO uDao; 

    public Utilizador loginUser(String email, String password){
        return uDao.findUtilizador_Email_Password(email, password);
    }

    public boolean registerUser(Utilizador u){
        uDao.save(u);
        return true;
    }

}
