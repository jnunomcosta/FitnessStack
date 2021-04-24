package ft.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ft.backend.entities.Bloco;

public interface BlocoDAO extends JpaRepository<Bloco,Integer>{
    
}
