package com.daily.timotae.repository;

import com.daily.timotae.domain.Reply;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {
    Reply saveReply(Reply reply);
    void deleteReply(Long replyId);
    Reply updateReply(Long replyId, Reply reply);
    Optional<Reply> findById(Long replyId);
    List<Reply> findAllByPostId(long postId);
    List<Reply> findAllByParentReplyId(long parentReplyId);
}
