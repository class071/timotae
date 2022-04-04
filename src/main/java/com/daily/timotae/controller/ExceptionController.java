package com.daily.timotae.controller;


import com.daily.timotae.constant.ErrorCode;
import com.daily.timotae.exception.post.NoSuchPostExist;
import com.daily.timotae.exception.post.NotSupportSuchTypeException;
import com.daily.timotae.exception.reply.NoMoreReply;
import com.daily.timotae.exception.reply.NoParentReplyExist;
import com.daily.timotae.global.api.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchPostExist.class)
    public ApiResponse handleNoSuchPostExist(NoSuchPostExist e){
        final ErrorCode errorCode = ErrorCode.RESOURCE_NOT_FOUND;
        return ApiResponse.error(errorCode.name(), errorCode.getHttpStatus(), errorCode.getMessage());
    }

    @ExceptionHandler(NotSupportSuchTypeException.class)
    public ApiResponse handleNotSupportSuchTypeException(NotSupportSuchTypeException e){
        final ErrorCode errorCode = ErrorCode.RESOURCE_NOT_FOUND;
        return ApiResponse.error(errorCode.name(), errorCode.getHttpStatus(), errorCode.getMessage());
    }

    @ExceptionHandler(NoParentReplyExist.class)
    public ApiResponse handleNoParentReplyExist(NoParentReplyExist e){
        final ErrorCode errorCode = ErrorCode.RESOURCE_NOT_FOUND;
        return ApiResponse.error(errorCode.name(), errorCode.getHttpStatus(), errorCode.getMessage());
    }

    @ExceptionHandler(NoMoreReply.class)
    public ApiResponse handleNoMoreReply(NoMoreReply e){
        final ErrorCode errorCode = ErrorCode.RESOURCE_NOT_FOUND;
        return ApiResponse.error(errorCode.name(), errorCode.getHttpStatus(), errorCode.getMessage());
    }
}
