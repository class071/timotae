package com.daily.timotae.global.api;

public class ApiResponse {

    public static <T>ApiResult<T> success(String code, int httpStatus, String message, T response) {
        return new ApiResult<>(code, httpStatus, message, response);
    }

    public static ApiResult<?> error(String code, int httpStatus, String message) {
        return new ApiResult<>(code, httpStatus, message, null);
    }
}
