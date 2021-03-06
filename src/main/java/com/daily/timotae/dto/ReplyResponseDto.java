package com.daily.timotae.dto;

import com.daily.timotae.domain.Reply;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReplyResponseDto {

    private Long postId;
    private Long replyId;
    private Long parentReplyId;
    private long userId;
    private String replyContent;
    private int depth;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ReplyResponseDto(Reply reply) {
        this.postId = reply.getPostId();
        this.replyId = reply.getReplyId();
        this.parentReplyId = reply.getParentReplyId();
        this.userId = reply.getUserId();
        this.replyContent = reply.getReplyContent();
        this.depth = reply.getDepth();
        this.createdDate = reply.getCreatedDate();
        this.modifiedDate = reply.getModifiedDate();
    }
}
