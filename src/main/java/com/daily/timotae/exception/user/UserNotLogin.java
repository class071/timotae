package com.daily.timotae.exception.user;

public class UserNotLogin extends RuntimeException{
    private static final String MESSAGE = "로그인이 필요합니다.";
    public UserNotLogin() {
        super(MESSAGE);
    }
}
