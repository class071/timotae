package com.daily.timotae.service;

import com.daily.timotae.domain.Post;
import com.daily.timotae.domain.Reply;
import com.daily.timotae.dto.*;
import com.daily.timotae.exception.reply.NoMoreReply;
import com.daily.timotae.exception.reply.NoParentReplyExist;
import com.daily.timotae.repository.PostRepository;

import com.daily.timotae.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import static com.daily.timotae.constant.PostConstant.POST_NOT_EXIST;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;

    public void createPost(PostCreateRequestDto postCreateRequestDto){
        postRepository.savePost(postCreateRequestDto.toEntity());
    }

    @Transactional
    public void updatePost(Long postId, PostUpdateRequestDto postUpdateRequestDto){
        postRepository.changePost(postId, postUpdateRequestDto.toEntity());
    }

    public void deletePost(Long postId){
        postRepository.removePost(postId);
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
                .orElseThrow(() -> new IllegalArgumentException(POST_NOT_EXIST + postId));
        return new PostResponseDto(newPost);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> search(String type, String keyword, Pageable pageable){
        List<Post> posts = postRepository.searchPost(type, keyword, pageable);
        List<PostResponseDto> postDtos = new ArrayList<>();

        for(Post post : posts){
            postDtos.add(new PostResponseDto(post));
        }

        return postDtos;
    }

    public List<PostResponseDto> findAllPaging(Pageable pageable) {
        List<Post> posts = postRepository.findAllPaging(pageable);
        List<PostResponseDto> postDtos = new ArrayList<>();

        for(Post post : posts){
            postDtos.add(new PostResponseDto(post));
        }

        return postDtos;
    }

    public void createReply(ReplyCreateRequestDto replyCreateRequestDto) {
        if(replyCreateRequestDto.getDepth() > 2) {
            throw new NoMoreReply();
        }

        if(checkParentExist(replyCreateRequestDto))
            replyRepository.saveReply(replyCreateRequestDto.toEntity());
        else{
            throw new NoParentReplyExist();
        }
    }

    public boolean checkParentExist(ReplyCreateRequestDto replyCreateRequestDto){
        if(replyCreateRequestDto.getParentReplyId() == null)
            return false;
        else
            return true;
    }

    public void deleteReply(long replyId){
        replyRepository.deleteReply(replyId);
    }

    @Transactional(readOnly = true)
    public List<ReplyResponseDto> findAllByPostId(long postId, Pageable pageable){
        List<Reply> replies = replyRepository.findAllByPostId(postId, pageable);
        List<ReplyResponseDto> replyDtos = new ArrayList<>();

        for(Reply reply : replies){
            replyDtos.add(new ReplyResponseDto(reply));
        }

        return replyDtos;
    }

    @Transactional(readOnly = true)
    public List<ReplyResponseDto> findAllByParentReplyId(long parentReplyId, Pageable pageable){
        List<Reply> replies = replyRepository.findAllByParentReplyId(parentReplyId, pageable);
        List<ReplyResponseDto> replyDtos = new ArrayList<>();

        for(Reply reply : replies){
            if(reply.getParentReplyId() == reply.getReplyId()) {
                continue;
            }
            replyDtos.add(new ReplyResponseDto(reply));
        }

        return replyDtos;
    }

    public void updateReply(long replyId, ReplyUpdateRequestDto replyUpdateRequestDto) {
        replyRepository.updateReply(replyId, replyUpdateRequestDto.toEntity());
    }
}
