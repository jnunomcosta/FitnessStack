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

public class MarcacaoDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression treinoId;
	public final AssociationExpression treino;
	public final DateExpression data_hora;
	
	public MarcacaoDetachedCriteria() {
		super(diagrama_classes.Marcacao.class, diagrama_classes.MarcacaoCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		treinoId = new IntegerExpression("treino.ID", this.getDetachedCriteria());
		treino = new AssociationExpression("treino", this.getDetachedCriteria());
		data_hora = new DateExpression("data_hora", this.getDetachedCriteria());
	}
	
	public MarcacaoDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, diagrama_classes.MarcacaoCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		treinoId = new IntegerExpression("treino.ID", this.getDetachedCriteria());
		treino = new AssociationExpression("treino", this.getDetachedCriteria());
		data_hora = new DateExpression("data_hora", this.getDetachedCriteria());
	}
	
	public TreinoDetachedCriteria createTreinoCriteria() {
		return new TreinoDetachedCriteria(createCriteria("treino"));
	}
	
	public Marcacao uniqueMarcacao(PersistentSession session) {
		return (Marcacao) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Marcacao[] listMarcacao(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Marcacao[]) list.toArray(new Marcacao[list.size()]);
	}
}

