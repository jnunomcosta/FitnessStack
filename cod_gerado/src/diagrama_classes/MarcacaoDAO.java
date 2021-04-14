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

public class MarcacaoDAO {
	public static Marcacao loadMarcacaoByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return loadMarcacaoByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Marcacao getMarcacaoByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return getMarcacaoByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Marcacao loadMarcacaoByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return loadMarcacaoByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Marcacao getMarcacaoByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return getMarcacaoByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Marcacao loadMarcacaoByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Marcacao) session.load(diagrama_classes.Marcacao.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Marcacao getMarcacaoByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Marcacao) session.get(diagrama_classes.Marcacao.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Marcacao loadMarcacaoByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Marcacao) session.load(diagrama_classes.Marcacao.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Marcacao getMarcacaoByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Marcacao) session.get(diagrama_classes.Marcacao.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMarcacao(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return queryMarcacao(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMarcacao(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return queryMarcacao(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Marcacao[] listMarcacaoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return listMarcacaoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Marcacao[] listMarcacaoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return listMarcacaoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMarcacao(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_classes.Marcacao as Marcacao");
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
	
	public static List queryMarcacao(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_classes.Marcacao as Marcacao");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Marcacao", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Marcacao[] listMarcacaoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryMarcacao(session, condition, orderBy);
			return (Marcacao[]) list.toArray(new Marcacao[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Marcacao[] listMarcacaoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryMarcacao(session, condition, orderBy, lockMode);
			return (Marcacao[]) list.toArray(new Marcacao[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Marcacao loadMarcacaoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return loadMarcacaoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Marcacao loadMarcacaoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return loadMarcacaoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Marcacao loadMarcacaoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Marcacao[] marcacaos = listMarcacaoByQuery(session, condition, orderBy);
		if (marcacaos != null && marcacaos.length > 0)
			return marcacaos[0];
		else
			return null;
	}
	
	public static Marcacao loadMarcacaoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Marcacao[] marcacaos = listMarcacaoByQuery(session, condition, orderBy, lockMode);
		if (marcacaos != null && marcacaos.length > 0)
			return marcacaos[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateMarcacaoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return iterateMarcacaoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateMarcacaoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASIPersistentManager.instance().getSession();
			return iterateMarcacaoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateMarcacaoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_classes.Marcacao as Marcacao");
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
	
	public static java.util.Iterator iterateMarcacaoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_classes.Marcacao as Marcacao");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Marcacao", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Marcacao createMarcacao() {
		return new diagrama_classes.Marcacao();
	}
	
	public static boolean save(diagrama_classes.Marcacao marcacao) throws PersistentException {
		try {
			AASIPersistentManager.instance().saveObject(marcacao);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(diagrama_classes.Marcacao marcacao) throws PersistentException {
		try {
			AASIPersistentManager.instance().deleteObject(marcacao);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(diagrama_classes.Marcacao marcacao) throws PersistentException {
		try {
			AASIPersistentManager.instance().getSession().refresh(marcacao);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(diagrama_classes.Marcacao marcacao) throws PersistentException {
		try {
			AASIPersistentManager.instance().getSession().evict(marcacao);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Marcacao loadMarcacaoByCriteria(MarcacaoCriteria marcacaoCriteria) {
		Marcacao[] marcacaos = listMarcacaoByCriteria(marcacaoCriteria);
		if(marcacaos == null || marcacaos.length == 0) {
			return null;
		}
		return marcacaos[0];
	}
	
	public static Marcacao[] listMarcacaoByCriteria(MarcacaoCriteria marcacaoCriteria) {
		return marcacaoCriteria.listMarcacao();
	}
}
