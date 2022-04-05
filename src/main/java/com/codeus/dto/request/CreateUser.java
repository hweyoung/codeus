package com.codeus.dto.request;

import com.codeus.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class CreateUser {
    private String id;
    private String pwd;

    public User toEntity(){
        return User.builder().id(id).pwd(pwd).build();
    }
}
