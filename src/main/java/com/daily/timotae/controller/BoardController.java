package com.daily.timotae.controller;

import com.daily.timotae.constant.SuccessCode;
import com.daily.timotae.domain.Post;
import com.daily.timotae.dto.*;
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
    public void create(@RequestBody PostCreateRequestDto postCreateRequestDto) {
        boardService.createPost(postCreateRequestDto);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody PostUpdateRequestDto postUpdateRequestDto) {
        boardService.updatePost(id, postUpdateRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        boardService.deletePost(id);
    }

    @GetMapping("/readAll/")
    public ApiResponse<PostResponseDto> readAll() {
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
    public ApiResponse<PostResponseDto> search(@PathVariable String type, @PathVariable String keyword) {
        List<PostResponseDto> postResponseDtos = boardService.search(type, keyword);
        final SuccessCode successCode = SuccessCode.SEARCH_SUCCESS;
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage() , postResponseDtos);
    }

}
