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
@Table(name="Treinador")
public class Treinador implements Serializable {
	public Treinador() {
	}
	
	/* private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_TREINADOR_AVALIACOES_T) {
			return ORM_avaliacoes_t;
		}
		
		return null;
	} */
	
	/* @Transient	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	}; */
	
	@Column(name="ID", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="TREINADOR_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="TREINADOR_ID_GENERATOR", strategy="native")	
	private int ID;
	
	@Column(name="Email", nullable=true, length=1024)	
	private String email;
	
	@Column(name="Password", nullable=true, length=1024)	
	private String password;
	
	@Column(name="Idade", nullable=false, length=10)	
	private int idade;
	
	@Column(name="Peso", nullable=false)	
	private float peso;
	
	@Column(name="Nome", nullable=true, length=2048)	
	private String nome;
	
	@Column(name="Altura", nullable=false)	
	private float altura;
	
	@Column(name="Genero", nullable=false, length=1)	
	private boolean genero;
	
	@OneToMany(targetEntity=Avaliacao.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="TreinadorID", nullable=false) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_avaliacoes_t = new java.util.HashSet();
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setIdade(int value) {
		this.idade = value;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setPeso(float value) {
		this.peso = value;
	}
	
	public float getPeso() {
		return peso;
	}
	
	public void setNome(String value) {
		this.nome = value;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setAltura(float value) {
		this.altura = value;
	}
	
	public float getAltura() {
		return altura;
	}
	
	public void setGenero(boolean value) {
		this.genero = value;
	}
	
	public boolean getGenero() {
		return genero;
	}
	
	private void setORM_Avaliacoes_t(java.util.Set value) {
		this.ORM_avaliacoes_t = value;
	}
	
	private java.util.Set getORM_Avaliacoes_t() {
		return ORM_avaliacoes_t;
	}
	
	/* @Transient	
	public final AvaliacaoSetCollection avaliacoes_t = new AvaliacaoSetCollection(this, _ormAdapter, ORMConstants.KEY_TREINADOR_AVALIACOES_T, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getID());
	} */
	
}
