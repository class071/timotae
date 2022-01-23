package com.daily.timotae.repository;

import com.daily.timotae.domain.Post;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.Optional;

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
        Post tmpPost = findPostOne(postId)
                .orElseThrow( () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다. postId : " + postId));
        tmpPost.update(post.getTitle(), post.getCategory(), post.getUserId(), post.getDateOfIssue(), post.getContent());
    }

    @Override
    public void removePost(Long postId) {
        jpaPostRepository.deleteById(postId);
    }

}
