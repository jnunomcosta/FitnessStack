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
@Table(name="Avaliacao")
public class Avaliacao implements Serializable {
	public Avaliacao() {
	}
	
	@Column(name="ID", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="AVALIACAO_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="AVALIACAO_ID_GENERATOR", strategy="native")	
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
