package ft.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import ft.backend.entities.Exercicio;

public interface ExercicioDAO extends JpaRepository<Exercicio,Integer>{

    @Query(value = "SELECT * FROM exercicio e WHERE e.nome = :nome",nativeQuery = true)
    public Exercicio findExercicioByNome(@Param("nome") String nome);

    @Query(value = "SELECT e.nome FROM exercicio e",nativeQuery = true)
    public List<String> listNomeExercicios();

    @Query(value = "SELECT * FROM exercicio e where e.nome like %:filtro% limit :pag,:pag_max",nativeQuery = true)
    public List<Exercicio> listExerciciosByFiltro(@Param("filtro") String filtro,@Param("pag") int pag,@Param("pag_max") int pag_max);

    @Query(value = "SELECT * FROM exercicio e limit :pag,:pag_max",nativeQuery = true)
    public List<Exercicio> listExerciciosByFiltro(@Param("pag") int pag,@Param("pag_max") int pag_max);

    @Query(value = "SELECT * FROM exercicio e where e.nome like %:filtro%",nativeQuery = true)
    public List<Exercicio> listExerciciosByFiltro(@Param("filtro") String filtro);

    @Query(value = "SELECT COUNT(*) FROM exercicio", nativeQuery = true)
    public int num_exercicios();
}
