package com.daily.timotae.repository;

import com.daily.timotae.domain.Post;

import java.util.List;

public interface PostRepository {
    Post savePost(Post post);
    List<Post> findAll();

    void clearStore();
}
