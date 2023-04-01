package com.bankify.bankapi.tools;

import lombok.Data;

import java.util.Base64;


public class PasswordUtil {

    private final Base64.Encoder encoder;
    private final Base64.Decoder decoder;

    private final int passCount;

    public PasswordUtil(){
        this.encoder = Base64.getEncoder();
        this.decoder = Base64.getDecoder();
        this.passCount = 5;
    }


    public String encodePassword(String password){

        for(int i=0; i<passCount;i++){
            password = encoder.encodeToString(password.getBytes());
        }
        return password;
    }

    public boolean comparePasswords(String encodedPassword,String password){

        for(int i=0; i<passCount;i++){
            encodedPassword = new String(decoder.decode(encodedPassword));
        }

        return (encodedPassword.compareTo(password) == 0);
    }

}
