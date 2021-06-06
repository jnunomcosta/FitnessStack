package ft.backend.beans;

import ft.backend.entities.ConteudoMedia;
import ft.backend.repositories.CategoriaDAO;
import ft.backend.repositories.ConteudoMediaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class gestao_conteudomedia {

    @Autowired
    ConteudoMediaDAO conteudoMediaDAO;

    public ConteudoMedia getConteudo(String id){
        return conteudoMediaDAO.getConteudoMediaByID(id);
    }

}
