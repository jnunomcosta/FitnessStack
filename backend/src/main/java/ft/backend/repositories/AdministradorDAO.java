package ft.backend.repositories;

import ft.backend.entities.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdministradorDAO extends JpaRepository<Administrador,Integer> {

    @Query(value = "SELECT * FROM administrador u WHERE u.username = :username",nativeQuery = true)
    public Administrador findAdmin_Username(@Param("username") String username);

}
