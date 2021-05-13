package ft.backend.repositories;

import ft.backend.entities.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ft.backend.entities.Treinador;

public interface TreinadorDAO extends JpaRepository<Treinador,Integer> {
    
    @Query(value = "SELECT * FROM Treinador u WHERE u.username = :username",nativeQuery = true)
    public Treinador findTreinador_Username(@Param("username") String username);

    @Query(value = "SELECT * FROM Treinador u WHERE u.email = :email",nativeQuery = true)
    public Utilizador findTreinador_Email(@Param("email") String email);

}
