package com.daily.timotae.service;

import com.daily.timotae.domain.Post;
import com.daily.timotae.repository.PostRepository;

public class BoardService {
    private final PostRepository postRepository;

    public BoardService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public Long createPost(Post post){ //등록
        postRepository.savePost(post);
        return post.getPostId();
    }
}
