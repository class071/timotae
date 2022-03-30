package com.daily.timotae.controller;

import com.daily.timotae.constant.SuccessCode;
import com.daily.timotae.dto.*;
import com.daily.timotae.exception.post.NoSuchPostExist;
import com.daily.timotae.global.api.ApiResponse;
import com.daily.timotae.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/")
    public ApiResponse<PostResponseDto> create(@RequestBody PostCreateRequestDto postCreateRequestDto) {
        PostResponseDto postResponseDto = boardService.createPost(postCreateRequestDto);
        final SuccessCode successCode = SuccessCode.CREATE_SUCCESS;
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage() , postResponseDto);
    }

    @PutMapping("/{id}")
    public ApiResponse<PostResponseDto> update(@PathVariable Long id, @RequestBody PostUpdateRequestDto postUpdateRequestDto) {
        PostResponseDto postResponseDto = boardService.updatePost(id, postUpdateRequestDto);
        final SuccessCode successCode = SuccessCode.UPDATE_SUCCESS;
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage(), postResponseDto);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable Long id) {
        boardService.deletePost(id);
        final SuccessCode successCode = SuccessCode.DELETE_SUCCESS;
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage(), id);
    }

    @GetMapping("/readAll/")
    public ApiResponse<List<PostResponseDto>> readAll() {
        List<PostResponseDto> postResponseDtos = boardService.readPostAll();
        final SuccessCode successCode = SuccessCode.READ_SUCCESS;
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage() , postResponseDtos);
    }

    @GetMapping("/readOne/{id}")
    public ApiResponse<PostResponseDto> readOne(@PathVariable Long id) {
        PostResponseDto postResponseDto = boardService.readPostOne(id);
        final SuccessCode successCode = SuccessCode.READ_SUCCESS;
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage() , postResponseDto);
    }

    @GetMapping("/search/{type}/{keyword}")
    public ApiResponse<List<PostResponseDto>> search(@PathVariable String type, @PathVariable String keyword) {
        List<PostResponseDto> postResponseDtos = boardService.search(type, keyword);
        final SuccessCode successCode = SuccessCode.SEARCH_SUCCESS;
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage() , postResponseDtos);
    }

}
