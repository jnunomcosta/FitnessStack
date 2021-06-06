package ft.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ft.backend.entities.*;

public interface UtilizadorDAO extends JpaRepository<Utilizador,Integer> {

    @Query(value = "SELECT * FROM utilizador u WHERE u.nome = :searchTerm",nativeQuery = true)
    public List<Utilizador> findByJavardice(@Param("searchTerm") String searchTerm);

    @Query(value = "SELECT * FROM utilizador u WHERE u.username = :username",nativeQuery = true)
    public Utilizador findUtilizador_Username(@Param("username") String username);

    @Query(value = "SELECT * FROM utilizador u WHERE u.email = :email",nativeQuery = true)
    public Utilizador findUtilizador_Email(@Param("email") String email);

}
