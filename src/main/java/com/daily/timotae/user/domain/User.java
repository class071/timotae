package com.daily.timotae.user.domain;

import lombok.Data;

import javax.persistence.*;

@Data
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
