package ft.backend.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Utilizador")
public class Utilizador implements Serializable {
	public Utilizador() {
	}
	
	@Column(name="ID", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="UTILIZADOR_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="UTILIZADOR_ID_GENERATOR", strategy="native")	
	private int ID;
	
	@Column(name="Email", nullable=true, length=1024)	
	private String email;
	
	@Column(name="Password", nullable=true, length=1024)	
	private String password;
	
	//@Column(name="Idade", nullable=true, length=10)	
	//private int idade;

	@Column(name="DataNascimento", nullable=true)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date data_nascimento;
	
	@Column(name="Peso", nullable=true)	
	private float peso;
	
	@Column(name="Nome", nullable=true, length=1024)	
	private String nome;
	
	@Column(name="Altura", nullable=true)	
	private float altura;
	
	@Column(name="Genero", nullable=true, length=1)	
	private boolean genero;

	@Column(name="Username", nullable=true, length=512)	
	private String username;

	@OneToMany(targetEntity=Marcacao.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="UtilizadorID", nullable=true) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_agenda = new java.util.HashSet();
	
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
	
	public void setDataNascimento(java.util.Date value) {
		this.data_nascimento = value;
	}
	
	public java.util.Date getDataNascimento() {
		return this.data_nascimento;
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

	private void setORM_Agenda(java.util.Set value) {
		this.ORM_agenda = value;
	}
	
	private java.util.Set getORM_Agenda() {
		return ORM_agenda;
	}

	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
