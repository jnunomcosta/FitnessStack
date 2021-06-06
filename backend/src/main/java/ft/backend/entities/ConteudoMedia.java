package ft.backend.entities;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="ConteudoMedia")
public class ConteudoMedia implements Serializable{

    public ConteudoMedia() {
	}
	/*
	@Column(name="ID", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="CONTEUDOMEDIA_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="CONTEUDOMEDIA_ID_GENERATOR", strategy="native")	
	private int ID;*/

	@Column(name="ID", nullable=false, length=32)
	@Id
	private String ID;

	@Column(name="Conteudo", nullable=false, columnDefinition = "LONGBLOB")
	private byte[] Conteudo;

	@Column(name="Extensao", nullable=false)	
	private boolean Extensao;
	
	public void setID(String value) {
		this.ID = value;
	}
	
	public String getID() {
		return ID;
	}

	public void setConteudo(byte[] value) {
		this.Conteudo = value;
	}
	
	public byte[] getConteudo() {
		return Conteudo;
	}

	public boolean getExtensao() {
		return Extensao;
	}

	public void setExtensao(Boolean extensao) {
		this.Extensao = extensao;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
}
