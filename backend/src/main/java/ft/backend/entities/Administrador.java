package ft.backend.entities;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Administrador")
public class Administrador implements Serializable {
	public Administrador() {
	}
	
	@Column(name="ID", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="ADMINISTRADOR_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="ADMINISTRADOR_ID_GENERATOR", strategy="native")	
	private int ID;
	
	@Column(name="Email", nullable=true, length=1024)	
	private String email;

	@Column(name="Username", nullable=true, length=1024)
	private String username;

	@Column(name="Nome", nullable=true, length=1024)
	private String nome;
	
	@Column(name="Password", nullable=true, length=1024)	
	private String password;
	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
