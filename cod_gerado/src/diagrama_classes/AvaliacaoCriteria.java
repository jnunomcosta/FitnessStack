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

public class AvaliacaoCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final FloatExpression classificacao;
	public final StringExpression comentario;
	
	public AvaliacaoCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		classificacao = new FloatExpression("classificacao", this);
		comentario = new StringExpression("comentario", this);
	}
	
	public AvaliacaoCriteria(PersistentSession session) {
		this(session.createCriteria(Avaliacao.class));
	}
	
	public AvaliacaoCriteria() throws PersistentException {
		this(AASIPersistentManager.instance().getSession());
	}
	
	public Avaliacao uniqueAvaliacao() {
		return (Avaliacao) super.uniqueResult();
	}
	
	public Avaliacao[] listAvaliacao() {
		java.util.List list = super.list();
		return (Avaliacao[]) list.toArray(new Avaliacao[list.size()]);
	}
}

