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
    @Column(name = "user_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

//    @Column(nullable = false)
    private String id;
//    @Column(nullable = false)
    private String pwd;

    @Column(name ="group_code")
    private String groupCode;

    private String question;

    private String img;

    @Column(name="cre_date")
    private String creDate;

    @Column(name = "reg_date")
    private String regDate;

    @Builder
    public User(String id, String pwd, String groupCode, String question, String img, String regDate) {
        this.id = id;
        this.pwd = pwd;
        this.groupCode = groupCode;
        this.question = question;
        this.img = img;
        this.regDate = regDate;
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

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
}
