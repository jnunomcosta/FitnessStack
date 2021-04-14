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

public class UtilizadorDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final StringExpression email;
	public final StringExpression password;
	public final IntegerExpression idade;
	public final FloatExpression peso;
	public final StringExpression nome;
	public final FloatExpression altura;
	public final BooleanExpression genero;
	public final CollectionExpression agenda;
	
	public UtilizadorDetachedCriteria() {
		super(diagrama_classes.Utilizador.class, diagrama_classes.UtilizadorCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
		idade = new IntegerExpression("idade", this.getDetachedCriteria());
		peso = new FloatExpression("peso", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
		altura = new FloatExpression("altura", this.getDetachedCriteria());
		genero = new BooleanExpression("genero", this.getDetachedCriteria());
		agenda = new CollectionExpression("ORM_agenda", this.getDetachedCriteria());
	}
	
	public UtilizadorDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, diagrama_classes.UtilizadorCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
		idade = new IntegerExpression("idade", this.getDetachedCriteria());
		peso = new FloatExpression("peso", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
		altura = new FloatExpression("altura", this.getDetachedCriteria());
		genero = new BooleanExpression("genero", this.getDetachedCriteria());
		agenda = new CollectionExpression("ORM_agenda", this.getDetachedCriteria());
	}
	
	public MarcacaoDetachedCriteria createAgendaCriteria() {
		return new MarcacaoDetachedCriteria(createCriteria("ORM_agenda"));
	}
	
	public Utilizador uniqueUtilizador(PersistentSession session) {
		return (Utilizador) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Utilizador[] listUtilizador(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Utilizador[]) list.toArray(new Utilizador[list.size()]);
	}
}

