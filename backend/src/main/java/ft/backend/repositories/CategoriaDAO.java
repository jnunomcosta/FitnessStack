package ft.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ft.backend.entities.Categoria;

public interface CategoriaDAO extends JpaRepository<Categoria,Integer>{
    
}
