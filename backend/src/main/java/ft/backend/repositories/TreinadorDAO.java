package ft.backend.repositories;

import ft.backend.entities.Utilizador;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ft.backend.entities.Treinador;

public interface TreinadorDAO extends JpaRepository<Treinador,Integer> {
    
    @Query(value = "SELECT * FROM treinador u WHERE u.username = :username",nativeQuery = true)
    public Treinador findTreinador_Username(@Param("username") String username);

    @Query(value = "SELECT * FROM treinador u WHERE u.email = :email",nativeQuery = true)
    public Treinador findTreinador_Email(@Param("email") String email);

    @Query(value = "SELECT * FROM treinador e where e.nome like %:filtro%",nativeQuery = true)
    public List<Treinador> listTreinadoresByFiltro(@Param("filtro") String filtro);

    @Query(value = "SELECT COUNT(*) FROM treinador", nativeQuery = true)
    public int num_treinadores();

}
