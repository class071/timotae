package com.daily.timotae.service;

import com.daily.timotae.domain.Post;
import com.daily.timotae.dto.PostCreateRequestDto;
import com.daily.timotae.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BoardService {

    private final PostRepository postRepository;

    public BoardService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public void createPost(PostCreateRequestDto postCreateRequestDto){
        postRepository.savePost(postCreateRequestDto.toEntity());
    }

    @Transactional
    public void updatePost(Long postId, Post post){
        postRepository.changePost(postId, post);
    }

    public void deletePost(Long postId){
        postRepository.removePost(postId);
    }

    public void readPostAll(){
        postRepository.findPostAll();
    }

    public Optional<Post> readPostOneDetail(Long postId){
        return postRepository.findPostOne(postId);
    }
}
