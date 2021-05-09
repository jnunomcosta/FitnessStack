package ft.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ft.backend.entities.Marcacao;

public interface MarcacaoDAO extends JpaRepository<Marcacao,Integer>{
    
}
