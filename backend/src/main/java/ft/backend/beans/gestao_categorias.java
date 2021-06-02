package ft.backend.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ft.backend.entities.Categoria;
import ft.backend.repositories.CategoriaDAO;

@Service
public class gestao_categorias {
    
    @Autowired
    CategoriaDAO categoriaDAO;

    public Categoria getCategoria(String categoria){
        Categoria ret = categoriaDAO.findCategoria(categoria);
        if(ret==null){
            ret = new Categoria();
            ret.setCategoria(categoria);
        }
        return ret;
    }
}
