package ft.backend.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Contrato")
public class Contrato implements Serializable {
	public Contrato() {
	}

    @Column(name="ID", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="CONTRATO_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="CONTRATO_ID_GENERATOR", strategy="native")	
	private int ID;

	@ManyToOne(targetEntity=Treinador.class, fetch=FetchType.LAZY)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="TreinadorID", referencedColumnName="ID", nullable=false) })		
	private Treinador treinador_responsavel;

	@ManyToOne(targetEntity=Utilizador.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="UtilizadorID", referencedColumnName="ID", nullable=false) })	
	private Utilizador utilizador;

	@Column(name="Comentario", nullable=false, length=255)	
	private String comentario;
		
	@Column(name="Estado", nullable=false, length=1)	
	private boolean estado;
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}

	
	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Treinador getTreinador_responsavel() {
		return treinador_responsavel;
	}

	public void setTreinador_responsavel(Treinador treinador_responsavel) {
		this.treinador_responsavel = treinador_responsavel;
	}
	public String toString() {
		return String.valueOf(getID());
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	
}
