package com.daily.timotae.repository;

import com.daily.timotae.domain.Post;
import com.daily.timotae.exception.post.NotSupportSuchTypeException;

import java.util.List;
import java.util.Optional;

import static com.daily.timotae.constant.PostConstant.POST_NOT_EXIST;

public class JpaPostRepositoryAdapter implements PostRepository {

    private JpaPostRepository jpaPostRepository;

    @Override
    public Post savePost(Post post) {
        return jpaPostRepository.save(post);
    }

    @Override
    public List<Post> findPostAll() {
        return jpaPostRepository.findAll();
    }

    @Override
    public Optional<Post> findPostOne(Long postId) {
        return jpaPostRepository.findById(postId);
    }

    @Override
    public void changePost(Long postId, Post post) {
        Post newPost = findPostOne(postId)
                .orElseThrow( () -> new IllegalArgumentException(POST_NOT_EXIST + postId));
        newPost.update(post.getTitle(), post.getCategory(), post.getUserId(), post.getContent());
    }

    @Override
    public void removePost(Long postId) {
        jpaPostRepository.deleteById(postId);
    }

    @Override
    public List<Post> searchPost(String type, String keyword) {
        if (isTitle(type)) {
            return jpaPostRepository.findByTitleContaining(keyword);
        } else if (isContent(type)) {
            return jpaPostRepository.findByContentContaining(keyword);
        } else if (isUserId(type)) {
            return jpaPostRepository.findByUserIdEquals(keyword);
        } else{
            throw new NotSupportSuchTypeException();
        }
    }

    private boolean isTitle(String type){
        if(type.equalsIgnoreCase("title")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isContent(String type){
        if(type.equalsIgnoreCase("content")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isUserId(String type){
        if(type.equalsIgnoreCase("userId")) {
            return true;
        } else {
            return false;
        }
    }
}
