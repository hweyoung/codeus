package com.codeus.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> BadRequestException(BadRequestException e){
        String msg = e.getMessage();
        int status = 400;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(status,"BAD_REQUEST",msg));
    }

    @ExceptionHandler(MethodNotAllowException.class)
    public ResponseEntity<Object> MethodNotAllowException(MethodNotAllowException e){
        String msg = e.getMessage();
        int status = 403;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(status,"METHOD_NOT_ALLOWED",msg));
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Object> TokenExpiredException(TokenExpiredException e){
        String msg = e.getMessage();
        int status = 403;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(status,"TOKEN_EXPIRED",msg));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> NotFoundException(NotFoundException e){
        String msg = e.getMessage();
        int status = 404;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(status,"NOT_FOUND",msg));
    }

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<Object> GlobalException(GlobalException e){
        String msg = e.getMessage();
        int status = 500;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(status,"GLOBAL_EXCEPTION",msg));
    }
}
