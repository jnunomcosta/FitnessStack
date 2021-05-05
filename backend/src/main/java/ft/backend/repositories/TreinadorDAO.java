package ft.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ft.backend.entities.Treinador;

public interface TreinadorDAO extends JpaRepository<Treinador,Integer> {
    
    @Query(value = "SELECT * FROM Treinador u WHERE u.email = :email and u.password = :pass",nativeQuery = true)
    public Treinador findTreinador_Email_Password(@Param("email") String email,@Param("pass") String password);

}
