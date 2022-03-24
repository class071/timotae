package com.daily.timotae.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {

    CREATE_SUCCESS(HttpStatus.OK, "Data create success"),
    READ_SUCCESS(HttpStatus.OK, "Data read success"),
    UPDATE_SUCCESS(HttpStatus.OK, "Data update success"),
    DELETE_SUCCESS(HttpStatus.OK, "Data delete success"),
    SEARCH_SUCCESS(HttpStatus.OK, "Data search success");

    private final HttpStatus httpStatus;
    private final String message;
}
