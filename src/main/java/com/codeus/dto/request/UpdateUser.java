package com.codeus.dto.request;

import com.codeus.domain.User;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class UpdateUser {
    private String id;
    private String pwd;
}
