package ft.backend.entities;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Treinador")
public class Treinador implements Serializable {
	public Treinador() {
	}
	
	@Column(name="ID", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="TREINADOR_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="TREINADOR_ID_GENERATOR", strategy="native")	
	private int ID;
	
	@Column(name="Email", nullable=true, length=1024)	
	private String email;
	
	@Column(name="Password", nullable=true, length=1024)	
	private String password;
	
	//@Column(name="Idade", nullable=false, length=10)	
	//private int idade;

	@Column(name="DataNascimento", nullable=true)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date data_nascimento;
	
	@Column(name="Peso", nullable=false)	
	private float peso;
	
	@Column(name="Nome", nullable=true, length=1024)	
	private String nome;
	
	@Column(name="Altura", nullable=false)	
	private float altura;
	
	@Column(name="Genero", nullable=false, length=1)	
	private boolean genero;

	@Column(name="Username", nullable=true, length=255)	
	private String username;
	
	@OneToMany(targetEntity=Avaliacao_Treinador.class, cascade = {CascadeType.ALL})	
	//@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="TreinadorID", nullable=true) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set<Avaliacao_Treinador> ORM_avaliacoes_t = new java.util.HashSet();
	
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

	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setORM_Avaliacoes_t(java.util.Set<Avaliacao_Treinador> value) {
		this.ORM_avaliacoes_t = value;
	}
	
	public java.util.Set<Avaliacao_Treinador> getORM_Avaliacoes_t() {
		return ORM_avaliacoes_t;
	}
	
	/* @Transient	
	public final AvaliacaoSetCollection avaliacoes_t = new AvaliacaoSetCollection(this, _ormAdapter, ORMConstants.KEY_TREINADOR_AVALIACOES_T, ORMConstants.KEY_MUL_ONE_TO_MANY);
	 */
	public String toString() {
		return String.valueOf(getID());
	} 
	
}
