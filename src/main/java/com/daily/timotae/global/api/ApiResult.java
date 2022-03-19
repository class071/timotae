package com.daily.timotae.global.api;

import lombok.Getter;

@Getter
public class ApiResult<T> {

    private final String code;
    private final int httpStatus;
    private final String message;
    private final T response;

    public ApiResult(String code, int httpStatus, String message, T response) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
        this.response = response;
    }
}
