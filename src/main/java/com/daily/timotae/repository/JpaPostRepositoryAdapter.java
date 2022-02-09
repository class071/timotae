package com.daily.timotae.repository;

import com.daily.timotae.constant.SearchType;
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
        SearchType searchType = SearchType.valueOf(type);
        try {
            return searchType.getListBySearchType(jpaPostRepository, keyword);
        }
        catch(IllegalArgumentException e){
            throw new NotSupportSuchTypeException();
        }
    }
}