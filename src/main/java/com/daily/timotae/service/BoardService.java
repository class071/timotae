package com.daily.timotae.service;

import com.daily.timotae.domain.Post;
import com.daily.timotae.dto.PostCreateRequestDto;
import com.daily.timotae.dto.PostResponseDto;
import com.daily.timotae.dto.PostUpdateRequestDto;
import com.daily.timotae.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import static com.daily.timotae.constant.PostConstant.POST_NOT_EXIST;

@Service
public class BoardService {

    private final PostRepository postRepository;

    public BoardService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

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
}
