package com.daily.timotae.repository;

import com.daily.timotae.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaPostRepository extends JpaRepository<Post, Long>, PostRepository {

}
