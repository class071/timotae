package com.daily.timotae.user.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    private String userPassword;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> roles;

    public User(String userId, String userPassword, String name, Integer age, String email, List<Role> roles) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.name = name;
        this.age = age;
        this.email = email;
        this.roles = roles.stream().map(r -> new UserRole(this, r)).collect(toSet());
    }

    public void update(String userId, String userPassword, String name, int age, String email) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
