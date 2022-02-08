package com.daily.timotae.user.service;

import com.daily.timotae.user.domain.User;
import com.daily.timotae.user.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService implements UserDetailsService {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = jpaUserRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("아이디가 없습니다."));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserId())
                .password(user.getUserPassword())
                .roles(user.getRole().name())
                .build();
    }
}
