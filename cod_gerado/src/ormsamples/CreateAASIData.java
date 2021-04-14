/**
 * Licensee: Nuno Costa(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class CreateAASIData {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = diagrama_classes.AASIPersistentManager.instance().getSession().beginTransaction();
		try {
			diagrama_classes.Treino ldiagrama_classesTreino = diagrama_classes.TreinoDAO.createTreino();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : avaliacoes_treino, blocos_exercicios, duracao
			diagrama_classes.TreinoDAO.save(ldiagrama_classesTreino);
			diagrama_classes.Utilizador ldiagrama_classesUtilizador = diagrama_classes.UtilizadorDAO.createUtilizador();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : agenda, genero, altura, peso, idade
			diagrama_classes.UtilizadorDAO.save(ldiagrama_classesUtilizador);
			diagrama_classes.Treinador ldiagrama_classesTreinador = diagrama_classes.TreinadorDAO.createTreinador();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : avaliacoes_t, genero, altura, peso, idade
			diagrama_classes.TreinadorDAO.save(ldiagrama_classesTreinador);
			diagrama_classes.Exercicio ldiagrama_classesExercicio = diagrama_classes.ExercicioDAO.createExercicio();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : duracao_media, criador_exercicio
			diagrama_classes.ExercicioDAO.save(ldiagrama_classesExercicio);
			diagrama_classes.Bloco ldiagrama_classesBloco = diagrama_classes.BlocoDAO.createBloco();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : duracao, series, descanso, exercicio
			diagrama_classes.BlocoDAO.save(ldiagrama_classesBloco);
			diagrama_classes.Administrador ldiagrama_classesAdministrador = diagrama_classes.AdministradorDAO.createAdministrador();
			// Initialize the properties of the persistent object here
			diagrama_classes.AdministradorDAO.save(ldiagrama_classesAdministrador);
			diagrama_classes.Marcacao ldiagrama_classesMarcacao = diagrama_classes.MarcacaoDAO.createMarcacao();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : treino
			diagrama_classes.MarcacaoDAO.save(ldiagrama_classesMarcacao);
			diagrama_classes.Avaliacao ldiagrama_classesAvaliacao = diagrama_classes.AvaliacaoDAO.createAvaliacao();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : classificacao
			diagrama_classes.AvaliacaoDAO.save(ldiagrama_classesAvaliacao);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			CreateAASIData createAASIData = new CreateAASIData();
			try {
				createAASIData.createTestData();
			}
			finally {
				diagrama_classes.AASIPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
