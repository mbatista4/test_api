package com.bankify.bankapi.exceptions;

import com.auth0.jwt.exceptions.IncorrectClaimException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidArgument(WebExchangeBindException e){
        Map<String, String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> errorMap.put(fieldError.getField(),fieldError.getDefaultMessage()));
        return errorMap;
    }

    @ExceptionHandler({TokenExpiredException.class,SignatureVerificationException.class, JWTDecodeException.class, IncorrectClaimException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleTokenExpireException(Exception e){
        Map<String,Object> mapError = new HashMap<>();
        mapError.put("msg",e.getMessage());
        return mapError;
    }
}
