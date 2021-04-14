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

public class TreinoCriteria extends AbstractORMCriteria {
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
	
	public TreinoCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		criador_tId = new IntegerExpression("criador_t.ID", this);
		criador_t = new AssociationExpression("criador_t", this);
		criador_uId = new IntegerExpression("criador_u.ID", this);
		criador_u = new AssociationExpression("criador_u", this);
		nome = new StringExpression("nome", this);
		duracao = new FloatExpression("duracao", this);
		categoria = new StringExpression("categoria", this);
		dificuldade = new StringExpression("dificuldade", this);
		codigo = new StringExpression("codigo", this);
		descricao = new StringExpression("descricao", this);
		data_criacao = new DateExpression("data_criacao", this);
		blocos_exercicios = new CollectionExpression("ORM_blocos_exercicios", this);
		avaliacoes_treino = new CollectionExpression("ORM_avaliacoes_treino", this);
	}
	
	public TreinoCriteria(PersistentSession session) {
		this(session.createCriteria(Treino.class));
	}
	
	public TreinoCriteria() throws PersistentException {
		this(AASIPersistentManager.instance().getSession());
	}
	
	public TreinadorCriteria createCriador_tCriteria() {
		return new TreinadorCriteria(createCriteria("criador_t"));
	}
	
	public UtilizadorCriteria createCriador_uCriteria() {
		return new UtilizadorCriteria(createCriteria("criador_u"));
	}
	
	public BlocoCriteria createBlocos_exerciciosCriteria() {
		return new BlocoCriteria(createCriteria("ORM_blocos_exercicios"));
	}
	
	public AvaliacaoCriteria createAvaliacoes_treinoCriteria() {
		return new AvaliacaoCriteria(createCriteria("ORM_avaliacoes_treino"));
	}
	
	public Treino uniqueTreino() {
		return (Treino) super.uniqueResult();
	}
	
	public Treino[] listTreino() {
		java.util.List list = super.list();
		return (Treino[]) list.toArray(new Treino[list.size()]);
	}
}

