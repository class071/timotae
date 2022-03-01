package com.daily.timotae.exception.reply;

public class NoParentReplyExist extends RuntimeException {
    private static final String MESSAGE = "해당 댓글의 답글은 작성할 수 없습니다.";
    public NoParentReplyExist() {
        super(MESSAGE);
    }
}
