package com.daily.timotae.controller;

import com.daily.timotae.constant.SuccessCode;
import com.daily.timotae.dto.PostResponseDto;
import com.daily.timotae.dto.ReplyCreateRequestDto;
import com.daily.timotae.dto.ReplyResponseDto;
import com.daily.timotae.dto.ReplyUpdateRequestDto;
import com.daily.timotae.exception.post.NoSuchPostExist;
import com.daily.timotae.exception.user.UserNotAuthorized;
import com.daily.timotae.exception.user.UserNotLogin;
import com.daily.timotae.global.api.ApiResponse;
import com.daily.timotae.global.config.security.CustomUserDetails;
import com.daily.timotae.service.BoardService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reply")
public class ReplyController {

    private final BoardService boardService;

    public ReplyController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/")
    public ApiResponse<?> createReply(@RequestBody ReplyCreateRequestDto ReplyCreateRequestDto){
        ReplyResponseDto replyResponseDto = boardService.createReply(ReplyCreateRequestDto);
        final SuccessCode successCode = SuccessCode.CREATE_SUCCESS;
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage(), replyResponseDto);
    }

    @DeleteMapping("/{replyId}")
    public ApiResponse<ReplyResponseDto> deleteReply(@PathVariable long replyId){
        boardService.deleteReply(replyId);
        final SuccessCode successCode = SuccessCode.DELETE_SUCCESS;
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage(), replyId);
    }

    @PutMapping("/{replyId}")
    public ApiResponse<ReplyResponseDto> updateReply(@PathVariable long replyId, @RequestBody ReplyUpdateRequestDto replyUpdateRequestDto){
        ReplyResponseDto replyResponseDto = boardService.updateReply(replyId, replyUpdateRequestDto);
        final SuccessCode successCode = SuccessCode.UPDATE_SUCCESS;
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage(), replyResponseDto);
    }

    @GetMapping("/readPostReply/{postId}")
    public ApiResponse<List<ReplyResponseDto>> readAllByPostId(@PathVariable long postId){
        final SuccessCode successCode = SuccessCode.READ_SUCCESS;
        List<ReplyResponseDto> replyResponseDtos = boardService.findAllByPostId(postId);
        return  ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage() , replyResponseDtos);
    }

    @GetMapping("/re-reply/{parentReplyId}")
    public ApiResponse<List<ReplyResponseDto>> readAllByParentReplyId(@PathVariable long parentReplyId) {
        final SuccessCode successCode = SuccessCode.READ_SUCCESS;
        List<ReplyResponseDto> replyResponseDtos = boardService.findAllByParentReplyId(parentReplyId);
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage() , replyResponseDtos);
    }

    public boolean checkUser(long replyId) {
        try{
            CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            long id = Long.valueOf(customUserDetails.getUserId());
            long replyUserId = boardService.readReplyOne(replyId).getUserId();
            if(id == replyUserId){
                return true;
            }else {
                throw new UserNotAuthorized(); // 해당 권한을 가진 User 가 아님.
            }
        }catch(IllegalStateException e){ // 로그인이 되어 있지 않음
            throw new UserNotLogin();
        }
    }
}
