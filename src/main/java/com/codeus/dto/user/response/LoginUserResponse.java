package com.codeus.dto.user.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginUserResponse {
    private String token;
    private String expiredDate ;

    public LoginUserResponse(String token, String expiredDate) {
        this.token = token;
        this.expiredDate = expiredDate;
    }
}
