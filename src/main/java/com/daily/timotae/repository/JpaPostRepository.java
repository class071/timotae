package com.daily.timotae.repository;

import com.daily.timotae.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaPostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleContaining(String keyword);
    List<Post> findByContentContaining(String keyword);
    List<Post> findByUserIdEquals(String keyword);
}
