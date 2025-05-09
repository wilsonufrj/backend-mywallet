package br.projeto.mywallet.Utils;

import io.jsonwebtoken.Jwts;
import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private static final SecretKey SECRET_KEY =  Jwts.SIG.HS256.key().build();
    private static final long EXPIRATION_TIME = 86400000; // 24h

    public static String generateToken(String username) {
        return Jwts.builder()
                .subject(username) // Novo método (substitui setSubject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    public static String validateToken(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

}
