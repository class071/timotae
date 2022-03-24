package com.daily.timotae.controller;

import com.daily.timotae.constant.SuccessCode;
import com.daily.timotae.dto.ReplyCreateRequestDto;
import com.daily.timotae.dto.ReplyResponseDto;
import com.daily.timotae.dto.ReplyUpdateRequestDto;
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
    public void createReply(@RequestBody ReplyCreateRequestDto ReplyCreateRequestDto){
        boardService.createReply(ReplyCreateRequestDto);
    }

    @DeleteMapping("/delete/{replyId}")
    public void deleteReply(@PathVariable long replyId){
        boardService.deleteReply(replyId);
    }

    @PutMapping("/update/{replyId}")
    public void updateReply(@PathVariable long replyId, @RequestBody ReplyUpdateRequestDto replyUpdateRequestDto){
        boardService.updateReply(replyId, replyUpdateRequestDto);
    }

    @GetMapping("/readPostReply/{postId}")
    public ApiResponse<ReplyResponseDto> readAllByPostId(@PathVariable long postId){
        final SuccessCode successCode = SuccessCode.READ_SUCCESS;
        List<ReplyResponseDto> replyResponseDtos = boardService.findAllByPostId(postId);
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage() , replyResponseDtos);
    }

    @GetMapping("/re-reply/{parentReplyId}")
    public ApiResponse<ReplyResponseDto> readAllByParentReplyId(@PathVariable long parentReplyId) {
        final SuccessCode successCode = SuccessCode.READ_SUCCESS;
        List<ReplyResponseDto> replyResponseDtos = boardService.findAllByParentReplyId(parentReplyId);
        return ApiResponse.success(successCode.name(), successCode.getHttpStatus(),
                successCode.getMessage() , replyResponseDtos);
    }
}
