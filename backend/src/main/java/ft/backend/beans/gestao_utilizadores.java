package ft.backend.beans;

import ft.backend.entities.Treinador;
import ft.backend.repositories.TreinadorDAO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ft.backend.entities.Utilizador;
import ft.backend.repositories.UtilizadorDAO;

@Service
public class gestao_utilizadores {
    
    @Autowired
    UtilizadorDAO uDao;

    public boolean loginUser(String username,String password){
        Utilizador u = uDao.findUtilizador_Username(username);
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

    public JSONObject getUserInformation(String username){
        Utilizador u = uDao.findUtilizador_Username(username);
        if(u != null){
            JSONObject ret = new JSONObject();
            ret.put("email", u.getEmail());
            ret.put("datanascimento", u.getDataNascimento());
            ret.put("nome",u.getNome());
            ret.put("username", username);
            ret.put("peso", u.getPeso());
            ret.put("m_gorda", 20);
            ret.put("m_muscular", 78);
            ret.put("altura", u.getAltura());
            ret.put("genero", u.getGenero() ? "Masculino" : "Feminino");
            ret.put("foto_perfil","/api/assets/photo/"+u.getFoto_perfil().getID());
            return ret;
        }
        else{
            return null;
        }
    }

    public JSONObject getSideBarUserInformation(String username){
        Utilizador u = uDao.findUtilizador_Username(username);
        if(u != null){
            JSONObject ret = new JSONObject();
            ret.put("nome",u.getNome());
            ret.put("username", username);
            ret.put("foto_perfil","/api/assets/photo/"+u.getFoto_perfil().getID());
            return ret;
        }
        else{
            return null;
        }
    }

    public boolean registerUser(Utilizador u){
        if(uDao.findUtilizador_Username(u.getUsername()) != null || uDao.findUtilizador_Email(u.getEmail()) != null){
            return false;
        }
        uDao.save(u);
        return true;
    }

    public boolean usernameExisteU(String username){
        if(uDao.findUtilizador_Username(username) != null){
            return true;
        }
        return false;
    }

    public boolean mudarEmail(String username,String email){
        Utilizador u = uDao.findUtilizador_Username(username);
        if(u != null){
            u.setEmail(email);
            uDao.save(u);
            return true;
        }
        return false;
    }

    public boolean mudarUsername(String username,String username_novo){
        Utilizador u = uDao.findUtilizador_Username(username);
        if(u != null){
            if(uDao.findUtilizador_Username(username_novo) == null){
                u.setUsername(username_novo);
                uDao.save(u);
                return true;
            }  
        }
        return false;
    }

    public Utilizador getUserByUsername(String username){
        return uDao.findUtilizador_Username(username); 
    }

}
