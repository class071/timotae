package com.daily.timotae.repository;

import com.daily.timotae.domain.Post;

import java.util.List;
import java.util.Map;

public interface PostRepository {

    Post savePost(Post post);
    Post findByPostId(Long postId);
    Map<Long, Post> findAllWithPostId();

    List<Post> findAll();
    void clearStore();

    void changePost(Long postId, Post post);
}
