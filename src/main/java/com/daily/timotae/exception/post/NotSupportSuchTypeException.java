package com.daily.timotae.exception.post;

public class NotSupportSuchTypeException extends RuntimeException{
    private static final String MESSAGE = "해당 Type의 검색은 지원하지 않습니다.";
    public NotSupportSuchTypeException() {
        super(MESSAGE);
    }
}
