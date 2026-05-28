package com.amit8085.finance_tracker.security;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "mysecretkeymysecretkeymysecretkey";   //password for token system
    private static final Key key= Keys.hmacShaKeyFor(SECRET.getBytes());        //convert secret string into crypographic

    //GENERATE TOKEN
    public static String generateToken(String username){
        return Jwts.builder()   //start building token
                .setSubject(username)   //store data inside token
                .setIssuedAt(new Date())    //token created time
                .setExpiration(new Date(System.currentTimeMillis()+86400000))   //token expire time
                .signWith(key)      //Lock the token with  secret
                .compact();     //Convert into final string like kuch bhi eyiwubdkhsvydgebiuwh
    }

    //EXTRACT USERNAME FROM TOKEN
    public static String extractUsername(String token){
        return Jwts.parserBuilder() //start reading token
                .setSigningKey(key)  //Use same secret to verify token
                .build()
                .parseClaimsJws(token)  //decode token
                .getBody()
                .getSubject();   // store username

    }

}
