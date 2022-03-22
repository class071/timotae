package com.daily.timotae.user.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    private String userPassword;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String userId, String userPassword, String name, int age, String email, Role role) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.name = name;
        this.age = age;
        this.email = email;
        this.role = role;
    }

    public void update(String userId, String userPassword, String name, int age, String email, Role role) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.name = name;
        this.age = age;
        this.email = email;
        this.role = role;
    }
}
