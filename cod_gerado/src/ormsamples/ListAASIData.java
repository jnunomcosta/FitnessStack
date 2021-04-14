/**
 * Licensee: Nuno Costa(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class ListAASIData {
	private static final int ROW_COUNT = 100;
	
	public void listTestData() throws PersistentException {
		System.out.println("Listing Treino...");
		diagrama_classes.Treino[] diagrama_classesTreinos = diagrama_classes.TreinoDAO.listTreinoByQuery(null, null);
		int length = Math.min(diagrama_classesTreinos.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(diagrama_classesTreinos[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Utilizador...");
		diagrama_classes.Utilizador[] diagrama_classesUtilizadors = diagrama_classes.UtilizadorDAO.listUtilizadorByQuery(null, null);
		length = Math.min(diagrama_classesUtilizadors.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(diagrama_classesUtilizadors[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Treinador...");
		diagrama_classes.Treinador[] diagrama_classesTreinadors = diagrama_classes.TreinadorDAO.listTreinadorByQuery(null, null);
		length = Math.min(diagrama_classesTreinadors.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(diagrama_classesTreinadors[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Exercicio...");
		diagrama_classes.Exercicio[] diagrama_classesExercicios = diagrama_classes.ExercicioDAO.listExercicioByQuery(null, null);
		length = Math.min(diagrama_classesExercicios.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(diagrama_classesExercicios[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Bloco...");
		diagrama_classes.Bloco[] diagrama_classesBlocos = diagrama_classes.BlocoDAO.listBlocoByQuery(null, null);
		length = Math.min(diagrama_classesBlocos.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(diagrama_classesBlocos[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Administrador...");
		diagrama_classes.Administrador[] diagrama_classesAdministradors = diagrama_classes.AdministradorDAO.listAdministradorByQuery(null, null);
		length = Math.min(diagrama_classesAdministradors.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(diagrama_classesAdministradors[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Marcacao...");
		diagrama_classes.Marcacao[] diagrama_classesMarcacaos = diagrama_classes.MarcacaoDAO.listMarcacaoByQuery(null, null);
		length = Math.min(diagrama_classesMarcacaos.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(diagrama_classesMarcacaos[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Avaliacao...");
		diagrama_classes.Avaliacao[] diagrama_classesAvaliacaos = diagrama_classes.AvaliacaoDAO.listAvaliacaoByQuery(null, null);
		length = Math.min(diagrama_classesAvaliacaos.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(diagrama_classesAvaliacaos[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
	}
	
	public void listByCriteria() throws PersistentException {
		System.out.println("Listing Treino by Criteria...");
		diagrama_classes.TreinoCriteria ldiagrama_classesTreinoCriteria = new diagrama_classes.TreinoCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldiagrama_classesTreinoCriteria.ID.eq();
		ldiagrama_classesTreinoCriteria.setMaxResults(ROW_COUNT);
		diagrama_classes.Treino[] diagrama_classesTreinos = ldiagrama_classesTreinoCriteria.listTreino();
		int length =diagrama_classesTreinos== null ? 0 : Math.min(diagrama_classesTreinos.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(diagrama_classesTreinos[i]);
		}
		System.out.println(length + " Treino record(s) retrieved."); 
		
		System.out.println("Listing Utilizador by Criteria...");
		diagrama_classes.UtilizadorCriteria ldiagrama_classesUtilizadorCriteria = new diagrama_classes.UtilizadorCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldiagrama_classesUtilizadorCriteria.ID.eq();
		ldiagrama_classesUtilizadorCriteria.setMaxResults(ROW_COUNT);
		diagrama_classes.Utilizador[] diagrama_classesUtilizadors = ldiagrama_classesUtilizadorCriteria.listUtilizador();
		length =diagrama_classesUtilizadors== null ? 0 : Math.min(diagrama_classesUtilizadors.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(diagrama_classesUtilizadors[i]);
		}
		System.out.println(length + " Utilizador record(s) retrieved."); 
		
		System.out.println("Listing Treinador by Criteria...");
		diagrama_classes.TreinadorCriteria ldiagrama_classesTreinadorCriteria = new diagrama_classes.TreinadorCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldiagrama_classesTreinadorCriteria.ID.eq();
		ldiagrama_classesTreinadorCriteria.setMaxResults(ROW_COUNT);
		diagrama_classes.Treinador[] diagrama_classesTreinadors = ldiagrama_classesTreinadorCriteria.listTreinador();
		length =diagrama_classesTreinadors== null ? 0 : Math.min(diagrama_classesTreinadors.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(diagrama_classesTreinadors[i]);
		}
		System.out.println(length + " Treinador record(s) retrieved."); 
		
		System.out.println("Listing Exercicio by Criteria...");
		diagrama_classes.ExercicioCriteria ldiagrama_classesExercicioCriteria = new diagrama_classes.ExercicioCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldiagrama_classesExercicioCriteria.ID.eq();
		ldiagrama_classesExercicioCriteria.setMaxResults(ROW_COUNT);
		diagrama_classes.Exercicio[] diagrama_classesExercicios = ldiagrama_classesExercicioCriteria.listExercicio();
		length =diagrama_classesExercicios== null ? 0 : Math.min(diagrama_classesExercicios.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(diagrama_classesExercicios[i]);
		}
		System.out.println(length + " Exercicio record(s) retrieved."); 
		
		System.out.println("Listing Bloco by Criteria...");
		diagrama_classes.BlocoCriteria ldiagrama_classesBlocoCriteria = new diagrama_classes.BlocoCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldiagrama_classesBlocoCriteria.ID.eq();
		ldiagrama_classesBlocoCriteria.setMaxResults(ROW_COUNT);
		diagrama_classes.Bloco[] diagrama_classesBlocos = ldiagrama_classesBlocoCriteria.listBloco();
		length =diagrama_classesBlocos== null ? 0 : Math.min(diagrama_classesBlocos.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(diagrama_classesBlocos[i]);
		}
		System.out.println(length + " Bloco record(s) retrieved."); 
		
		System.out.println("Listing Administrador by Criteria...");
		diagrama_classes.AdministradorCriteria ldiagrama_classesAdministradorCriteria = new diagrama_classes.AdministradorCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldiagrama_classesAdministradorCriteria.ID.eq();
		ldiagrama_classesAdministradorCriteria.setMaxResults(ROW_COUNT);
		diagrama_classes.Administrador[] diagrama_classesAdministradors = ldiagrama_classesAdministradorCriteria.listAdministrador();
		length =diagrama_classesAdministradors== null ? 0 : Math.min(diagrama_classesAdministradors.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(diagrama_classesAdministradors[i]);
		}
		System.out.println(length + " Administrador record(s) retrieved."); 
		
		System.out.println("Listing Marcacao by Criteria...");
		diagrama_classes.MarcacaoCriteria ldiagrama_classesMarcacaoCriteria = new diagrama_classes.MarcacaoCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldiagrama_classesMarcacaoCriteria.ID.eq();
		ldiagrama_classesMarcacaoCriteria.setMaxResults(ROW_COUNT);
		diagrama_classes.Marcacao[] diagrama_classesMarcacaos = ldiagrama_classesMarcacaoCriteria.listMarcacao();
		length =diagrama_classesMarcacaos== null ? 0 : Math.min(diagrama_classesMarcacaos.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(diagrama_classesMarcacaos[i]);
		}
		System.out.println(length + " Marcacao record(s) retrieved."); 
		
		System.out.println("Listing Avaliacao by Criteria...");
		diagrama_classes.AvaliacaoCriteria ldiagrama_classesAvaliacaoCriteria = new diagrama_classes.AvaliacaoCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldiagrama_classesAvaliacaoCriteria.ID.eq();
		ldiagrama_classesAvaliacaoCriteria.setMaxResults(ROW_COUNT);
		diagrama_classes.Avaliacao[] diagrama_classesAvaliacaos = ldiagrama_classesAvaliacaoCriteria.listAvaliacao();
		length =diagrama_classesAvaliacaos== null ? 0 : Math.min(diagrama_classesAvaliacaos.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(diagrama_classesAvaliacaos[i]);
		}
		System.out.println(length + " Avaliacao record(s) retrieved."); 
		
	}
	
	public static void main(String[] args) {
		try {
			ListAASIData listAASIData = new ListAASIData();
			try {
				listAASIData.listTestData();
				//listAASIData.listByCriteria();
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
