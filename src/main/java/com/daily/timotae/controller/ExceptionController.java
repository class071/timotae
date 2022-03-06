package com.daily.timotae.controller;

import com.daily.timotae.constant.ErrorCode;
import com.daily.timotae.dto.ErrorResponse;
import com.daily.timotae.exception.post.NotSupportSuchTypeException;
import com.daily.timotae.exception.reply.NoMoreReply;
import com.daily.timotae.exception.reply.NoParentReplyExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e){
        final ErrorCode errorCode = ErrorCode.RESOURCE_NOT_FOUND;
        return handleExceptionInternal(errorCode, e.getMessage());
    }

    @ExceptionHandler(NotSupportSuchTypeException.class)
    public ResponseEntity<Object> handleNotSupportSuchTypeException(NotSupportSuchTypeException e){
        final ErrorCode errorCode = ErrorCode.RESOURCE_NOT_FOUND;
        return handleExceptionInternal(errorCode, e.getMessage());
    }

    @ExceptionHandler(NoParentReplyExist.class)
    public ResponseEntity<Object> handleNoParentReplyExist(NoParentReplyExist e){
        final ErrorCode errorCode = ErrorCode.RESOURCE_NOT_FOUND;
        return handleExceptionInternal(errorCode, e.getMessage());
    }

    @ExceptionHandler(NoMoreReply.class)
    public ResponseEntity<Object> handleNoMoreReply(NoMoreReply e){
        final ErrorCode errorCode = ErrorCode.RESOURCE_NOT_FOUND;
        return handleExceptionInternal(errorCode, e.getMessage());
    }

    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode, String message) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode, message));
    }

    private ErrorResponse makeErrorResponse(ErrorCode errorCode, String message) {
        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(message)
                .build();
    }

}
