package com.daily.timotae;

import com.daily.timotae.domain.Post;
import com.daily.timotae.repository.MemoryPostRepository;
import com.daily.timotae.repository.PostRepository;
import com.daily.timotae.service.BoardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryPostRepositoryTest {

    PostRepository postRepository = new MemoryPostRepository();

    @AfterEach
    public void afterEach(){
        postRepository.clearStore();
    }

    @Test
    public void 글작성(){
        //given
        String title = "제목";
        String content = "글 내용";
        String userId = "작성자";
        String category = "카테고리";
        String dateOfIssue = "2021-12-23";

        Post post = new Post.PostBuilder(title, content, userId, category, dateOfIssue)
                .build();

        postRepository.savePost(post);

        //when
        List<Post> result = postRepository.findAll();

        //then
        Assertions.assertThat(result.size()).isEqualTo(1);
    }
}
