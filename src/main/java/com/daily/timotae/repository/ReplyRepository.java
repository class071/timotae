package com.daily.timotae.repository;

import com.daily.timotae.domain.Reply;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface ReplyRepository {
    Reply saveReply(Reply reply);
    void deleteReply(Long replyId);
    void updateReply(Long replyId, Reply reply);
    Optional<Reply> findById(Long replyId);
    List<Reply> findAllByPostId(long postId, Pageable pageable);
    List<Reply> findAllByParentReplyId(long parentReplyId, Pageable pageable);
}
