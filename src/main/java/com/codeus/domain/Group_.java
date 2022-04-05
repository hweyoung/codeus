package com.codeus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group_ {
    @Id
    @GeneratedValue
    private Long groupSeq;

    @OneToMany
    private List<User> users=new ArrayList<>();

    private String name;


}
