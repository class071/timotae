package com.daily.timotae.controller;

import com.daily.timotae.constant.SuccessCode;
import com.daily.timotae.domain.Post;
import com.daily.timotae.dto.*;
import com.daily.timotae.exception.post.NoSuchPostExist;
import com.daily.timotae.global.api.ApiResponse;
import com.daily.timotae.service.BoardService;
import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/create")
    public ApiResponse<?> create(@RequestBody PostCreateRequestDto postCreateRequestDto) {
        PostResponseDto postResponseDto = boardService.createPost(postCreateRequestDto);
        final SuccessCode successCode = SuccessCode.CREATE_SUCCESS;
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage() , postResponseDto);
    }

    @PutMapping("/update/{id}")
    public ApiResponse<?> update(@PathVariable Long id, @RequestBody PostUpdateRequestDto postUpdateRequestDto) {
        PostResponseDto postResponseDto = boardService.updatePost(id, postUpdateRequestDto);
        final SuccessCode successCode = SuccessCode.UPDATE_SUCCESS;
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage(), postResponseDto);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<?> delete(@PathVariable Long id) {
        try{
            boardService.deletePost(id);
        } catch(IllegalArgumentException e){
            throw new NoSuchPostExist();
        }
        final SuccessCode successCode = SuccessCode.DELETE_SUCCESS;
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage(), id);
    }

    @GetMapping("/readAll/")
    public ApiResponse<?> readAll() {
        List<PostResponseDto> postResponseDtos = boardService.readPostAll();
        final SuccessCode successCode = SuccessCode.READ_SUCCESS;
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage() , postResponseDtos);
    }

    @GetMapping("/readOne/{id}")
    public ApiResponse<?> readOne(@PathVariable Long id) {
        PostResponseDto postResponseDto = boardService.readPostOne(id);
        final SuccessCode successCode = SuccessCode.READ_SUCCESS;
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage() , postResponseDto);
    }

    @GetMapping("/search/{type}/{keyword}")
    public ApiResponse<?> search(@PathVariable String type, @PathVariable String keyword) {
        List<PostResponseDto> postResponseDtos = boardService.search(type, keyword);
        final SuccessCode successCode = SuccessCode.SEARCH_SUCCESS;
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage() , postResponseDtos);
    }

}
