package com.arupapi.arupapi.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenHelper {
    
    
  private static final int JWT_TOKEN_VALIDITY = (5 * 60 * 60);

   

    private String secret ="JwttokenKey";

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

   public Date getExpirationDateRromToken(String token){
        return getClaimFromToken(token , Claims :: getExpiration);
   }

    private <T> T getClaimFromToken(String token, Function<Claims, T> clamesResolver) {
        final Claims claims =getAllClaimFromToken(token);
        return clamesResolver.apply(claims);
    }

    private Claims getAllClaimFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public boolean isTokenExpired(String token){
        final Date expiredate =getExpirationDateRromToken(token);
        return expiredate.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
