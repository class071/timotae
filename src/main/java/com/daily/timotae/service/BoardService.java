package com.daily.timotae.service;

import com.daily.timotae.domain.Post;
import com.daily.timotae.domain.Reply;
import com.daily.timotae.dto.*;
import com.daily.timotae.exception.post.NotSupportSuchTypeException;
import com.daily.timotae.exception.reply.NoMoreReply;
import com.daily.timotae.repository.PostRepository;

import com.daily.timotae.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<Post> postEntityList = postRepository.findPostAll();
        List<PostResponseDto> postDtoList = new ArrayList<>();

        for(Post post : postEntityList){
            postDtoList.add(new PostResponseDto(post));
        }

        return postDtoList;
    }

    public PostResponseDto readPostOne(Long postId){
        Post newPost = postRepository.findPostOne(postId)
                .orElseThrow(() -> new IllegalArgumentException(POST_NOT_EXIST + postId));
        return new PostResponseDto(newPost);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> search(String type, String keyword, Pageable pageable){
        List<Post> postEntityList = postRepository.searchPost(type, keyword, pageable);
        List<PostResponseDto> postDtoList = new ArrayList<>();

        for(Post post : postEntityList){
            postDtoList.add(new PostResponseDto(post));
        }

        return postDtoList;
    }

    public List<PostResponseDto> findAllPaging(Pageable pageable) {
        List<Post> postEntityList = postRepository.findAllPaging(pageable);
        List<PostResponseDto> postDtoList = new ArrayList<>();

        for(Post post : postEntityList){
            postDtoList.add(new PostResponseDto(post));
        }

        return postDtoList;
    }

    public void createReply(ReplyCreateRequestDto replyCreateRequestDto, int depth) {
        if(depth > 2) {
            throw new NoMoreReply();
        }
        replyRepository.saveReply(replyCreateRequestDto.toEntity());
    }

    public void deleteReply(long replyId){
        replyRepository.deleteReply(replyId);
    }

    @Transactional(readOnly = true)
    public List<ReplyResponseDto> findAllByPostId(long postId, Pageable pageable){
        List<Reply> replyEntityList = replyRepository.findAllByPostId(postId, pageable);
        List<ReplyResponseDto> replyDtoList = new ArrayList<>();

        for(Reply reply : replyEntityList){
            replyDtoList.add(new ReplyResponseDto(reply));
        }

        return replyDtoList;
    }

    @Transactional(readOnly = true)
    public List<ReplyResponseDto> findAllByParentReplyId(long parentReplyId, Pageable pageable){
        List<Reply> replyEntityList = replyRepository.findAllByParentReplyId(parentReplyId, pageable);
        List<ReplyResponseDto> replyDtoList = new ArrayList<>();

        for(Reply reply : replyEntityList){
            if(reply.getParentReplyId() == reply.getReplyId()) {
                continue;
            }
            replyDtoList.add(new ReplyResponseDto(reply));
        }

        return replyDtoList;
    }

    public void updateReply(long replyId, ReplyUpdateRequestDto replyUpdateRequestDto) {
        replyRepository.updateReply(replyId, replyUpdateRequestDto.toEntity());
    }
}
