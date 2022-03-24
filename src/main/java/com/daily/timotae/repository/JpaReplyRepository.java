package com.daily.timotae.repository;

import com.daily.timotae.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface JpaReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByPostId(Long postId);
    List<Reply> findAllByParentReplyId(Long parentReplyId);
}
