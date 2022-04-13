package com.daily.timotae.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

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

    private int hits;

    @Builder
    public Post(String title, String category, String userId,  String content, int hits) {
        this.title = title;
        this.category = category;
        this.userId = userId;
        this.content = content;
        this.hits = hits;
    }

    public void update(String title, String category, String userId, String content, int hits){
        this.title = title;
        this.category = category;
        this.userId = userId;
        this.content = content;
        this.hits = hits;
    }

    public void postHits(int hits) {
        this.hits = hits;
    }
}
