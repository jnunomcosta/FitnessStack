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

public class TreinoDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression criador_tId;
	public final AssociationExpression criador_t;
	public final IntegerExpression criador_uId;
	public final AssociationExpression criador_u;
	public final StringExpression nome;
	public final FloatExpression duracao;
	public final StringExpression categoria;
	public final StringExpression dificuldade;
	public final StringExpression codigo;
	public final StringExpression descricao;
	public final DateExpression data_criacao;
	public final CollectionExpression blocos_exercicios;
	public final CollectionExpression avaliacoes_treino;
	
	public TreinoDetachedCriteria() {
		super(diagrama_classes.Treino.class, diagrama_classes.TreinoCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		criador_tId = new IntegerExpression("criador_t.ID", this.getDetachedCriteria());
		criador_t = new AssociationExpression("criador_t", this.getDetachedCriteria());
		criador_uId = new IntegerExpression("criador_u.ID", this.getDetachedCriteria());
		criador_u = new AssociationExpression("criador_u", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
		duracao = new FloatExpression("duracao", this.getDetachedCriteria());
		categoria = new StringExpression("categoria", this.getDetachedCriteria());
		dificuldade = new StringExpression("dificuldade", this.getDetachedCriteria());
		codigo = new StringExpression("codigo", this.getDetachedCriteria());
		descricao = new StringExpression("descricao", this.getDetachedCriteria());
		data_criacao = new DateExpression("data_criacao", this.getDetachedCriteria());
		blocos_exercicios = new CollectionExpression("ORM_blocos_exercicios", this.getDetachedCriteria());
		avaliacoes_treino = new CollectionExpression("ORM_avaliacoes_treino", this.getDetachedCriteria());
	}
	
	public TreinoDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, diagrama_classes.TreinoCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		criador_tId = new IntegerExpression("criador_t.ID", this.getDetachedCriteria());
		criador_t = new AssociationExpression("criador_t", this.getDetachedCriteria());
		criador_uId = new IntegerExpression("criador_u.ID", this.getDetachedCriteria());
		criador_u = new AssociationExpression("criador_u", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
		duracao = new FloatExpression("duracao", this.getDetachedCriteria());
		categoria = new StringExpression("categoria", this.getDetachedCriteria());
		dificuldade = new StringExpression("dificuldade", this.getDetachedCriteria());
		codigo = new StringExpression("codigo", this.getDetachedCriteria());
		descricao = new StringExpression("descricao", this.getDetachedCriteria());
		data_criacao = new DateExpression("data_criacao", this.getDetachedCriteria());
		blocos_exercicios = new CollectionExpression("ORM_blocos_exercicios", this.getDetachedCriteria());
		avaliacoes_treino = new CollectionExpression("ORM_avaliacoes_treino", this.getDetachedCriteria());
	}
	
	public TreinadorDetachedCriteria createCriador_tCriteria() {
		return new TreinadorDetachedCriteria(createCriteria("criador_t"));
	}
	
	public UtilizadorDetachedCriteria createCriador_uCriteria() {
		return new UtilizadorDetachedCriteria(createCriteria("criador_u"));
	}
	
	public BlocoDetachedCriteria createBlocos_exerciciosCriteria() {
		return new BlocoDetachedCriteria(createCriteria("ORM_blocos_exercicios"));
	}
	
	public AvaliacaoDetachedCriteria createAvaliacoes_treinoCriteria() {
		return new AvaliacaoDetachedCriteria(createCriteria("ORM_avaliacoes_treino"));
	}
	
	public Treino uniqueTreino(PersistentSession session) {
		return (Treino) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Treino[] listTreino(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Treino[]) list.toArray(new Treino[list.size()]);
	}
}

