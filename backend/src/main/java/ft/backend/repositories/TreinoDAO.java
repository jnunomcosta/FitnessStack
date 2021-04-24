package ft.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ft.backend.entities.Treino;

public interface TreinoDAO extends JpaRepository<Treino,Integer>{
    
}
