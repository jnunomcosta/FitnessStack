package ft.backend.beans;

import java.text.SimpleDateFormat;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ft.backend.repositories.*;
import java.text.SimpleDateFormat;
import ft.backend.entities.*;

@Service
public class gestao_marcacoes {
    
    @Autowired
    UtilizadorDAO uDao;
    @Autowired
    MarcacaoDAO mDao;
    @Autowired
    TreinoDAO tDAO;

    public JSONArray getAgendaUtilizador(String username){
        Utilizador u = uDao.findUtilizador_Username(username);
        JSONArray ret = new JSONArray();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        Set<Marcacao> arr = u.getORM_Agenda();
        for(Marcacao m : arr){
            JSONObject ra = new JSONObject();

            ra.put("name",m.getTreino().getCodigo() + " - " + m.getTreino().getNome());

            Calendar cal = Calendar.getInstance(),cal2 = Calendar.getInstance();
            cal.setTime(m.getData());
            cal2.setTime(m.getHora());
            cal.set(Calendar.HOUR,cal2.get(Calendar.HOUR));
            cal.set(Calendar.MINUTE,cal2.get(Calendar.MINUTE));

            ra.put("start",sdf.format(cal.getTime()));

            cal.set(Calendar.HOUR,cal.get(Calendar.HOUR)+1);

            ra.put("end",sdf.format(cal.getTime()));

            ra.put("color",m.getColor());

            ret.put(ra);
        }
        return ret;
    }

    public boolean novaMarcacao(Date m,String username,String id_treino, String cor){
        Utilizador u = uDao.findUtilizador_Username(username);
        Treino t = tDAO.findbyCodigo(id_treino);
        Marcacao mar = new Marcacao();
        mar.setData(m);
        mar.setHora(m);
        mar.setTreino(t);
        mar.setColor(cor);
        u.getORM_Agenda().add(mar);
        uDao.save(u);
        return true;
    }

}
