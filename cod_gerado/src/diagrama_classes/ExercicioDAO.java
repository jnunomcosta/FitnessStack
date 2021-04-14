/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: Nuno Costa(Universidade do Minho)
 * License Type: Academic
 */
package diagrama_classes;

import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class ExercicioDAO {
	public static Exercicio loadExercicioByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return loadExercicioByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Exercicio getExercicioByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return getExercicioByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Exercicio loadExercicioByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return loadExercicioByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Exercicio getExercicioByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return getExercicioByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Exercicio loadExercicioByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Exercicio) session.load(diagrama_classes.Exercicio.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Exercicio getExercicioByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Exercicio) session.get(diagrama_classes.Exercicio.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Exercicio loadExercicioByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Exercicio) session.load(diagrama_classes.Exercicio.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Exercicio getExercicioByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Exercicio) session.get(diagrama_classes.Exercicio.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryExercicio(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return queryExercicio(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryExercicio(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return queryExercicio(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Exercicio[] listExercicioByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return listExercicioByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Exercicio[] listExercicioByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return listExercicioByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryExercicio(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_classes.Exercicio as Exercicio");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryExercicio(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_classes.Exercicio as Exercicio");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Exercicio", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Exercicio[] listExercicioByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryExercicio(session, condition, orderBy);
			return (Exercicio[]) list.toArray(new Exercicio[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Exercicio[] listExercicioByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryExercicio(session, condition, orderBy, lockMode);
			return (Exercicio[]) list.toArray(new Exercicio[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Exercicio loadExercicioByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return loadExercicioByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Exercicio loadExercicioByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return loadExercicioByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Exercicio loadExercicioByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Exercicio[] exercicios = listExercicioByQuery(session, condition, orderBy);
		if (exercicios != null && exercicios.length > 0)
			return exercicios[0];
		else
			return null;
	}
	
	public static Exercicio loadExercicioByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Exercicio[] exercicios = listExercicioByQuery(session, condition, orderBy, lockMode);
		if (exercicios != null && exercicios.length > 0)
			return exercicios[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateExercicioByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return iterateExercicioByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateExercicioByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return iterateExercicioByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateExercicioByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_classes.Exercicio as Exercicio");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateExercicioByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_classes.Exercicio as Exercicio");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Exercicio", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Exercicio createExercicio() {
		return new diagrama_classes.Exercicio();
	}
	
	public static boolean save(diagrama_classes.Exercicio exercicio) throws PersistentException {
		try {
			AASIPersistentManager.instance().saveObject(exercicio);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(diagrama_classes.Exercicio exercicio) throws PersistentException {
		try {
			AASIPersistentManager.instance().deleteObject(exercicio);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(diagrama_classes.Exercicio exercicio) throws PersistentException {
		try {
			AASIPersistentManager.instance().getSession().refresh(exercicio);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(diagrama_classes.Exercicio exercicio) throws PersistentException {
		try {
			AASIPersistentManager.instance().getSession().evict(exercicio);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Exercicio loadExercicioByCriteria(ExercicioCriteria exercicioCriteria) {
		Exercicio[] exercicios = listExercicioByCriteria(exercicioCriteria);
		if(exercicios == null || exercicios.length == 0) {
			return null;
		}
		return exercicios[0];
	}
	
	public static Exercicio[] listExercicioByCriteria(ExercicioCriteria exercicioCriteria) {
		return exercicioCriteria.listExercicio();
	}
}
