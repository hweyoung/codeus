package com.codeus.dto.user.request;

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
    private String question;

    public User toEntity(){
        return User.builder().id(id).pwd(pwd).question(question).build();
    }
}
