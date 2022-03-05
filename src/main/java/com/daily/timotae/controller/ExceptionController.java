package com.daily.timotae.controller;

import com.daily.timotae.dto.ErrorResponse;
import com.daily.timotae.exception.post.NotSupportSuchTypeException;
import com.daily.timotae.exception.reply.NoMoreReply;
import com.daily.timotae.exception.reply.NoParentReplyExist;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotSupportSuchTypeException.class)
    public ResponseEntity<?> handleNotSupportSuchTypeException(NotSupportSuchTypeException e){
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .code("Not Support Such Type")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NoParentReplyExist.class)
    public ResponseEntity<?> handleNoParentReplyExist(NoParentReplyExist e){
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .code("NOT_EXIST")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NoMoreReply.class)
    public ResponseEntity<?> handleNoMoreReply(NoMoreReply e){
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .code("Not able to create More Reply")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e){
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .code("Resource not exists")
                .message("Resource not exists")
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
