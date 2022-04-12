package com.daily.timotae.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Reply extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long replyId;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long parentReplyId;

    @Column(nullable = false)
    private long userId;

    @Column(length = 50, nullable = false)
    private String replyContent;

    private int depth;

    @Builder
    public Reply(Long replyId, Long postId, Long parentReplyId, long userId, String replyContent, int depth) {
        this.replyId = replyId;
        this.postId = postId;
        this.parentReplyId = parentReplyId;
        this.userId = userId;
        this.replyContent = replyContent;
        this.depth = depth;
    }

    public void update(String content){
        this.replyContent = content;
    }
}
