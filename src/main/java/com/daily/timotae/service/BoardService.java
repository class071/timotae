package com.daily.timotae.service;

import com.daily.timotae.domain.Post;
import com.daily.timotae.repository.PostRepository;
import java.util.Map;

public class BoardService {

    private final PostRepository postRepository;

    public BoardService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public void createPost(Post post){
        postRepository.savePost(post);
    }

    public Map<Long, Post> readAllPost(){
        return postRepository.findAllWithPostId();
    }

    public Post readOnePostDetail(Long postId){
        return postRepository.findByPostId(postId);
    }

    public void updatePost(Long postId, Post post){
        postRepository.changePost(postId, post);
    }
}
