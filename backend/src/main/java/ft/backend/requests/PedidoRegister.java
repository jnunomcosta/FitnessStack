package ft.backend.requests;

import java.io.Serializable;

public class PedidoRegister implements Serializable{
    
    private String email;
    private String password;
    private String nome;
    private int idade;
    private float peso;
    private float altura;
    private boolean genero;

    public PedidoRegister() {
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
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
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
