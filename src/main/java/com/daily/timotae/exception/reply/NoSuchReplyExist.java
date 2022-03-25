package com.daily.timotae.exception.reply;

public class NoSuchReplyExist extends RuntimeException{
    private static final String MESSAGE = "존재하지 않는 댓글입니다.";
    public NoSuchReplyExist() {
        super(MESSAGE);
    }
}
