package ft.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ft.backend.entities.ConteudoMedia;

public interface ConteudoMediaDAO extends JpaRepository<ConteudoMedia,Integer> {
    
}
