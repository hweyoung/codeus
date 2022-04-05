package com.codeus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    private Long postSeq;

    private Long groupSeq;

    private String userid;

    private Long qListid;

    private Long week;

    private String qListName;

    private String code;

    private String stack;

    private String comment;




}
