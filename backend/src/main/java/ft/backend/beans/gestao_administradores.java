package ft.backend.beans;

import ft.backend.entities.Administrador;
import ft.backend.repositories.AdministradorDAO;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
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

    public JSONObject getAdminInformation(String username){
        Administrador u = administradorDAO.findAdmin_Username(username);
        if(u != null){
            JSONObject ret = new JSONObject();
            ret.put("email", u.getEmail());
            ret.put("nome",u.getNome());
            ret.put("username", username);
            return ret;
        }
        else{
            return null;
        }
    }
    public JSONObject getSideBarAdminInformation(String username){
        Administrador u = administradorDAO.findAdmin_Username(username);
        if(u != null){
            JSONObject ret = new JSONObject();
            ret.put("nome",u.getNome());
            ret.put("username", username);
            return ret;
        }
        else{
            return null;
        }
    }
    
    public boolean usernameExisteU(String username){
        if(administradorDAO.findAdmin_Username(username) != null){
            return true;
        }
        return false;
    }


    public boolean mudarEmail(String username,String email){
        Administrador u = administradorDAO.findAdmin_Username(username);
        if(u != null){
            u.setEmail(email);
            administradorDAO.save(u);
            return true;
        }
        return false;
    }

    public boolean mudarUsername(String username,String username_novo){
        Administrador u = administradorDAO.findAdmin_Username(username);
        if(u != null){
            if(administradorDAO.findAdmin_Username(username_novo) == null){
                u.setUsername(username_novo);
                administradorDAO.save(u);
                return true;
            }  
        }
        return false;
    }

    public boolean mudarPassword(String username,String oldP, String newP){
        Administrador u = administradorDAO.findAdmin_Username(username);
        if(u != null){
           
                if(oldP.equals(u.getPassword())){

                    u.setPassword(newP);
                    administradorDAO.save(u);

                    return true;
                }
                
                
            
        }


        return false;
    }


    public JSONArray getAdmins(){

        JSONArray res = new JSONArray();
        
        List<Administrador> l = administradorDAO.findAll();
        for(Administrador e: l){
            JSONObject exe = new JSONObject();
            exe.put("nome", e.getNome());
            exe.put("username", e.getUsername());
            exe.put("email",e.getEmail());
            
            res.put(exe);
        }

        return res;
    }
}
