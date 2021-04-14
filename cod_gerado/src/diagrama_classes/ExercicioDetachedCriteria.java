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

public class ExercicioDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression criador_exercicioId;
	public final AssociationExpression criador_exercicio;
	public final StringExpression nome;
	public final FloatExpression duracao_media;
	public final StringExpression material_necessario;
	public final StringExpression descricao;
	public final ByteArrayExpression conteudo_media;
	
	public ExercicioDetachedCriteria() {
		super(diagrama_classes.Exercicio.class, diagrama_classes.ExercicioCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		criador_exercicioId = new IntegerExpression("criador_exercicio.ID", this.getDetachedCriteria());
		criador_exercicio = new AssociationExpression("criador_exercicio", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
		duracao_media = new FloatExpression("duracao_media", this.getDetachedCriteria());
		material_necessario = new StringExpression("material_necessario", this.getDetachedCriteria());
		descricao = new StringExpression("descricao", this.getDetachedCriteria());
		conteudo_media = new ByteArrayExpression("conteudo_media", this.getDetachedCriteria());
	}
	
	public ExercicioDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, diagrama_classes.ExercicioCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		criador_exercicioId = new IntegerExpression("criador_exercicio.ID", this.getDetachedCriteria());
		criador_exercicio = new AssociationExpression("criador_exercicio", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
		duracao_media = new FloatExpression("duracao_media", this.getDetachedCriteria());
		material_necessario = new StringExpression("material_necessario", this.getDetachedCriteria());
		descricao = new StringExpression("descricao", this.getDetachedCriteria());
		conteudo_media = new ByteArrayExpression("conteudo_media", this.getDetachedCriteria());
	}
	
	public TreinadorDetachedCriteria createCriador_exercicioCriteria() {
		return new TreinadorDetachedCriteria(createCriteria("criador_exercicio"));
	}
	
	public Exercicio uniqueExercicio(PersistentSession session) {
		return (Exercicio) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Exercicio[] listExercicio(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Exercicio[]) list.toArray(new Exercicio[list.size()]);
	}
}

