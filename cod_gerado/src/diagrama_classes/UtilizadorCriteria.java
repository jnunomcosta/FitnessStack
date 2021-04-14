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

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class UtilizadorCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final StringExpression email;
	public final StringExpression password;
	public final IntegerExpression idade;
	public final FloatExpression peso;
	public final StringExpression nome;
	public final FloatExpression altura;
	public final BooleanExpression genero;
	public final CollectionExpression agenda;
	
	public UtilizadorCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		email = new StringExpression("email", this);
		password = new StringExpression("password", this);
		idade = new IntegerExpression("idade", this);
		peso = new FloatExpression("peso", this);
		nome = new StringExpression("nome", this);
		altura = new FloatExpression("altura", this);
		genero = new BooleanExpression("genero", this);
		agenda = new CollectionExpression("ORM_agenda", this);
	}
	
	public UtilizadorCriteria(PersistentSession session) {
		this(session.createCriteria(Utilizador.class));
	}
	
	public UtilizadorCriteria() throws PersistentException {
		this(AASIPersistentManager.instance().getSession());
	}
	
	public MarcacaoCriteria createAgendaCriteria() {
		return new MarcacaoCriteria(createCriteria("ORM_agenda"));
	}
	
	public Utilizador uniqueUtilizador() {
		return (Utilizador) super.uniqueResult();
	}
	
	public Utilizador[] listUtilizador() {
		java.util.List list = super.list();
		return (Utilizador[]) list.toArray(new Utilizador[list.size()]);
	}
}

