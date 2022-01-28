package com.daily.timotae.domain;

import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String userId;

    @Column(length = 300, nullable = false)
    private String content;

    @Builder
    public Post(String title, String category, String userId,  String content) {
        this.title = title;
        this.category = category;
        this.userId = userId;
        this.content = content;
    }

    public void update(String title, String category, String userId, String content){
        this.title = title;
        this.category = category;
        this.userId = userId;
        this.content = content;
    }
}
