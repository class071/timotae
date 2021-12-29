package com.daily.timotae.service;

import com.daily.timotae.domain.Post;
import com.daily.timotae.repository.PostRepository;

import java.util.List;

public class BoardService {
    private final PostRepository postRepository;

    public BoardService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public void createPost(Post post){
        postRepository.savePost(post);
    }

    public List<Post> readAllPost(){
        return postRepository.findAll();
    }

    public Post readOnePostDetail(Long postId){
        return postRepository.findByPostId(postId);
    }
}
