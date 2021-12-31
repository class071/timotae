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
    BoardService boardService = new BoardService(postRepository);
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

        String title2 = "제목2";
        String content2 = "글 내용2";
        String userId2 = "작성자2";
        String category2 = "카테고리2";
        String dateOfIssue2 = "2021-12-29";

        Post post = new Post.PostBuilder(title, content, userId, category, dateOfIssue)
                .build();

        Post post2 = new Post.PostBuilder(title2, content2, userId2, category2, dateOfIssue2)
                .build();

        boardService.createPost(post);
        boardService.createPost(post2);

        //when
        List<Post> result = postRepository.findAll();

        //then
        Assertions.assertThat(result.get(1).getTitle()).isEqualTo("제목");
    }
}
