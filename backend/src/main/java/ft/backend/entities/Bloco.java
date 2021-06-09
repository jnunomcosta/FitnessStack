package ft.backend.entities;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Bloco")
public class Bloco implements Serializable {
	public Bloco() {
	}
	
	@Column(name="ID", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="BLOCO_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="BLOCO_ID_GENERATOR", strategy="native")	
	private int ID;
	
	@ManyToOne(targetEntity=Exercicio.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="ExercicioID", referencedColumnName="ID", nullable=true) }, foreignKey=@ForeignKey(name="FKBloco735558"))
	private Exercicio exercicio;
	
	@Column(name="Descanso", nullable=true)	
	private float descanso;
	
	@Column(name="Series", nullable=true, length=10)	
	private int series;
	
	@Column(name="Duracao", nullable=true, length=10)	
	private int duracao;

	//True -> Repeticoes | False -> Duracao
	@Column(name="Tipo", nullable=true)
	private boolean tipo;
	
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

	public boolean isTipo() {
		return tipo;
	}

	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
