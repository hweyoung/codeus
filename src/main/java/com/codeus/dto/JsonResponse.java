package com.codeus.dto;

import lombok.Getter;

@Getter
public class JsonResponse {
    private int status;
    private String Message;
    private String ErrorCode;
    private Object data;

    public JsonResponse(String message, String errorCode,Object data) {
        this.status = 200;
        this.Message = message;
        this.ErrorCode = errorCode;
        this.data = data;
    }
}
