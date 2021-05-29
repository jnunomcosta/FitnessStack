package ft.backend.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="InformacaoFisica")
public class InformacaoFisica implements Serializable {

    public InformacaoFisica(){

    }

    @Column(name="ID", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="INFORMACAOFISICA_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="INFORMACAOFISICA_ID_GENERATOR", strategy="native")	
	private int ID;

	@Column(name="Peso", nullable=true)	
	private float peso;

    @Column(name="MassaGorda", nullable=true)	
	private float m_gorda;

    @Column(name="MassaMuscular", nullable=true)	
	private float m_muscular;

    @Column(name="Data", nullable=true)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date data;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getM_gorda() {
		return m_gorda;
	}

	public void setM_gorda(float m_gorda) {
		this.m_gorda = m_gorda;
	}

	public float getM_muscular() {
		return m_muscular;
	}

	public void setM_muscular(float m_muscular) {
		this.m_muscular = m_muscular;
	}

	public java.util.Date getData() {
		return data;
	}

	public void setData(java.util.Date data) {
		this.data = data;
	}
}
