package ft.backend.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Marcacao")
public class Marcacao implements Serializable {
	public Marcacao() {
	}
	
	@Column(name="ID", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="MARCACAO_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="MARCACAO_ID_GENERATOR", strategy="native")	
	private int ID;
	
	@ManyToOne(targetEntity=Treino.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="TreinoID", referencedColumnName="ID", nullable=false) }, foreignKey=@ForeignKey(name="FKMarcacao343781"))	
	private Treino treino;
	
	@Column(name="Data", nullable=true)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date data;

	@Column(name="Hora", nullable=true)	
	@Temporal(TemporalType.TIME)	
	private java.util.Date hora;

	@Column(name="Cor", nullable=true)
	private String color;

	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setData(java.util.Date value) {
		this.data = value;
	}
	
	public java.util.Date getData() {
		return data;
	}

	public void setHora(java.util.Date value) {
		this.hora = value;
	}
	
	public java.util.Date getHora() {
		return hora;
	}
	
	public void setTreino(Treino value) {
		this.treino = value;
	}
	
	public Treino getTreino() {
		return treino;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String toString() {
		return String.valueOf(getID());
	}
	
}
