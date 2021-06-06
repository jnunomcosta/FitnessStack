package ft.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ft.backend.entities.ConteudoMedia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConteudoMediaDAO extends JpaRepository<ConteudoMedia,Integer> {

    @Query(value = "SELECT * FROM conteudo_media u where u.id = :id",nativeQuery = true)
    public ConteudoMedia getConteudoMediaByID(@Param("id") String id);

}
