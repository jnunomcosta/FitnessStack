package ft.backend.beans;

import java.util.Base64;
import java.util.List;

import ft.backend.entities.Utilizador;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ft.backend.entities.Avaliacao_Treinador;
import ft.backend.entities.Treinador;
import ft.backend.repositories.TreinadorDAO;

@Service
public class gestao_treinadores {
    
    @Autowired
    TreinadorDAO tDao;
    
    public boolean loginTreinador(String username, String password){
        Treinador u = tDao.findTreinador_Username(username);
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

    public JSONArray getTreinadores(){
        JSONArray ret = new JSONArray();

        List<Treinador> t = tDao.findAll();
        for(Treinador tr : t){
            JSONObject exe = new JSONObject();
            exe.put("nome", tr.getNome());
            exe.put("username", tr.getUsername());
            exe.put("email", tr.getEmail());
            exe.put("descricao", tr.getDescricao());
            exe.put("classsificacao",tr.getMediaAvaliacao());
            exe.put("numero_classsificacao",tr.getNumeroAvaliacoes());
            exe.put("imagem", "/api/assets/photo/"+tr.getFoto_perfil().getID());
            ret.put(exe);
        }

        return ret;
    }

    public JSONObject getTreinadorInformation(String username){
        Treinador u = tDao.findTreinador_Username(username);
        if(u != null){
            JSONObject ret = new JSONObject();
            ret.put("email", u.getEmail());
            ret.put("nome",u.getNome());
            ret.put("username", username);
            ret.put("classsificacao",u.getMediaAvaliacao());
            ret.put("numero_classsificacao",u.getNumeroAvaliacoes());
            ret.put("foto_perfil","/api/assets/photo/"+u.getFoto_perfil().getID());
            ret.put("descricao",u.getDescricao());
            JSONArray a = new JSONArray();
            for(Avaliacao_Treinador t : u.getORM_avaliacoes_t()){
                JSONObject o = new JSONObject();
                o.put("nota", t.getClassificacao());
                o.put("comentario", t.getComentario());
                o.put("username", t.getUser().getUsername());
                o.put("foto_user","/api/assets/photo/"+t.getUser().getFoto_perfil().getID());
                a.put(o);
            }
            ret.put("comentarios", a);
            return ret;
        }
        else{
            return null;
        }
    }

    public JSONObject getSideBarTreinadorInformation(String username){
        Treinador u = tDao.findTreinador_Username(username);
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

    public Treinador getTreinadorByUsername(String username){
        return tDao.findTreinador_Username(username);
    }


    public boolean avaliacao(String username, Avaliacao_Treinador at){
        boolean b = false;
        Treinador t = tDao.findTreinador_Username(username);
        if(t!=null){
            t.getORM_avaliacoes_t().add(at);
           
            float numero = t.getNumeroAvaliacoes();
            float media = t.getMediaAvaliacao();

            float nova_media = (float) (media*numero + at.getClassificacao()) / ( numero +1);

            t.setNumeroAvaliacoes(numero+1);
            t.setMediaAvaliacao(nova_media);

            tDao.save(t);
            b = true;
        }
        return b;
    }

    public boolean deleteTreinador(String codigo){
        Treinador u = tDao.findTreinador_Username(codigo);
        tDao.delete(u);
        return true;
    }

    public boolean mudarEmail(String username,String email){
        Treinador u = tDao.findTreinador_Username(username);
        if(u != null){
            u.setEmail(email);
            tDao.save(u);
            return true;
        }
        return false;
    }

    public boolean mudarUsername(String username,String username_novo){
        Treinador u = tDao.findTreinador_Username(username);
        if(u != null){
            if(tDao.findTreinador_Username(username_novo) == null){
                u.setUsername(username_novo);
                tDao.save(u);
                return true;
            }
        }
        return false;
    }

    public boolean mudarPassword(String username,String oldP, String newP){
        Treinador u = tDao.findTreinador_Username(username);
        if(u != null){
            if(oldP.equals(u.getPassword())){
                u.setPassword(newP);
                tDao.save(u);
                return true;
            }
        }
        return false;
    }

    public boolean mudarImagem(String username,String imagem){
        Treinador u = tDao.findTreinador_Username(username);
        if(u!=null){
            u.getFoto_perfil().setConteudo(Base64.getDecoder().decode(imagem));
            tDao.save(u);
            return true;
        }
        return false;
    }

}
