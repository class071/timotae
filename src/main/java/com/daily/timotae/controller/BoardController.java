package com.daily.timotae.controller;

import com.daily.timotae.constant.SuccessCode;
import com.daily.timotae.dto.*;
import com.daily.timotae.exception.user.UserNotAuthorized;
import com.daily.timotae.exception.user.UserNotLogin;
import com.daily.timotae.global.api.ApiResponse;
import com.daily.timotae.global.config.security.CustomUserDetails;
import com.daily.timotae.service.BoardService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        checkUser(id);
        PostResponseDto postResponseDto = boardService.updatePost(id, postUpdateRequestDto);
        final SuccessCode successCode = SuccessCode.UPDATE_SUCCESS;
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage(), postResponseDto);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable Long id) {
        checkUser(id);
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
    public ApiResponse<PostResponseDto> readOne(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
        viewCountUp(id, request, response);
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

    public boolean checkUser(long postId) {
        try{
            CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            long id = Long.valueOf(customUserDetails.getUserId());
            long postUserId = boardService.readPostOne(postId).getUserId();
            if(id == postUserId){
                return true;
            }else {
                throw new UserNotAuthorized(); // 해당 권한을 가진 User 가 아님.
            }
        }catch(IllegalStateException e){ // 로그인이 되어 있지 않음
            throw new UserNotLogin();
        }
    }

    private void viewCountUp(Long postId, HttpServletRequest request, HttpServletResponse response) {
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("postView")) {
                    oldCookie = cookie;
                }
            }
        }

        if (oldCookie != null) {
            if (!oldCookie.getValue().contains("[" + postId.toString() + "]")) {
                boardService.viewCountUp(postId);
                oldCookie.setValue(oldCookie.getValue() + "_[" + postId + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(oldCookie);
            }
        } else {
            boardService.viewCountUp(postId);
            Cookie newCookie = new Cookie("postView","[" + postId + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(newCookie);
        }
    }
}
