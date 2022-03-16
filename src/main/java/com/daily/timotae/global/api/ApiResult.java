package com.daily.timotae.global.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class ApiResult<T> {

    private final boolean success;
    private final String Code;
    private final T response;
    private final ApiError apiError;
}
