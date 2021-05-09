package ft.backend.responses;

import java.io.Serializable;
import java.util.Date;

public class RespostaAgenda implements Serializable{

    private Date data;
    private Date hora;
    private int id_treino;

    public RespostaAgenda(){
        
    }

    public int getId_treino() {
        return id_treino;
    }

    public void setId_treino(int id_treino) {
        this.id_treino = id_treino;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    
}
