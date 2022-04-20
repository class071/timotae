package com.daily.timotae.dto;

import com.daily.timotae.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {

    private String title;
    private String category;
    private long userId;
    private String content;
    private int viewCount;

    @Builder
    public PostUpdateRequestDto(String title, String category, long userId, String content, int viewCount) {
        this.title = title;
        this.category = category;
        this.userId = userId;
        this.content = content;
        this.viewCount = viewCount;
    }

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .category(category)
                .content(content)
                .userId(userId)
                .viewCount(viewCount)
                .build();
    }
}
