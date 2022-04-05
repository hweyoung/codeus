package com.codeus.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    //entity는 카멜케이스로 작성

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

//    @Column(nullable = false)
    private String id;
//    @Column(nullable = false)
    private String pwd;

//    @Column(nullable = false)
    private String refToken;

    @ManyToOne
    private Group_ group;

    @Builder
    public User(String id, String pwd){
        this.id = id;
        this.pwd = pwd;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setRefToken(String refToken) {
        this.refToken = refToken;
    }
}
