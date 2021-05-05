package ft.backend.beans;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import ft.backend.repositories.UtilizadorDAO;

@Configurable
public class gestao_utilizadores {
/*
    @Autowired
    static UtilizadorDAO uDao; 

    @Autowired
    private UtilizadorDAO utilizadorDAO;
 
    @PostConstruct
    private void init() {
       uDao = this.utilizadorDAO;
    }

    @Bean
    public static String loginUser(String email, String password){

        //decrypt password
        uDao.findUtilizador_Email_Password(email, password);
        
        //if(u==null){
        //    return "Mau dia";
        //}
        
        return "Bum dia";
    }*/

}
