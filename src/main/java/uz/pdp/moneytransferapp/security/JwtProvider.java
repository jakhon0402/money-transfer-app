package uz.pdp.moneytransferapp.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    String secret_word = "gorgeous";
    long expireTime = 36_000_000;

    public String generateToken(String username){
        Date expireDate = new Date(System.currentTimeMillis()+expireTime);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret_word)
                .compact();
        return token;
    }

    public boolean validateToken(String token){
        try {
            Jwts
                    .parser()
                    .setSigningKey(secret_word)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public String getUsernameFromToken(String token){
        String subject = Jwts
                .parser()
                .setSigningKey(secret_word)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return subject;
    }
}
