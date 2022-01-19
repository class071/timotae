package com.daily.timotae.repository;

import com.daily.timotae.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post savePost(Post post);
    List<Post> findPostAll();
    Optional<Post> findPostOne(Long postId);
    void changePost(Long postId, Post post);
    void removePost(Long postId);

}
