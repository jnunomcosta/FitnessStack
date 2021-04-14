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

public class TreinadorDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final StringExpression email;
	public final StringExpression password;
	public final IntegerExpression idade;
	public final FloatExpression peso;
	public final StringExpression nome;
	public final FloatExpression altura;
	public final BooleanExpression genero;
	public final CollectionExpression avaliacoes_t;
	
	public TreinadorDetachedCriteria() {
		super(diagrama_classes.Treinador.class, diagrama_classes.TreinadorCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
		idade = new IntegerExpression("idade", this.getDetachedCriteria());
		peso = new FloatExpression("peso", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
		altura = new FloatExpression("altura", this.getDetachedCriteria());
		genero = new BooleanExpression("genero", this.getDetachedCriteria());
		avaliacoes_t = new CollectionExpression("ORM_avaliacoes_t", this.getDetachedCriteria());
	}
	
	public TreinadorDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, diagrama_classes.TreinadorCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
		idade = new IntegerExpression("idade", this.getDetachedCriteria());
		peso = new FloatExpression("peso", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
		altura = new FloatExpression("altura", this.getDetachedCriteria());
		genero = new BooleanExpression("genero", this.getDetachedCriteria());
		avaliacoes_t = new CollectionExpression("ORM_avaliacoes_t", this.getDetachedCriteria());
	}
	
	public AvaliacaoDetachedCriteria createAvaliacoes_tCriteria() {
		return new AvaliacaoDetachedCriteria(createCriteria("ORM_avaliacoes_t"));
	}
	
	public Treinador uniqueTreinador(PersistentSession session) {
		return (Treinador) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Treinador[] listTreinador(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Treinador[]) list.toArray(new Treinador[list.size()]);
	}
}

