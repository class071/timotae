package com.daily.timotae.dto;

import com.daily.timotae.domain.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {

    private String title;
    private String category;
    private String userId;
    private String content;
    private String dateOfIssue;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.category = post.getCategory();
        this.userId = post.getUserId();
        this.content = post.getContent();
        this.dateOfIssue = post.getDateOfIssue();
    }
}
