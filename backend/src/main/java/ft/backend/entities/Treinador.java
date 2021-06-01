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
	
	@Column(name="Email", nullable=false, length=1024)	
	private String email;
	
	@Column(name="Password", nullable=false, length=1024)	
	private String password;

	@Column(name="DataNascimento", nullable=true)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date data_nascimento;
	
	@Column(name="Nome", nullable=true, length=1024)	
	private String nome;
	
	@Column(name="Genero", nullable=true, length=1)	
	private boolean genero;

	@Column(name="Username", nullable=false, length=255)	
	private String username;

	@Column(name="Descricao", nullable=true, length=2048)	
	private String descricao;
	
	@OneToMany(targetEntity=Avaliacao_Treinador.class, cascade = {CascadeType.ALL})	
	@JoinColumns({ @JoinColumn(name="TreinadorID", nullable=true) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set<Avaliacao_Treinador> ORM_avaliacoes_t = new java.util.HashSet<>();

	@ManyToOne(targetEntity=ConteudoMedia.class, fetch=FetchType.LAZY, cascade = {CascadeType.ALL})	
	@JoinColumns(value={ @JoinColumn(name="ConteudoMediaID", referencedColumnName="ID", nullable=true) })	
	private ConteudoMedia foto_perfil;

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
	
	public void setNome(String value) {
		this.nome = value;
	}
	
	public String getNome() {
		return nome;
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

	public void setDescricao(String value) {
		this.descricao = value;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setORM_Avaliacoes_t(java.util.Set<Avaliacao_Treinador> value) {
		this.ORM_avaliacoes_t = value;
	}
	
	public java.util.Set<Avaliacao_Treinador> getORM_Avaliacoes_t() {
		return ORM_avaliacoes_t;
	}

	public ConteudoMedia getFoto_perfil() {
		return foto_perfil;
	}

	public void setFoto_perfil(ConteudoMedia foto_perfil) {
		this.foto_perfil = foto_perfil;
	}

	public String toString() {
		return String.valueOf(getID());
	} 	
}