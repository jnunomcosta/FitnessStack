package ft.backend.beans;

import ft.backend.utils.Authorization;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class gestao_verificacoes {

  @Autowired
  gestao_utilizadores gestao_utilizadores;
  @Autowired
  gestao_treinadores gestao_treinadores;
  @Autowired
  gestao_administradores gestao_administradores;

  public String verifyUser(String token) {
    try {
      JSONObject obj = new JSONObject(Authorization.extractClaims(token));
      int tipo = obj.getInt("tipo");
      String username_token = obj.getString("username");

      if (
        gestao_utilizadores.usernameExisteU(username_token) &&
        tipo == 0
      ) return username_token;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    
    return null;
  }

  public String verifyTreinador(String token) {
    try {
      JSONObject obj = new JSONObject(Authorization.extractClaims(token));
      int tipo = obj.getInt("tipo");
      String username_token = obj.getString("username");

      if (
        gestao_treinadores.usernameExisteT(username_token) &&
        tipo == 1
      ) return username_token;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

    return null;
  }

  public String verifyAdmin(String token) {
    try {
      JSONObject obj = new JSONObject(Authorization.extractClaims(token));
      int tipo = obj.getInt("tipo");
      String username_token = obj.getString("username");

      if (
        gestao_administradores.usernameExisteU(username_token) &&
        tipo == 2
      ) return username_token;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

    return null;
  }
}
