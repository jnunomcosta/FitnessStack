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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class AvaliacaoDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final FloatExpression classificacao;
	public final StringExpression comentario;
	
	public AvaliacaoDetachedCriteria() {
		super(diagrama_classes.Avaliacao.class, diagrama_classes.AvaliacaoCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		classificacao = new FloatExpression("classificacao", this.getDetachedCriteria());
		comentario = new StringExpression("comentario", this.getDetachedCriteria());
	}
	
	public AvaliacaoDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, diagrama_classes.AvaliacaoCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		classificacao = new FloatExpression("classificacao", this.getDetachedCriteria());
		comentario = new StringExpression("comentario", this.getDetachedCriteria());
	}
	
	public Avaliacao uniqueAvaliacao(PersistentSession session) {
		return (Avaliacao) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Avaliacao[] listAvaliacao(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Avaliacao[]) list.toArray(new Avaliacao[list.size()]);
	}
}

