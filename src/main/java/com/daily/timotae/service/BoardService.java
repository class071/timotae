package com.daily.timotae.service;

import com.daily.timotae.domain.Post;
import com.daily.timotae.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private final PostRepository postRepository;

    @Autowired
    public BoardService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public void createPost(Post post){
        postRepository.savePost(post);
    }

    public void updatePost(Long postId, Post post){
        postRepository.changePost(postId, post);
    }

    public void deletePost(Long postId){
        postRepository.removePost(postId);
    }

    public void readPostAll(){
        postRepository.findPostAll();
    }

    public Post readPostOneDetail(Long postId){
        return postRepository.findPostOne(postId);
    }
}
