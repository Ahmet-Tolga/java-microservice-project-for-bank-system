package com.bank_account.app.service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private static String SECRET_KEY="qzy4CcnXqr5CTzgNP9cOgSZHmYoDFQVSrRit7gOVCNLDI55ywhQhhuHXQPeTLe";

    public <T> T extractClaim(String token,Function<Claims,T> claimResolver){
        final Claims claims=extractAllClaims(token);

        return claimResolver.apply(claims);
    }

    public String extractUserFromToken(String token){
        return extractClaim(token, Claims::getSubject);
    }


    public boolean isTokenExpired(String token){
        return extractClaim(token,Claims::getExpiration).before(new Date());
    }

    public boolean isTokenValid(String username,String token){

        String extractedUsername=extractUserFromToken(token);

        if(!isTokenExpired(token) && username.equals(extractedUsername)){
            return true;
        }

        return false;
    }

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
        .signWith(getSigninKey(),io.jsonwebtoken.SignatureAlgorithm.HS256).compact();
    }

    public Claims extractAllClaims(String jwtToken){
        return Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(jwtToken).getBody();
    }

    public Key getSigninKey(){
        byte[] key=Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(key);
    }
}