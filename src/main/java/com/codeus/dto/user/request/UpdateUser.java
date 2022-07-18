package com.codeus.dto.user.request;

import lombok.*;


@Getter
@NoArgsConstructor
//pwd, group,
public class UpdateUser {
    private String pwd;
    private String question;
}
