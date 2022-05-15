package com.codeus.config.exception;

public class TokenExpiredException extends RuntimeException{
    public TokenExpiredException(String s){
        super(s);
    }
}
