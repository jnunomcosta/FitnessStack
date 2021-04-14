/**
 * Licensee: Nuno Costa(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class DeleteAASIData {
	public void deleteTestData() throws PersistentException {
		PersistentTransaction t = diagrama_classes.AASIPersistentManager.instance().getSession().beginTransaction();
		try {
			diagrama_classes.Treino ldiagrama_classesTreino = diagrama_classes.TreinoDAO.loadTreinoByQuery(null, null);
			// Delete the persistent object
			diagrama_classes.TreinoDAO.delete(ldiagrama_classesTreino);
			diagrama_classes.Utilizador ldiagrama_classesUtilizador = diagrama_classes.UtilizadorDAO.loadUtilizadorByQuery(null, null);
			// Delete the persistent object
			diagrama_classes.UtilizadorDAO.delete(ldiagrama_classesUtilizador);
			diagrama_classes.Treinador ldiagrama_classesTreinador = diagrama_classes.TreinadorDAO.loadTreinadorByQuery(null, null);
			// Delete the persistent object
			diagrama_classes.TreinadorDAO.delete(ldiagrama_classesTreinador);
			diagrama_classes.Exercicio ldiagrama_classesExercicio = diagrama_classes.ExercicioDAO.loadExercicioByQuery(null, null);
			// Delete the persistent object
			diagrama_classes.ExercicioDAO.delete(ldiagrama_classesExercicio);
			diagrama_classes.Bloco ldiagrama_classesBloco = diagrama_classes.BlocoDAO.loadBlocoByQuery(null, null);
			// Delete the persistent object
			diagrama_classes.BlocoDAO.delete(ldiagrama_classesBloco);
			diagrama_classes.Administrador ldiagrama_classesAdministrador = diagrama_classes.AdministradorDAO.loadAdministradorByQuery(null, null);
			// Delete the persistent object
			diagrama_classes.AdministradorDAO.delete(ldiagrama_classesAdministrador);
			diagrama_classes.Marcacao ldiagrama_classesMarcacao = diagrama_classes.MarcacaoDAO.loadMarcacaoByQuery(null, null);
			// Delete the persistent object
			diagrama_classes.MarcacaoDAO.delete(ldiagrama_classesMarcacao);
			diagrama_classes.Avaliacao ldiagrama_classesAvaliacao = diagrama_classes.AvaliacaoDAO.loadAvaliacaoByQuery(null, null);
			// Delete the persistent object
			diagrama_classes.AvaliacaoDAO.delete(ldiagrama_classesAvaliacao);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			DeleteAASIData deleteAASIData = new DeleteAASIData();
			try {
				deleteAASIData.deleteTestData();
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
