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

public class TreinadorDAO {
	public static Treinador loadTreinadorByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return loadTreinadorByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treinador getTreinadorByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return getTreinadorByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treinador loadTreinadorByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return loadTreinadorByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treinador getTreinadorByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return getTreinadorByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treinador loadTreinadorByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Treinador) session.load(diagrama_classes.Treinador.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treinador getTreinadorByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Treinador) session.get(diagrama_classes.Treinador.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treinador loadTreinadorByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Treinador) session.load(diagrama_classes.Treinador.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treinador getTreinadorByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Treinador) session.get(diagrama_classes.Treinador.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryTreinador(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return queryTreinador(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryTreinador(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return queryTreinador(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treinador[] listTreinadorByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return listTreinadorByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treinador[] listTreinadorByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return listTreinadorByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryTreinador(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_classes.Treinador as Treinador");
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
	
	public static List queryTreinador(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_classes.Treinador as Treinador");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Treinador", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treinador[] listTreinadorByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryTreinador(session, condition, orderBy);
			return (Treinador[]) list.toArray(new Treinador[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treinador[] listTreinadorByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryTreinador(session, condition, orderBy, lockMode);
			return (Treinador[]) list.toArray(new Treinador[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treinador loadTreinadorByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return loadTreinadorByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treinador loadTreinadorByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return loadTreinadorByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treinador loadTreinadorByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Treinador[] treinadors = listTreinadorByQuery(session, condition, orderBy);
		if (treinadors != null && treinadors.length > 0)
			return treinadors[0];
		else
			return null;
	}
	
	public static Treinador loadTreinadorByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Treinador[] treinadors = listTreinadorByQuery(session, condition, orderBy, lockMode);
		if (treinadors != null && treinadors.length > 0)
			return treinadors[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateTreinadorByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return iterateTreinadorByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateTreinadorByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return iterateTreinadorByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateTreinadorByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_classes.Treinador as Treinador");
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
	
	public static java.util.Iterator iterateTreinadorByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_classes.Treinador as Treinador");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Treinador", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treinador createTreinador() {
		return new diagrama_classes.Treinador();
	}
	
	public static boolean save(diagrama_classes.Treinador treinador) throws PersistentException {
		try {
			AASIPersistentManager.instance().saveObject(treinador);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(diagrama_classes.Treinador treinador) throws PersistentException {
		try {
			AASIPersistentManager.instance().deleteObject(treinador);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(diagrama_classes.Treinador treinador) throws PersistentException {
		try {
			AASIPersistentManager.instance().getSession().refresh(treinador);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(diagrama_classes.Treinador treinador) throws PersistentException {
		try {
			AASIPersistentManager.instance().getSession().evict(treinador);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Treinador loadTreinadorByCriteria(TreinadorCriteria treinadorCriteria) {
		Treinador[] treinadors = listTreinadorByCriteria(treinadorCriteria);
		if(treinadors == null || treinadors.length == 0) {
			return null;
		}
		return treinadors[0];
	}
	
	public static Treinador[] listTreinadorByCriteria(TreinadorCriteria treinadorCriteria) {
		return treinadorCriteria.listTreinador();
	}
}
