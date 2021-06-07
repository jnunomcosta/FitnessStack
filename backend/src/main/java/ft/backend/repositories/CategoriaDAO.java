package ft.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ft.backend.entities.Categoria;

public interface CategoriaDAO extends JpaRepository<Categoria,Integer>{
    
    @Query(value = "SELECT * FROM categoria u WHERE u.categoria = :categoria",nativeQuery = true)
    public Categoria findCategoria(@Param("categoria") String categoria);

}
