package com.daily.timotae.repository;

import com.daily.timotae.domain.Post;

import java.util.List;
import java.util.Map;

public interface PostRepository {

    Post savePost(Post post);
    Map<Long, Post> findPostAll();
    Post findPostOne(Long postId);
    void changePost(Long postId, Post post);
    void removePost(Long postId);

    List<Post> findAll();
    void clearStore();

}
