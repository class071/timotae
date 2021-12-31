package com.daily.timotae.service;

import com.daily.timotae.domain.Post;
import com.daily.timotae.repository.PostRepository;

public class BoardService {

    private final PostRepository postRepository;

    public BoardService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public void createPost(Post post){
        postRepository.savePost(post);
    }

    public void readPostAll(){
        postRepository.findPostAll();
    }

    public Post readPostOneDetail(Long postId){
        return postRepository.findPostOne(postId);
    }
}
