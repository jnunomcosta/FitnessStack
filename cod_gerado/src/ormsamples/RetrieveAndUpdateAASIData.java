/**
 * Licensee: Nuno Costa(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class RetrieveAndUpdateAASIData {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = diagrama_classes.AASIPersistentManager.instance().getSession().beginTransaction();
		try {
			diagrama_classes.Treino ldiagrama_classesTreino = diagrama_classes.TreinoDAO.loadTreinoByQuery(null, null);
			// Update the properties of the persistent object
			diagrama_classes.TreinoDAO.save(ldiagrama_classesTreino);
			diagrama_classes.Utilizador ldiagrama_classesUtilizador = diagrama_classes.UtilizadorDAO.loadUtilizadorByQuery(null, null);
			// Update the properties of the persistent object
			diagrama_classes.UtilizadorDAO.save(ldiagrama_classesUtilizador);
			diagrama_classes.Treinador ldiagrama_classesTreinador = diagrama_classes.TreinadorDAO.loadTreinadorByQuery(null, null);
			// Update the properties of the persistent object
			diagrama_classes.TreinadorDAO.save(ldiagrama_classesTreinador);
			diagrama_classes.Exercicio ldiagrama_classesExercicio = diagrama_classes.ExercicioDAO.loadExercicioByQuery(null, null);
			// Update the properties of the persistent object
			diagrama_classes.ExercicioDAO.save(ldiagrama_classesExercicio);
			diagrama_classes.Bloco ldiagrama_classesBloco = diagrama_classes.BlocoDAO.loadBlocoByQuery(null, null);
			// Update the properties of the persistent object
			diagrama_classes.BlocoDAO.save(ldiagrama_classesBloco);
			diagrama_classes.Administrador ldiagrama_classesAdministrador = diagrama_classes.AdministradorDAO.loadAdministradorByQuery(null, null);
			// Update the properties of the persistent object
			diagrama_classes.AdministradorDAO.save(ldiagrama_classesAdministrador);
			diagrama_classes.Marcacao ldiagrama_classesMarcacao = diagrama_classes.MarcacaoDAO.loadMarcacaoByQuery(null, null);
			// Update the properties of the persistent object
			diagrama_classes.MarcacaoDAO.save(ldiagrama_classesMarcacao);
			diagrama_classes.Avaliacao ldiagrama_classesAvaliacao = diagrama_classes.AvaliacaoDAO.loadAvaliacaoByQuery(null, null);
			// Update the properties of the persistent object
			diagrama_classes.AvaliacaoDAO.save(ldiagrama_classesAvaliacao);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public void retrieveByCriteria() throws PersistentException {
		System.out.println("Retrieving Treino by TreinoCriteria");
		diagrama_classes.TreinoCriteria ldiagrama_classesTreinoCriteria = new diagrama_classes.TreinoCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldiagrama_classesTreinoCriteria.ID.eq();
		System.out.println(ldiagrama_classesTreinoCriteria.uniqueTreino());
		
		System.out.println("Retrieving Utilizador by UtilizadorCriteria");
		diagrama_classes.UtilizadorCriteria ldiagrama_classesUtilizadorCriteria = new diagrama_classes.UtilizadorCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldiagrama_classesUtilizadorCriteria.ID.eq();
		System.out.println(ldiagrama_classesUtilizadorCriteria.uniqueUtilizador());
		
		System.out.println("Retrieving Treinador by TreinadorCriteria");
		diagrama_classes.TreinadorCriteria ldiagrama_classesTreinadorCriteria = new diagrama_classes.TreinadorCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldiagrama_classesTreinadorCriteria.ID.eq();
		System.out.println(ldiagrama_classesTreinadorCriteria.uniqueTreinador());
		
		System.out.println("Retrieving Exercicio by ExercicioCriteria");
		diagrama_classes.ExercicioCriteria ldiagrama_classesExercicioCriteria = new diagrama_classes.ExercicioCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldiagrama_classesExercicioCriteria.ID.eq();
		System.out.println(ldiagrama_classesExercicioCriteria.uniqueExercicio());
		
		System.out.println("Retrieving Bloco by BlocoCriteria");
		diagrama_classes.BlocoCriteria ldiagrama_classesBlocoCriteria = new diagrama_classes.BlocoCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldiagrama_classesBlocoCriteria.ID.eq();
		System.out.println(ldiagrama_classesBlocoCriteria.uniqueBloco());
		
		System.out.println("Retrieving Administrador by AdministradorCriteria");
		diagrama_classes.AdministradorCriteria ldiagrama_classesAdministradorCriteria = new diagrama_classes.AdministradorCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldiagrama_classesAdministradorCriteria.ID.eq();
		System.out.println(ldiagrama_classesAdministradorCriteria.uniqueAdministrador());
		
		System.out.println("Retrieving Marcacao by MarcacaoCriteria");
		diagrama_classes.MarcacaoCriteria ldiagrama_classesMarcacaoCriteria = new diagrama_classes.MarcacaoCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldiagrama_classesMarcacaoCriteria.ID.eq();
		System.out.println(ldiagrama_classesMarcacaoCriteria.uniqueMarcacao());
		
		System.out.println("Retrieving Avaliacao by AvaliacaoCriteria");
		diagrama_classes.AvaliacaoCriteria ldiagrama_classesAvaliacaoCriteria = new diagrama_classes.AvaliacaoCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldiagrama_classesAvaliacaoCriteria.ID.eq();
		System.out.println(ldiagrama_classesAvaliacaoCriteria.uniqueAvaliacao());
		
	}
	
	
	public static void main(String[] args) {
		try {
			RetrieveAndUpdateAASIData retrieveAndUpdateAASIData = new RetrieveAndUpdateAASIData();
			try {
				retrieveAndUpdateAASIData.retrieveAndUpdateTestData();
				//retrieveAndUpdateAASIData.retrieveByCriteria();
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
