package com.codeus.dto.user.response;

import lombok.Getter;

@Getter
public class ChangePwdResponse {
    private String token;
    private String id;

    public ChangePwdResponse(String id, String token) {
        this.token = token;
        this.id = id;
    }
}
