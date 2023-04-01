package com.bankify.bankapi.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class JWTService {

    @Value("${database.jwt-secret}")
    private String jwtSecret="C^51ASxa4ikt";
    final private Algorithm algorithm;
    final private List<String> listOfValidTokens;


    public JWTService(){
        this.algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
        listOfValidTokens = new ArrayList<>();
    }

    public String createJWT(String username){
        Instant instant = Instant.now();
        String token=JWT.create()
                .withIssuer(username)
                .withIssuedAt(instant)
                .withExpiresAt(instant.plusSeconds(600))
                .withJWTId(username)
                .withClaim("username",username)
                .sign(this.algorithm);

       listOfValidTokens.add(token);
       return token;
    }

    public Boolean verifyJwt(String token, String username){

        JWTVerifier verifier = JWT.require(this.algorithm)
                .withIssuer(username)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        if(jwt.getId().compareTo(username) == 0){
            return true;
        }
        return false;
    }
}
