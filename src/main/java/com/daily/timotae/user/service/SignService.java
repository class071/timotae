package com.daily.timotae.user.service;

import com.daily.timotae.exception.user.*;
import com.daily.timotae.user.domain.RoleType;
import com.daily.timotae.user.domain.User;
import com.daily.timotae.user.dto.RefreshTokenResponse;
import com.daily.timotae.user.dto.SignInRequest;
import com.daily.timotae.user.dto.SignInResponse;
import com.daily.timotae.user.dto.SignUpRequest;
import com.daily.timotae.user.repository.JpaUserRepository;
import com.daily.timotae.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SignService {

    private final JpaUserRepository jpaUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Transactional
    public void signUp(SignUpRequest req) {
        validateSignUpInfo(req);
        jpaUserRepository.save(SignUpRequest.toEntity(req,
                roleRepository.findByRoleType(RoleType.USER).orElseThrow(RoleNotFoundException::new),
                passwordEncoder));
    }

    @Transactional(readOnly = true)
    public SignInResponse signIn(SignInRequest req) {
        User user = jpaUserRepository.findByUserId(req.getUserId()).orElseThrow(UserNotFoundException::new);
        validatePassword(req, user);
        String subject = createSubject(user);
        String accessToken = tokenService.createAccessToken(subject);
        String refreshToken = tokenService.createRefreshToken(subject);
        return new SignInResponse(accessToken, refreshToken);
    }

    private void validateSignUpInfo(SignUpRequest req) {
        if(jpaUserRepository.existsByUserId(req.getUserId()))
            throw new UserIdAlreadyExistsException(req.getUserId());
        if(jpaUserRepository.existsByEmail(req.getEmail()))
            throw new UserEmailAlreadyExistsException(req.getEmail());
    }

    private void validatePassword(SignInRequest req, User user) {
        if(!passwordEncoder.matches(req.getUserPassword(), user.getUserPassword())) {
            throw new LoginFailureException();
        }
    }

    private String createSubject(User user) {
        return String.valueOf(user.getId());
    }

    public RefreshTokenResponse refreshToken(String rToken) {
        validateRefreshToken(rToken);
        String subject = tokenService.extractRefreshTokenSubject(rToken);
        String accessToken = tokenService.createAccessToken(subject);
        return new RefreshTokenResponse(accessToken);
    }

    private void validateRefreshToken(String rToken) {
        if (!tokenService.validateRefreshToken(rToken)) {
            throw new AuthenticationEntryPointException();
        }
    }
}
