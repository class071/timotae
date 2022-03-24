package com.daily.timotae.repository;

import com.daily.timotae.domain.Post;
import com.daily.timotae.domain.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import static com.daily.timotae.constant.PostConstant.POST_NOT_EXIST;
import static com.daily.timotae.constant.ReplyConstant.REPLY_NOT_EXIST;

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
    public void updateReply(Long replyId, Reply reply) {
        Reply newReply = findById(replyId)
                .orElseThrow( () -> new IllegalArgumentException(REPLY_NOT_EXIST + replyId));
        newReply.update(reply.getReplyContent());
    }

    public Optional<Reply> findById(Long replyId) {
        return jpaReplyRepository.findById(replyId);
    }

    @Override
    public List<Reply> findAllByPostId(long postId, Pageable pageable) {
        return jpaReplyRepository.findAllByPostId(postId, pageable);
    }

    @Override
    public List<Reply> findAllByParentReplyId(long parentReplyId, Pageable pageable) {
        return jpaReplyRepository.findAllByParentReplyId(parentReplyId, pageable);
    }
}
