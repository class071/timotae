package com.daily.timotae.user.dto;

import com.daily.timotae.user.domain.Role;
import com.daily.timotae.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    private String userId;
    private String userPassword;
    private String name;
    private int age;
    private String email;

    public static User toEntity(SignUpRequest req, PasswordEncoder passwordEncoder) {
        return new User(req.userId, passwordEncoder.encode(req.userPassword), req.name, req.age, req.email, Role.USER);
    }
}
