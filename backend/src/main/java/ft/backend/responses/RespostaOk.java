package ft.backend.responses;

public class RespostaOk {

    private String estado;
    
    public RespostaOk(){
        estado = "OK";
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
