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

public class TreinadorCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final StringExpression email;
	public final StringExpression password;
	public final IntegerExpression idade;
	public final FloatExpression peso;
	public final StringExpression nome;
	public final FloatExpression altura;
	public final BooleanExpression genero;
	public final CollectionExpression avaliacoes_t;
	
	public TreinadorCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		email = new StringExpression("email", this);
		password = new StringExpression("password", this);
		idade = new IntegerExpression("idade", this);
		peso = new FloatExpression("peso", this);
		nome = new StringExpression("nome", this);
		altura = new FloatExpression("altura", this);
		genero = new BooleanExpression("genero", this);
		avaliacoes_t = new CollectionExpression("ORM_avaliacoes_t", this);
	}
	
	public TreinadorCriteria(PersistentSession session) {
		this(session.createCriteria(Treinador.class));
	}
	
	public TreinadorCriteria() throws PersistentException {
		this(AASIPersistentManager.instance().getSession());
	}
	
	public AvaliacaoCriteria createAvaliacoes_tCriteria() {
		return new AvaliacaoCriteria(createCriteria("ORM_avaliacoes_t"));
	}
	
	public Treinador uniqueTreinador() {
		return (Treinador) super.uniqueResult();
	}
	
	public Treinador[] listTreinador() {
		java.util.List list = super.list();
		return (Treinador[]) list.toArray(new Treinador[list.size()]);
	}
}

