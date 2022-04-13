package com.daily.timotae.dto;

import com.daily.timotae.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCreateRequestDto {

    private String title;
    private String category;
    private String userId;
    private String content;
    private int hits;

    @Builder
    public PostCreateRequestDto(String title, String category, String userId, String content, int hits) {
        this.title = title;
        this.category = category;
        this.userId = userId;
        this.content = content;
        this.hits = hits;
    }

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .category(category)
                .content(content)
                .userId(userId)
                .hits(hits)
                .build();
    }
}
