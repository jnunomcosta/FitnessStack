package ft.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ft.backend.entities.Treinador;

public interface TreinadorDAO extends JpaRepository<Treinador,Integer> {
    
}
