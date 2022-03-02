package com.daily.timotae.global.login.filter;

import com.daily.timotae.global.jwt.service.JwtService;
import com.daily.timotae.user.domain.User;
import com.daily.timotae.user.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final JpaUserRepository jpaUserRepository;

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    private final String NO_CHECK_URL = "/login";//1

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getRequestURI().equals(NO_CHECK_URL)) {
            filterChain.doFilter(request, response);
            return;
        }

        String refreshToken = jwtService
                .extractRefreshToken(request)
                .filter(jwtService::isTokenValid)
                .orElse(null);


        if(refreshToken != null){
            checkRefreshTokenAndReIssueAccessToken(response, refreshToken);
            return;
        }

        checkAccessTokenAndAuthentication(request, response, filterChain);

    }

    private void checkAccessTokenAndAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        jwtService.extractAccessToken(request).filter(jwtService::isTokenValid).ifPresent(

                accessToken -> jwtService.extractUsername(accessToken).ifPresent(

                        username -> jpaUserRepository.findByUserId(username).ifPresent(

                                user -> saveAuthentication(user)
                        )
                )
        );

        filterChain.doFilter(request,response);
    }

    private void saveAuthentication(User user) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserId())
                .password(user.getUserPassword())
                .roles(user.getRole().name())
                .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,authoritiesMapper.mapAuthorities(userDetails.getAuthorities()));

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    private void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, String refreshToken) {


        jpaUserRepository.findByRefreshToken(refreshToken).ifPresent(
                user -> jwtService.sendAccessToken(response, jwtService.createAccessToken(user.getUserId())
                ));


    }
}
