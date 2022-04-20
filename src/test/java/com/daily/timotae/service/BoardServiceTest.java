package com.daily.timotae.service;

import com.daily.timotae.domain.Post;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BoardServiceTest {

    @Test
    void viewCountUp() throws InterruptedException {
        //given
        Post newPost = new Post("test", "test", 1L, "test", 0);
        //when
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                newPost.viewCountUp();
            }).start();
        }

        //then
        Thread.sleep(100);
        assertThat(newPost.getViewCount()).isEqualTo(100);
    }
}