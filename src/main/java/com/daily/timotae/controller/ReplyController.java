package com.daily.timotae.controller;

import com.daily.timotae.constant.SuccessCode;
import com.daily.timotae.dto.PostResponseDto;
import com.daily.timotae.dto.ReplyCreateRequestDto;
import com.daily.timotae.dto.ReplyResponseDto;
import com.daily.timotae.dto.ReplyUpdateRequestDto;
import com.daily.timotae.exception.post.NoSuchPostExist;
import com.daily.timotae.global.api.ApiResponse;
import com.daily.timotae.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/reply")
public class ReplyController {

    private final BoardService boardService;

    public ReplyController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/create")
    public ApiResponse<?> createReply(@RequestBody ReplyCreateRequestDto ReplyCreateRequestDto){
        ReplyResponseDto replyResponseDto = boardService.createReply(ReplyCreateRequestDto);
        final SuccessCode successCode = SuccessCode.CREATE_SUCCESS;
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage(), replyResponseDto);
    }

    @DeleteMapping("/delete/{replyId}")
    public ApiResponse<ReplyResponseDto> deleteReply(@PathVariable long replyId){
        boardService.deleteReply(replyId);
        final SuccessCode successCode = SuccessCode.DELETE_SUCCESS;
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage(), replyId);
    }

    @PutMapping("/update/{replyId}")
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
}
