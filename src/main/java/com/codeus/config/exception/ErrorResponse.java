package com.codeus.config.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private int status;
    private String Message;
    private String ErrorCode;

    public ErrorResponse(int status, String message, String errorCode) {
        this.status = status;
        Message = message;
        ErrorCode = errorCode;
    }
}
