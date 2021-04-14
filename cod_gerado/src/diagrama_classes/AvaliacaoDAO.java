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

public class AvaliacaoDAO {
	public static Avaliacao loadAvaliacaoByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return loadAvaliacaoByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao getAvaliacaoByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return getAvaliacaoByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao loadAvaliacaoByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return loadAvaliacaoByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao getAvaliacaoByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return getAvaliacaoByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao loadAvaliacaoByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Avaliacao) session.load(diagrama_classes.Avaliacao.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao getAvaliacaoByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Avaliacao) session.get(diagrama_classes.Avaliacao.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao loadAvaliacaoByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Avaliacao) session.load(diagrama_classes.Avaliacao.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao getAvaliacaoByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Avaliacao) session.get(diagrama_classes.Avaliacao.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryAvaliacao(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return queryAvaliacao(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryAvaliacao(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return queryAvaliacao(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao[] listAvaliacaoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return listAvaliacaoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao[] listAvaliacaoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return listAvaliacaoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryAvaliacao(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_classes.Avaliacao as Avaliacao");
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
	
	public static List queryAvaliacao(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_classes.Avaliacao as Avaliacao");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Avaliacao", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao[] listAvaliacaoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryAvaliacao(session, condition, orderBy);
			return (Avaliacao[]) list.toArray(new Avaliacao[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao[] listAvaliacaoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryAvaliacao(session, condition, orderBy, lockMode);
			return (Avaliacao[]) list.toArray(new Avaliacao[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao loadAvaliacaoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return loadAvaliacaoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao loadAvaliacaoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return loadAvaliacaoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao loadAvaliacaoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Avaliacao[] avaliacaos = listAvaliacaoByQuery(session, condition, orderBy);
		if (avaliacaos != null && avaliacaos.length > 0)
			return avaliacaos[0];
		else
			return null;
	}
	
	public static Avaliacao loadAvaliacaoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Avaliacao[] avaliacaos = listAvaliacaoByQuery(session, condition, orderBy, lockMode);
		if (avaliacaos != null && avaliacaos.length > 0)
			return avaliacaos[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateAvaliacaoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return iterateAvaliacaoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateAvaliacaoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return iterateAvaliacaoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateAvaliacaoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_classes.Avaliacao as Avaliacao");
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
	
	public static java.util.Iterator iterateAvaliacaoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_classes.Avaliacao as Avaliacao");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Avaliacao", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao createAvaliacao() {
		return new diagrama_classes.Avaliacao();
	}
	
	public static boolean save(diagrama_classes.Avaliacao avaliacao) throws PersistentException {
		try {
			AASIPersistentManager.instance().saveObject(avaliacao);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(diagrama_classes.Avaliacao avaliacao) throws PersistentException {
		try {
			AASIPersistentManager.instance().deleteObject(avaliacao);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(diagrama_classes.Avaliacao avaliacao) throws PersistentException {
		try {
			AASIPersistentManager.instance().getSession().refresh(avaliacao);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(diagrama_classes.Avaliacao avaliacao) throws PersistentException {
		try {
			AASIPersistentManager.instance().getSession().evict(avaliacao);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao loadAvaliacaoByCriteria(AvaliacaoCriteria avaliacaoCriteria) {
		Avaliacao[] avaliacaos = listAvaliacaoByCriteria(avaliacaoCriteria);
		if(avaliacaos == null || avaliacaos.length == 0) {
			return null;
		}
		return avaliacaos[0];
	}
	
	public static Avaliacao[] listAvaliacaoByCriteria(AvaliacaoCriteria avaliacaoCriteria) {
		return avaliacaoCriteria.listAvaliacao();
	}
}
