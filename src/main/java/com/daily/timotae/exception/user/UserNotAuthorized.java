package com.daily.timotae.exception.user;

public class UserNotAuthorized extends RuntimeException{
    private static final String MESSAGE = "권한이 없습니다.";
    public UserNotAuthorized() {
        super(MESSAGE);
    }
}
