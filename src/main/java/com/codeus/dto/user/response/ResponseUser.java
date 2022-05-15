package com.codeus.dto.user.response;

import com.codeus.domain.User;
import lombok.Getter;

@Getter
public class ResponseUser {
    private String id;
    private String pwd;
    private String img;

    public ResponseUser(User user){
        this.id = user.getId();
        this.pwd = user.getPwd();
        this.img = user.getImg();
    }
}
