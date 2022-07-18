package com.codeus.dto.user.response;

import lombok.Getter;

@Getter
public class GetUserResponse {
    private String id;
    private String img;

    public GetUserResponse(String id, String img) {
        this.id = id;
        this.img = img;
    }
}
