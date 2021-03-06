package com.daily.timotae.service;

import com.daily.timotae.domain.Post;
import com.daily.timotae.domain.Reply;
import com.daily.timotae.dto.*;
import com.daily.timotae.exception.post.NoSuchPostExist;
import com.daily.timotae.exception.reply.NoMoreReply;
import com.daily.timotae.exception.reply.NoParentReplyExist;
import com.daily.timotae.exception.reply.NoSuchReplyExist;
import com.daily.timotae.repository.PostRepository;

import com.daily.timotae.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;

    public PostResponseDto createPost(PostCreateRequestDto postCreateRequestDto){
        Post saved = postRepository.savePost(postCreateRequestDto.toEntity());
        return new PostResponseDto(saved);
    }

    @Transactional
    public PostResponseDto updatePost(Long postId, PostUpdateRequestDto postUpdateRequestDto){
        Post updated = postRepository.changePost(postId, postUpdateRequestDto.toEntity());
        return new PostResponseDto(updated);
    }

    public void deletePost(Long postId){
        try{
            postRepository.removePost(postId);
        } catch(IllegalArgumentException e){
            throw new NoSuchPostExist();
        }
    }

    public List<PostResponseDto> readPostAll(){
        List<Post> posts = postRepository.findPostAll();
        List<PostResponseDto> postDtos = new ArrayList<>();

        for(Post post : posts){
            postDtos.add(new PostResponseDto(post));
        }

        return postDtos;
    }

    public PostResponseDto readPostOne(Long postId){
        Post newPost = postRepository.findPostOne(postId)
                .orElseThrow(() -> new NoSuchPostExist());
        return new PostResponseDto(newPost);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> search(String type, String keyword){
        List<Post> posts = postRepository.searchPost(type, keyword);
        List<PostResponseDto> postDtos = new ArrayList<>();

        for(Post post : posts){
            postDtos.add(new PostResponseDto(post));
        }

        return postDtos;
    }

    public ReplyResponseDto createReply(ReplyCreateRequestDto replyCreateRequestDto) {
        if(replyCreateRequestDto.getDepth() > 2) {
            throw new NoMoreReply();
        }
        if(checkParentExist(replyCreateRequestDto)) {
            Reply created = replyRepository.saveReply(replyCreateRequestDto.toEntity());
            return new ReplyResponseDto(created);
        } else{
            throw new NoParentReplyExist();
        }
    }

    public boolean checkParentExist(ReplyCreateRequestDto replyCreateRequestDto){
        if(replyRepository.findById(replyCreateRequestDto.getParentReplyId()).isPresent())
            return true;
        else
            return false;
    }

    public void deleteReply(long replyId){
        try{
            replyRepository.deleteReply(replyId);
        } catch(IllegalArgumentException e){
            throw new NoSuchPostExist();
        }
    }

    @Transactional(readOnly = true)
    public List<ReplyResponseDto> findAllByPostId(long postId){
        List<Reply> replies = replyRepository.findAllByPostId(postId);
        List<ReplyResponseDto> replyDtos = new ArrayList<>();

        for(Reply reply : replies){
            replyDtos.add(new ReplyResponseDto(reply));
        }

        return replyDtos;
    }

    @Transactional(readOnly = true)
    public List<ReplyResponseDto> findAllByParentReplyId(long parentReplyId){
        List<Reply> replies = replyRepository.findAllByParentReplyId(parentReplyId);
        List<ReplyResponseDto> replyDtos = new ArrayList<>();

        for(Reply reply : replies){
            if(reply.getParentReplyId() == reply.getReplyId()) {
                continue;
            }
            replyDtos.add(new ReplyResponseDto(reply));
        }

        return replyDtos;
    }

    public ReplyResponseDto updateReply(long replyId, ReplyUpdateRequestDto replyUpdateRequestDto) {
        Reply updated = replyRepository.updateReply(replyId, replyUpdateRequestDto.toEntity());
        return new ReplyResponseDto(updated);
    }

    public ReplyResponseDto readReplyOne(long replyId){
        Reply newReply = replyRepository.findById(replyId)
                .orElseThrow(() -> new NoSuchReplyExist());
        return new ReplyResponseDto(newReply);
    }

    @Transactional
    public void viewCountUp(long postId) {
        postRepository.viewCountUp(postId);
    }
}
