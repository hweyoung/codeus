package com.codeus.dto.user.request;

import lombok.*;


@Getter
@NoArgsConstructor
public class UpdatePwd {
    private String pwd;
    private String newPwd;
}
