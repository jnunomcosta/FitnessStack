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

public class TreinoDAO {
	public static Treino loadTreinoByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return loadTreinoByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treino getTreinoByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return getTreinoByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treino loadTreinoByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return loadTreinoByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treino getTreinoByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return getTreinoByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treino loadTreinoByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Treino) session.load(diagrama_classes.Treino.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treino getTreinoByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Treino) session.get(diagrama_classes.Treino.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treino loadTreinoByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Treino) session.load(diagrama_classes.Treino.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treino getTreinoByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Treino) session.get(diagrama_classes.Treino.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryTreino(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return queryTreino(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryTreino(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return queryTreino(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treino[] listTreinoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return listTreinoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treino[] listTreinoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return listTreinoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryTreino(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_classes.Treino as Treino");
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
	
	public static List queryTreino(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_classes.Treino as Treino");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Treino", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treino[] listTreinoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryTreino(session, condition, orderBy);
			return (Treino[]) list.toArray(new Treino[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treino[] listTreinoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryTreino(session, condition, orderBy, lockMode);
			return (Treino[]) list.toArray(new Treino[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treino loadTreinoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return loadTreinoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treino loadTreinoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return loadTreinoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treino loadTreinoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Treino[] treinos = listTreinoByQuery(session, condition, orderBy);
		if (treinos != null && treinos.length > 0)
			return treinos[0];
		else
			return null;
	}
	
	public static Treino loadTreinoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Treino[] treinos = listTreinoByQuery(session, condition, orderBy, lockMode);
		if (treinos != null && treinos.length > 0)
			return treinos[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateTreinoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return iterateTreinoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateTreinoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return iterateTreinoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateTreinoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_classes.Treino as Treino");
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
	
	public static java.util.Iterator iterateTreinoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_classes.Treino as Treino");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Treino", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treino createTreino() {
		return new diagrama_classes.Treino();
	}
	
	public static boolean save(diagrama_classes.Treino treino) throws PersistentException {
		try {
			AASIPersistentManager.instance().saveObject(treino);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(diagrama_classes.Treino treino) throws PersistentException {
		try {
			AASIPersistentManager.instance().deleteObject(treino);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(diagrama_classes.Treino treino) throws PersistentException {
		try {
			AASIPersistentManager.instance().getSession().refresh(treino);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(diagrama_classes.Treino treino) throws PersistentException {
		try {
			AASIPersistentManager.instance().getSession().evict(treino);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treino loadTreinoByCriteria(TreinoCriteria treinoCriteria) {
		Treino[] treinos = listTreinoByCriteria(treinoCriteria);
		if(treinos == null || treinos.length == 0) {
			return null;
		}
		return treinos[0];
	}
	
	public static Treino[] listTreinoByCriteria(TreinoCriteria treinoCriteria) {
		return treinoCriteria.listTreino();
	}
}
