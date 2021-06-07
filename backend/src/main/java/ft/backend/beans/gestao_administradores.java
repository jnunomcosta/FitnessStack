package ft.backend.beans;

import ft.backend.entities.Administrador;
import ft.backend.repositories.AdministradorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class gestao_administradores {

    @Autowired
    AdministradorDAO administradorDAO;

    public boolean loginAdministrador(String username,String password){
        Administrador u = administradorDAO.findAdmin_Username(username);
        if(u!=null){
            if(u.getPassword().equals(password)){
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
}
