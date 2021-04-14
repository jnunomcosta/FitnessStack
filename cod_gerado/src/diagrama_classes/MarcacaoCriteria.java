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

public class MarcacaoCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression treinoId;
	public final AssociationExpression treino;
	public final DateExpression data_hora;
	
	public MarcacaoCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		treinoId = new IntegerExpression("treino.ID", this);
		treino = new AssociationExpression("treino", this);
		data_hora = new DateExpression("data_hora", this);
	}
	
	public MarcacaoCriteria(PersistentSession session) {
		this(session.createCriteria(Marcacao.class));
	}
	
	public MarcacaoCriteria() throws PersistentException {
		this(AASIPersistentManager.instance().getSession());
	}
	
	public TreinoCriteria createTreinoCriteria() {
		return new TreinoCriteria(createCriteria("treino"));
	}
	
	public Marcacao uniqueMarcacao() {
		return (Marcacao) super.uniqueResult();
	}
	
	public Marcacao[] listMarcacao() {
		java.util.List list = super.list();
		return (Marcacao[]) list.toArray(new Marcacao[list.size()]);
	}
}

