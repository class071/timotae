package com.daily.timotae.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private Long userId;

    @Column(length = 300, nullable = false)
    private String content;

    @Column(nullable = false)
    private volatile int viewCount;

    @Builder
    public Post(String title, String category, long userId,  String content, int viewCount) {
        this.title = title;
        this.category = category;
        this.userId = userId;
        this.content = content;
        this.viewCount = viewCount;
    }

    public void update(String title, String category, long userId, String content, int viewCount){
        this.title = title;
        this.category = category;
        this.userId = userId;
        this.content = content;
        this.viewCount = viewCount;
    }

    public synchronized void viewCountUp() {
        viewCount++;
    }
}
