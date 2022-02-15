package com.daily.timotae.exception.reply;

public class NoMoreReply extends RuntimeException {
    private static final String MESSAGE = "더 이상의 답글 작성은 불가능합니다.";
    public NoMoreReply() {
        super(MESSAGE);
    }
}
