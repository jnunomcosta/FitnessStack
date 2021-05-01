package ft.backend.requests;

import java.io.Serializable;

public class PedidoLogin implements Serializable{
    
    private String email;
    private String password;

    public PedidoLogin(){
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }
    
}
