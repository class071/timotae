package com.daily.timotae.exception.post;

public class NoSuchPostExist extends RuntimeException{
    private static final String MESSAGE = "존재하지 않는 게시글입니다.";
    public NoSuchPostExist() {
        super(MESSAGE);
    }
}
