package com.daily.timotae.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not exists"),
    NOT_LOGIN_USER(HttpStatus.UNAUTHORIZED, "This user did not login"),
    NOT_AUTHORIZED_USER(HttpStatus.FORBIDDEN, "This user did not be authorized");

    private final HttpStatus httpStatus;
    private final String message;

}