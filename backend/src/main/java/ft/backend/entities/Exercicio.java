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
@Table(name="Exercicio")
public class Exercicio implements Serializable {
	public Exercicio() {
	}
	
	@Column(name="ID", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="EXERCICIO_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="EXERCICIO_ID_GENERATOR", strategy="native")	
	private int ID;
	
	@ManyToOne(targetEntity=Treinador.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="TreinadorID", referencedColumnName="ID", nullable=false) }, foreignKey=@ForeignKey(name="FKExercicio354696"))	
	private Treinador criador_exercicio;
	
	@Column(name="Nome", nullable=true, length=512)	
	private String nome;
	
	@Column(name="Duracao_media", nullable=false)	
	private float duracao_media;
	
	@Column(name="Material_necessario", nullable=true, length=2048)	
	private String material_necessario;
	
	@Column(name="Descricao", nullable=true, length=2048)	
	private String descricao;
	
	@Column(name="Conteudo_media", nullable=true, columnDefinition = "LONGBLOB")	
	private byte[] conteudo_media;
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setNome(String value) {
		this.nome = value;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setDuracao_media(float value) {
		this.duracao_media = value;
	}
	
	public float getDuracao_media() {
		return duracao_media;
	}
	
	public void setMaterial_necessario(String value) {
		this.material_necessario = value;
	}
	
	public String getMaterial_necessario() {
		return material_necessario;
	}
	
	public void setDescricao(String value) {
		this.descricao = value;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setConteudo_media(byte[] value) {
		this.conteudo_media = value;
	}
	
	public byte[] getConteudo_media() {
		return conteudo_media;
	}
	
	public void setCriador_exercicio(Treinador value) {
		this.criador_exercicio = value;
	}
	
	public Treinador getCriador_exercicio() {
		return criador_exercicio;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
