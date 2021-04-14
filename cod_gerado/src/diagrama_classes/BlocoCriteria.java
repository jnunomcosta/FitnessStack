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

public class BlocoCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression exercicioId;
	public final AssociationExpression exercicio;
	public final FloatExpression descanso;
	public final IntegerExpression series;
	public final IntegerExpression duracao;
	
	public BlocoCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		exercicioId = new IntegerExpression("exercicio.ID", this);
		exercicio = new AssociationExpression("exercicio", this);
		descanso = new FloatExpression("descanso", this);
		series = new IntegerExpression("series", this);
		duracao = new IntegerExpression("duracao", this);
	}
	
	public BlocoCriteria(PersistentSession session) {
		this(session.createCriteria(Bloco.class));
	}
	
	public BlocoCriteria() throws PersistentException {
		this(AASIPersistentManager.instance().getSession());
	}
	
	public ExercicioCriteria createExercicioCriteria() {
		return new ExercicioCriteria(createCriteria("exercicio"));
	}
	
	public Bloco uniqueBloco() {
		return (Bloco) super.uniqueResult();
	}
	
	public Bloco[] listBloco() {
		java.util.List list = super.list();
		return (Bloco[]) list.toArray(new Bloco[list.size()]);
	}
}

