package com.daily.timotae.repository;

import com.daily.timotae.domain.Post;
import com.daily.timotae.dto.PostResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface JpaPostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleContaining(String keyword);
    List<Post> findByContentContaining(String keyword);
    List<Post> findByUserIdEquals(String keyword);
    List<Post> findAll();
}
