package com.daily.timotae.domain;

import lombok.Builder;

import java.io.Serializable;

public class Post implements Serializable {

    private Long postId;
    private String title;
    private String category;
    private String userId;
    private String dateOfIssue;
    private String content;

    public Post(Long postId){
        this.postId = postId;
    }

    public Long getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getUserId() {
        return userId;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public String getContent() {
        return content;
    }

    @Builder
    public Post(String title, String category, String userId, String dateOfIssue, String content){
        this.title = title;
        this.category = category;
        this.userId = userId;
        this.dateOfIssue = dateOfIssue;
        this.content = content;
    }
}