package com.daily.timotae.user.controller;

import com.daily.timotae.global.api.ApiResponse;
import com.daily.timotae.user.dto.SignInRequest;
import com.daily.timotae.user.dto.SignUpRequest;
import com.daily.timotae.user.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class SignController {

    private final SignService signService;

    @PostMapping("/api/sign-up")
    public ApiResponse signUp(@Valid @RequestBody SignUpRequest request) {
        signService.signUp(request);
        return ApiResponse.success(null, HttpStatus.CREATED, null, null);
    }

    @PostMapping("/api/sign-in")
    public ApiResponse singIn(@Valid @RequestBody SignInRequest request) {
        return ApiResponse.success(null, HttpStatus.OK, null, signService.signIn(request));
    }

    @PostMapping("/api/refresh-token")
    public ApiResponse refreshToken(@RequestHeader(value = "Authorization") String refreshToken) {
        return ApiResponse.success(null, HttpStatus.OK, null, signService.refreshToken(refreshToken));
    }
}
