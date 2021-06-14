package ft.backend.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="utilizador")
public class Utilizador implements Serializable {
	public Utilizador() {
	}
	
	@Column(name="ID", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="UTILIZADOR_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="UTILIZADOR_ID_GENERATOR", strategy="native")	
	private int ID;
	
	@Column(name="Email", nullable=false, length=1024)	
	private String email;
	
	@Column(name="Password", nullable=false, length=1024)	
	private String password;

	@Column(name="DataNascimento", nullable=true)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date data_nascimento;
	
	@Column(name="Peso", nullable=true)	
	private float peso;

	@Column(name="MassaGorda", nullable=true)	
	private float m_gorda;

	@Column(name="MassaMuscular", nullable=true)	
	private float m_muscular;
	
	@Column(name="Nome", nullable=true, length=1024)	
	private String nome;
	
	@Column(name="Altura", nullable=true)	
	private float altura;
	
	@Column(name="Genero", nullable=true, length=1)	
	private boolean genero;

	@Column(name="Username", nullable=false, length=512)	
	private String username;

	@OneToMany(targetEntity=Marcacao.class, fetch=FetchType.LAZY, cascade = {CascadeType.ALL})	
	@JoinColumns({ @JoinColumn(name="UtilizadorID", nullable=true) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set<Marcacao> agenda = new java.util.HashSet<>();

	@OneToMany(targetEntity=InformacaoFisica.class, fetch=FetchType.LAZY, cascade = {CascadeType.ALL})	
	@JoinColumns({ @JoinColumn(name="UtilizadorID3", nullable=true) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE) 	
	private java.util.List<InformacaoFisica> informacao_fisica = new java.util.ArrayList<>();

	@ManyToOne(targetEntity=ConteudoMedia.class, fetch=FetchType.LAZY, cascade = {CascadeType.ALL})	
	@JoinColumns(value={ @JoinColumn(name="ConteudoMediaID", referencedColumnName="ID", nullable=true) })	
	private ConteudoMedia foto_perfil;

	@ManyToMany(mappedBy="ORM_utilizadores", targetEntity=Treino.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private java.util.Set<Treino> treinos_favoritos = new java.util.HashSet<>();
	
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

	public void setORM_Agenda(java.util.Set<Marcacao> value) {
		this.agenda = value;
	}
	
	public java.util.Set<Marcacao> getORM_Agenda() {
		return agenda;
	}

	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return username;
	}

	public void setORM_Treinos_favoritos(java.util.Set<Treino> value) {
		this.treinos_favoritos = value;
	}
	
	public java.util.Set<Treino> getORM_Treinos_favoritos() {
		return treinos_favoritos;
	}

	public ConteudoMedia getFoto_perfil() {
		return foto_perfil;
	}

	public void setFoto_perfil(ConteudoMedia foto_perfil) {
		this.foto_perfil = foto_perfil;
	}

	public float getMassaGorda() {
		return m_gorda;
	}

	public void setMassaGorda(float m_gorda) {
		this.m_gorda = m_gorda;
	}

	public float getMassaMuscular() {
		return m_muscular;
	}

	public void setMassaMuscular(float m_muscular) {
		this.m_muscular = m_muscular;
	}

	public java.util.List<InformacaoFisica> getInformacao_fisica() {
		return informacao_fisica;
	}

	public void setInformacao_fisica(java.util.List<InformacaoFisica> informacao_fisica) {
		this.informacao_fisica = informacao_fisica;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
