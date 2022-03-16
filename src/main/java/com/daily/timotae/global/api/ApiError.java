package com.daily.timotae.global.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class ApiError {

    private final String message;
    private final int HttpStatus;
}
