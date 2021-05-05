package ft.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ft.backend.entities.Treino;
import java.util.*;

public interface TreinoDAO extends JpaRepository<Treino,Integer>{

    @Query(value = "SELECT * FROM Treino",nativeQuery = true)
    public List<Treino> findAllTreinos();

    @Query(value = "SELECT * FROM Treino u WHERE u.id = :id",nativeQuery = true)
    public Treino findbyId(@Param("id") int id);
}
