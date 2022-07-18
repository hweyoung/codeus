package com.codeus.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity{

    //entity는 카멜케이스로 작성

    @Id
    @Column(name = "user_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false)
    private String question;

    @Column(name ="group_code")
    private String groupCode;

    private String img;


    @Builder
    public User(String id, String pwd, String groupCode, String question, String img) {
        this.id = id;
        this.pwd = pwd;
        this.groupCode = groupCode;
        this.question = question;
        this.img = img;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
