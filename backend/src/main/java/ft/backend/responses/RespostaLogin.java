package ft.backend.responses;

public class RespostaLogin{

    private String token;
    
    public RespostaLogin(){

    }
    
    public String getToken(){
        return this.token;
    }

    public void setToken(String token){
        this.token=token;
    }
    
}
