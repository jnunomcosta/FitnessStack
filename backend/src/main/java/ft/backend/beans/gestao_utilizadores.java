package ft.backend.beans;

import ft.backend.entities.InformacaoFisica;
import ft.backend.entities.Treinador;
import ft.backend.entities.Treino;
import ft.backend.repositories.TreinadorDAO;

import java.util.Base64;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.json.JSONArray;
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
            ret.put("m_gorda", u.getMassaGorda());
            ret.put("m_muscular", u.getMassaMuscular());
            ret.put("altura", u.getAltura());
            ret.put("genero", u.getGenero() ? "Masculino" : "Feminino");
            ret.put("foto_perfil","/api/assets/photo/"+u.getFoto_perfil().getID());

            return ret;
        }
        else{
            return null;
        }
    }

    public JSONArray getUserFisicalHistory(String username){
        Utilizador u = uDao.findUtilizador_Username(username);
        if(u != null) {
            JSONArray arr = new JSONArray();
            for(InformacaoFisica i : u.getInformacao_fisica()){
                JSONObject o = new JSONObject();
                o.put("peso",i.getPeso());
                o.put("m_gorda",i.getM_gorda());
                o.put("m_muscular",i.getM_muscular());
                o.put("data",i.getData());
                arr.put(o);
            }
            return arr;
        }
        return null;
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

    public void novaInfoFisica(String username,InformacaoFisica i){
        Utilizador u = uDao.findUtilizador_Username(username);
        if(u != null){
            u.getInformacao_fisica().add(i);
            u.setMassaGorda(i.getM_gorda());
            u.setMassaMuscular(i.getM_muscular());
            u.setPeso(i.getPeso());
            uDao.save(u);
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

    public boolean mudarPassword(String username,String oldP, String newP){
        Utilizador u = uDao.findUtilizador_Username(username);
        if(u != null){
            if(oldP.equals(u.getPassword())){
                u.setPassword(newP);
                uDao.save(u);
                return true;
            }
        }
        return false;
    }

    public boolean mudarImagem(String username,String imagem){
        Utilizador u = uDao.findUtilizador_Username(username);
        if(u!=null){
            u.getFoto_perfil().setConteudo(Base64.getDecoder().decode(imagem));
            uDao.save(u);
            return true;
        }
        return false;
    }

    public boolean mudarAltura(String username, float altura){
        Utilizador u = uDao.findUtilizador_Username(username);
        if(u!=null){
            u.setAltura(altura);
            uDao.save(u);
            return true;
        }
        return false;
    }

    public Utilizador getUserByUsername(String username){
        return uDao.findUtilizador_Username(username); 
    }


    public JSONArray getUsers(){

        JSONArray res = new JSONArray();
        
        List<Utilizador> l = uDao.findAll();
        for(Utilizador e: l){
            JSONObject exe = new JSONObject();
            exe.put("nome", e.getNome());
            exe.put("username", e.getUsername());
            exe.put("email",e.getEmail());
            
            res.put(exe);
        }

        return res;
    }

    public boolean deleteUtilizador(String codigo){
        Utilizador u = uDao.findUtilizador_Username(codigo);
        uDao.delete(u);
        return true;
    }

}
