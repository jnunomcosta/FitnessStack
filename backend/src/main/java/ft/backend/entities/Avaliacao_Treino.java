package ft.backend.entities;

import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Avaliacao_Treino")
public class Avaliacao_Treino implements Serializable {
	public Avaliacao_Treino() {
	}
	
	@Column(name="ID", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="AVALIACAO_TREINO_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="AVALIACAO_TREINO_ID_GENERATOR", strategy="native")	
	private int ID;
	
	@Column(name="Classificacao", nullable=false)	
	private float classificacao;
	
	@Column(name="Comentario", nullable=true, length=2048)	
	private String comentario;
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setClassificacao(float value) {
		this.classificacao = value;
	}
	
	public float getClassificacao() {
		return classificacao;
	}
	
	public void setComentario(String value) {
		this.comentario = value;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
