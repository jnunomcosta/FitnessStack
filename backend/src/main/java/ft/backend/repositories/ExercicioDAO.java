package ft.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ft.backend.entities.Exercicio;

public interface ExercicioDAO extends JpaRepository<Exercicio,Integer>{
    
}
