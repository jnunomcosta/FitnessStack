package ft.backend.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Categoria")
public class Categoria implements Serializable {
	public Categoria() {
	}

    @Column(name="ID", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="CATEGORIA_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="CATEGORIA_ID_GENERATOR", strategy="native")	
	private int ID;
	
	@Column(name="Categoria", nullable=false, length=255)	
	private String categoria;
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setCategoria(String value) {
		this.categoria = value;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
}
