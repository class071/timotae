package com.daily.timotae.repository;

import com.daily.timotae.domain.Reply;
import com.daily.timotae.exception.reply.NoSuchReplyExist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class JpaReplyRepositoryAdapter implements ReplyRepository{

    private final JpaReplyRepository jpaReplyRepository;

    @Override
    public Reply saveReply(Reply reply) {
        return jpaReplyRepository.save(reply);
    }

    @Override
    public void deleteReply(Long replyId) {
        jpaReplyRepository.deleteById(replyId);
    }

    @Override
    public Reply updateReply(Long replyId, Reply reply) {
        Reply newReply = findById(replyId)
                .orElseThrow( () -> new NoSuchReplyExist());
        newReply.update(reply.getReplyContent());
        return newReply;
    }

    public Optional<Reply> findById(Long replyId) {
        return jpaReplyRepository.findById(replyId);
    }

    @Override
    public List<Reply> findAllByPostId(long postId) {
        return jpaReplyRepository.findAllByPostId(postId);
    }

    @Override
    public List<Reply> findAllByParentReplyId(long parentReplyId) {
        return jpaReplyRepository.findAllByParentReplyId(parentReplyId);
    }
}
