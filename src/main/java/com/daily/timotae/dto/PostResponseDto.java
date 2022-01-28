package com.daily.timotae.dto;

import com.daily.timotae.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {

    private String title;
    private String category;
    private String userId;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.category = post.getCategory();
        this.userId = post.getUserId();
        this.content = post.getContent();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
    }
}
