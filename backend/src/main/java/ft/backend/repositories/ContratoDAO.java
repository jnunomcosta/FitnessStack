package ft.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ft.backend.entities.Categoria;
import ft.backend.entities.Contrato;

public interface ContratoDAO extends JpaRepository<Contrato,Integer>{
    
    @Query(value = "SELECT * FROM contrato u WHERE u.UtilizadorID = :utilizador",nativeQuery = true)
    public Contrato findContratoIdUser(@Param("utilizador") int utilizador);

    @Query(value = "SELECT * FROM contrato u WHERE u.TreinadorID = :treinador",nativeQuery = true)
    public List<Contrato> findContratoIdTreinador(@Param("treinador") int treinador);

}
