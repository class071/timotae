package com.daily.timotae.global.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private String code;
    private int httpStatus;
    private String message;
    private T response;

    public static <T> ApiResponse success(String code, int httpStatus, String message, T response) {
        return new ApiResponse<>(code, httpStatus, message, response);
    }

    public static ApiResponse error(String code, int httpStatus, String message) {
        return new ApiResponse<>(code, httpStatus, message, null);
    }
}
