package com.daily.timotae.repository;

import com.daily.timotae.domain.Post;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post savePost(Post post);
    List<Post> findPostAll();
    List<Post> findAllPaging(Pageable pageable);
    Optional<Post> findPostOne(Long postId);
    void changePost(Long postId, Post post);
    void removePost(Long postId);
    List<Post> searchPost(String type, String keyword, Pageable pageable);

}
