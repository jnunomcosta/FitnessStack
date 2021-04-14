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

public class BlocoDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression exercicioId;
	public final AssociationExpression exercicio;
	public final FloatExpression descanso;
	public final IntegerExpression series;
	public final IntegerExpression duracao;
	
	public BlocoDetachedCriteria() {
		super(diagrama_classes.Bloco.class, diagrama_classes.BlocoCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		exercicioId = new IntegerExpression("exercicio.ID", this.getDetachedCriteria());
		exercicio = new AssociationExpression("exercicio", this.getDetachedCriteria());
		descanso = new FloatExpression("descanso", this.getDetachedCriteria());
		series = new IntegerExpression("series", this.getDetachedCriteria());
		duracao = new IntegerExpression("duracao", this.getDetachedCriteria());
	}
	
	public BlocoDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, diagrama_classes.BlocoCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		exercicioId = new IntegerExpression("exercicio.ID", this.getDetachedCriteria());
		exercicio = new AssociationExpression("exercicio", this.getDetachedCriteria());
		descanso = new FloatExpression("descanso", this.getDetachedCriteria());
		series = new IntegerExpression("series", this.getDetachedCriteria());
		duracao = new IntegerExpression("duracao", this.getDetachedCriteria());
	}
	
	public ExercicioDetachedCriteria createExercicioCriteria() {
		return new ExercicioDetachedCriteria(createCriteria("exercicio"));
	}
	
	public Bloco uniqueBloco(PersistentSession session) {
		return (Bloco) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Bloco[] listBloco(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Bloco[]) list.toArray(new Bloco[list.size()]);
	}
}

