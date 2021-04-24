package ft.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ft.backend.entities.Exercicio;

public interface RepositorioExercicios extends JpaRepository<Exercicio,Integer>{
    
}
