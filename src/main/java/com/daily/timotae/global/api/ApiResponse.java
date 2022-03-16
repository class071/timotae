package com.daily.timotae.global.api;

public class ApiResponse {

    public static <T>ApiResult<T> success(String code, T response) {
        return new ApiResult<>(true, code, response, null);
    }

    public static ApiResult<?> error(String code, String message, int status){
        return new ApiResult<>(false, code, null, new ApiError(message, status));
    }
}
