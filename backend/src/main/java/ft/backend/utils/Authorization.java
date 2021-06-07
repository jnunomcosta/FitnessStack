package ft.backend.utils;

import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.json.JSONObject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Authorization {

    private static String secret = "augustooooo";
    private static long duration = 1000 * 60 * 60 * 10; //10 hrs

    //tipo -> 0 = utilizador | 1 = treinador | 2 = administrador FALTA ISSO
    public static String generateToken(String data,boolean tipo){
        JSONObject json = new JSONObject();
        json.put("treinador_utilizador", tipo);
        json.put("username",data);
        return Jwts.builder()
                .setSubject(json.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + duration))
                .signWith(SignatureAlgorithm.HS256,secret).compact(); 
    }

    public static String extractClaims(String jwt) throws Exception {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                    .parseClaimsJws(jwt).getBody();
            if(claims.getExpiration().before(new Date())){
                throw new Exception();
            }    
            return claims.getSubject();
    }

    public static void main(String args[]) throws Exception{
        String token = Authorization.generateToken("bum dia",true);
        System.out.println("Token "+ token);

        String extract = Authorization.extractClaims(token).toString();

        System.out.println("Extract " + extract);

        //System.out.println("Expirou -> " + isTokenExpired(token));
    }

}
