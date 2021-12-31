package com.daily.timotae.domain;

import lombok.Builder;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

public class Post {

    //@Id @GeneratedValue(strategy = GenerationType.AUTO)
    //private Long postId;

    private String title;

    private String category;

    private String userId;

    private String dateOfIssue;

    private String content;

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

    private Post(PostBuilder postBuilder) {
        this.title = postBuilder.title;
        this.category = postBuilder.category;
        this.userId = postBuilder.userId;
        this.dateOfIssue = postBuilder.dateOfIssue;
        this.content = postBuilder.content;
    }

    public static class PostBuilder {

        private String title;
        private String category;
        private String userId;
        private String dateOfIssue;
        private String content;

        public PostBuilder(String title, String category, String userId, String dateOfIssue, String content) {
            this.title = title;
            this.category = category;
            this.userId = userId;
            this.dateOfIssue = dateOfIssue;
            this.content = content;
        }

        public Post build() {

            return new Post(this);
        }
    }
}