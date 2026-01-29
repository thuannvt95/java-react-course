package com.bank.app.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.logging.Logger;

@Component
public class JwtUtil {
    Logger logger = Logger.getLogger(JwtUtil.class.getName());

    public String generateToken(UserDetails userDetails) {
        String userName = userDetails.getUsername();

        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000)) // 1 day expiration;
                .signWith(key())
                .compact();
    }

    public String getTokenFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(key())
                   .build()
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
           logger.info(e.getMessage());
        }
        return false;
    }


    public Key key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode("RkJZQzN4R3h1Qk9mZVZKSkhLZzR1RjN3dFZ6b0ZlU0R6aHhVbGxQY2c=")
        );
    }
}
