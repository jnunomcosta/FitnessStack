package ft.backend.repositories;

import ft.backend.entities.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;

import ft.backend.entities.Exercicio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExercicioDAO extends JpaRepository<Exercicio,Integer>{

    @Query(value = "SELECT * FROM exercicio e WHERE e.nome = :nome",nativeQuery = true)
    public Exercicio findExercicioByNome(@Param("nome") String nome);

    @Query(value = "SELECT e.nome FROM exercicio e",nativeQuery = true)
    public List<String> listNomeExercicios();

}
