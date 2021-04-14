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
package diagrama_classes;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Treino")
public class Treino implements Serializable {
	public Treino() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_TREINO_BLOCOS_EXERCICIOS) {
			return ORM_blocos_exercicios;
		}
		else if (key == ORMConstants.KEY_TREINO_AVALIACOES_TREINO) {
			return ORM_avaliacoes_treino;
		}
		
		return null;
	}
	
	@Transient	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	@Column(name="ID", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="DIAGRAMA_CLASSES_TREINO_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="DIAGRAMA_CLASSES_TREINO_ID_GENERATOR", strategy="native")	
	private int ID;
	
	@ManyToOne(targetEntity=diagrama_classes.Treinador.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="TreinadorID", referencedColumnName="ID") }, foreignKey=@ForeignKey(name="FKTreino416635"))	
	private diagrama_classes.Treinador criador_t;
	
	@ManyToOne(targetEntity=diagrama_classes.Utilizador.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="UtilizadorID", referencedColumnName="ID") }, foreignKey=@ForeignKey(name="FKTreino370037"))	
	private diagrama_classes.Utilizador criador_u;
	
	@Column(name="Nome", nullable=true, length=1024)	
	private String nome;
	
	@Column(name="Duracao", nullable=false)	
	private float duracao;
	
	@Column(name="Categoria", nullable=true, length=512)	
	private String categoria;
	
	@Column(name="Dificuldade", nullable=true, length=512)	
	private String dificuldade;
	
	@Column(name="Codigo", nullable=true, length=512)	
	private String codigo;
	
	@Column(name="Descricao", nullable=true, length=2048)	
	private String descricao;
	
	@Column(name="Data_criacao", nullable=true)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date data_criacao;
	
	@OneToMany(targetEntity=diagrama_classes.Bloco.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="TreinoID", nullable=false) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_blocos_exercicios = new java.util.HashSet();
	
	@OneToMany(targetEntity=diagrama_classes.Avaliacao.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="TreinoID", nullable=false) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_avaliacoes_treino = new java.util.HashSet();
	
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
	
	public void setCategoria(String value) {
		this.categoria = value;
	}
	
	public String getCategoria() {
		return categoria;
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
	
	private void setORM_Blocos_exercicios(java.util.Set value) {
		this.ORM_blocos_exercicios = value;
	}
	
	private java.util.Set getORM_Blocos_exercicios() {
		return ORM_blocos_exercicios;
	}
	
	@Transient	
	public final diagrama_classes.BlocoSetCollection blocos_exercicios = new diagrama_classes.BlocoSetCollection(this, _ormAdapter, ORMConstants.KEY_TREINO_BLOCOS_EXERCICIOS, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Avaliacoes_treino(java.util.Set value) {
		this.ORM_avaliacoes_treino = value;
	}
	
	private java.util.Set getORM_Avaliacoes_treino() {
		return ORM_avaliacoes_treino;
	}
	
	@Transient	
	public final diagrama_classes.AvaliacaoSetCollection avaliacoes_treino = new diagrama_classes.AvaliacaoSetCollection(this, _ormAdapter, ORMConstants.KEY_TREINO_AVALIACOES_TREINO, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public void setCriador_u(diagrama_classes.Utilizador value) {
		this.criador_u = value;
	}
	
	public diagrama_classes.Utilizador getCriador_u() {
		return criador_u;
	}
	
	public void setCriador_t(diagrama_classes.Treinador value) {
		this.criador_t = value;
	}
	
	public diagrama_classes.Treinador getCriador_t() {
		return criador_t;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
