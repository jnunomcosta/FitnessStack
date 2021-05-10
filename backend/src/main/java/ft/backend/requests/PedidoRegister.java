package ft.backend.requests;

import java.io.Serializable;

public class PedidoRegister implements Serializable{
    
    private String email;
    private String password;
    private String nome;
    private String nascimento;
    private float peso;
    private float altura;
    private boolean genero;
    private String username;

    public PedidoRegister() {
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public boolean getGenero() {
        return genero;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDataNascimento() {
        return this.nascimento;
    }
    public void setDataNascimento(String data) {
        this.nascimento = data;
    }
    public float getPeso() {
        return peso;
    }
    public void setPeso(float peso) {
        this.peso = peso;
    }
    public float getAltura() {
        return altura;
    }
    public void setAltura(float altura) {
        this.altura = altura;
    }
    public void setGenero(boolean genero) {
        this.genero = genero;
    }

}
