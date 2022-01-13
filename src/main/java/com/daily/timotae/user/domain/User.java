package com.daily.timotae.user.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userId;

    @Column
    private String userPassword;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private String email;
}
