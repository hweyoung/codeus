package com.codeus.dto.user.request;

import com.codeus.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
public class LoginUser {
    private String id;
    private String pwd;


    public User toEntity(){
        return User.builder().id(id).pwd(pwd).build();
    }
}
