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
package ft.backend.entities;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Bloco")
public class Bloco implements Serializable {
	public Bloco() {
	}
	
	/* private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_BLOCO_EXERCICIO) {
			this.exercicio = (Exercicio) owner;
		}
	}
	
	@Transient	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	}; */
	
	@Column(name="ID", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="BLOCO_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="BLOCO_ID_GENERATOR", strategy="native")	
	private int ID;
	
	@ManyToOne(targetEntity=Exercicio.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="ExercicioID", referencedColumnName="ID", nullable=false) }, foreignKey=@ForeignKey(name="FKBloco735558"))	
	private Exercicio exercicio;
	
	@Column(name="Descanso", nullable=false)	
	private float descanso;
	
	@Column(name="Series", nullable=false, length=10)	
	private int series;
	
	@Column(name="Duracao", nullable=false, length=10)	
	private int duracao;
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setDescanso(float value) {
		this.descanso = value;
	}
	
	public float getDescanso() {
		return descanso;
	}
	
	public void setSeries(int value) {
		this.series = value;
	}
	
	public int getSeries() {
		return series;
	}
	
	public void setDuracao(int value) {
		this.duracao = value;
	}
	
	public int getDuracao() {
		return duracao;
	}
	
	public void setExercicio(Exercicio value) {
		this.exercicio = value;
	}
	
	public Exercicio getExercicio() {
		return exercicio;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
