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

public class ExercicioCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression criador_exercicioId;
	public final AssociationExpression criador_exercicio;
	public final StringExpression nome;
	public final FloatExpression duracao_media;
	public final StringExpression material_necessario;
	public final StringExpression descricao;
	public final ByteArrayExpression conteudo_media;
	
	public ExercicioCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		criador_exercicioId = new IntegerExpression("criador_exercicio.ID", this);
		criador_exercicio = new AssociationExpression("criador_exercicio", this);
		nome = new StringExpression("nome", this);
		duracao_media = new FloatExpression("duracao_media", this);
		material_necessario = new StringExpression("material_necessario", this);
		descricao = new StringExpression("descricao", this);
		conteudo_media = new ByteArrayExpression("conteudo_media", this);
	}
	
	public ExercicioCriteria(PersistentSession session) {
		this(session.createCriteria(Exercicio.class));
	}
	
	public ExercicioCriteria() throws PersistentException {
		this(AASIPersistentManager.instance().getSession());
	}
	
	public TreinadorCriteria createCriador_exercicioCriteria() {
		return new TreinadorCriteria(createCriteria("criador_exercicio"));
	}
	
	public Exercicio uniqueExercicio() {
		return (Exercicio) super.uniqueResult();
	}
	
	public Exercicio[] listExercicio() {
		java.util.List list = super.list();
		return (Exercicio[]) list.toArray(new Exercicio[list.size()]);
	}
}

