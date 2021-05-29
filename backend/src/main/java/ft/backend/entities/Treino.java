package ft.backend.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Treino")
public class Treino implements Serializable {
	public Treino() {
	}
	
	@Column(name="ID", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="TREINO_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="TREINO_ID_GENERATOR", strategy="native")	
	private int ID;
	
	@ManyToOne(targetEntity=Treinador.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="TreinadorID", referencedColumnName="ID", nullable = true) }, foreignKey=@ForeignKey(name="FKTreino416635"))	
	private Treinador criador_t;
	
	@ManyToOne(targetEntity=Utilizador.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="UtilizadorID", referencedColumnName="ID", nullable = true) }, foreignKey=@ForeignKey(name="FKTreino370037"))	
	private Utilizador criador_u;
	
	@Column(name="Nome", nullable=true, length=1024)	
	private String nome;
	
	@Column(name="Duracao", nullable=true)	
	private float duracao;

	@Column(name="Dificuldade", nullable=true, length=512)	
	private String dificuldade;
	
	@Column(name="Codigo", nullable=true, length=512)	
	private String codigo;
	
	@Column(name="Descricao", nullable=true, length=2048)	
	private String descricao;
	
	@Column(name="Data_criacao", nullable=true)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date data_criacao;
	
	@OneToMany(targetEntity=Bloco.class, fetch=FetchType.LAZY, cascade = {CascadeType.ALL})	
	@JoinColumns({ @JoinColumn(name="TreinoID", nullable=true) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set<Bloco> ORM_blocos_exercicios = new java.util.HashSet<>();
	
	@OneToMany(targetEntity=Avaliacao_Treino.class, fetch=FetchType.LAZY, cascade = {CascadeType.ALL})	
	@JoinColumns({ @JoinColumn(name="TreinoID", nullable=true) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set<Avaliacao_Treino> ORM_avaliacoes_treino = new java.util.HashSet<>();

	@OneToMany(targetEntity=Categoria.class, fetch=FetchType.LAZY, cascade = {CascadeType.ALL})	
	//@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="TreinoID", nullable=true) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set<Categoria> ORM_categorias = new java.util.HashSet<>();

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
	
	public void setDuracao(float value) {
		this.duracao = value;
	}
	
	public float getDuracao() {
		return duracao;
	}
		
	public void setDificuldade(String value) {
		this.dificuldade = value;
	}
	
	public String getDificuldade() {
		return dificuldade;
	}
	
	public void setCodigo(String value) {
		this.codigo = value;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setDescricao(String value) {
		this.descricao = value;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setData_criacao(java.util.Date value) {
		this.data_criacao = value;
	}
	
	public java.util.Date getData_criacao() {
		return data_criacao;
	}
	
	public void setORM_Blocos_exercicios(java.util.Set<Bloco> value) {
		this.ORM_blocos_exercicios = value;
	}
	
	public java.util.Set<Bloco> getORM_Blocos_exercicios() {
		return ORM_blocos_exercicios;
	}
	
	public void setORM_Avaliacoes_treino(java.util.Set<Avaliacao_Treino> value) {
		this.ORM_avaliacoes_treino = value;
	}
	
	public java.util.Set<Avaliacao_Treino> getORM_Avaliacoes_treino() {
		return ORM_avaliacoes_treino;
	}

	public void setCategorias(java.util.Set<Categoria> value) {
		this.ORM_categorias = value;
	}
	
	public java.util.Set<Categoria> getCategorias() {
		return ORM_categorias;
	}
	
	public void setCriador_u(Utilizador value) {
		this.criador_u = value;
	}
	
	public Utilizador getCriador_u() {
		return criador_u;
	}
	
	public void setCriador_t(Treinador value) {
		this.criador_t = value;
	}
	
	public Treinador getCriador_t() {
		return criador_t;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
