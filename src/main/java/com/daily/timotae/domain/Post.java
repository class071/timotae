package com.daily.timotae.domain;

import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    private String title;

    private String category;

    private String userId;

    private String dateOfIssue;

    private String content;

    @Builder
    public Post(String title, String category, String userId, String dateOfIssue, String content) {
        this.title = title;
        this.category = category;
        this.userId = userId;
        this.dateOfIssue = dateOfIssue;
        this.content = content;
    }
}
