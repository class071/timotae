package com.daily.timotae.dto;

import com.daily.timotae.domain.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyCreateRequestDto {

    private Long postId;
    private Long replyId;
    private Long parentReplyId;
    private String userId;
    private String replyContent;
    private int depth;

    @Builder
    public ReplyCreateRequestDto(Long postId, Long replyId, Long parentReplyId, String userId, String replyContent, int depth) {
        this.postId = postId;
        this.replyId = replyId;
        this.parentReplyId = parentReplyId;
        this.userId = userId;
        this.replyContent = replyContent;
        this.depth = depth;
    }

    public Reply toEntity(){
        return Reply.builder()
                .postId(postId)
                .replyId(replyId)
                .replyContent(replyContent)
                .userId(userId)
                .depth(depth)
                .build();
    }
}
