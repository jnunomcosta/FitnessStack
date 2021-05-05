package ft.backend.requests;

import java.io.Serializable;

public class PedidoLogin implements Serializable{
    
    private String username;
    private String password;

    public PedidoLogin(){
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }
    
}
