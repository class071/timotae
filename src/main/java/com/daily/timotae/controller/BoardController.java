package com.daily.timotae.controller;

import com.daily.timotae.domain.Post;
import com.daily.timotae.dto.*;
import com.daily.timotae.service.BoardService;
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
    public List<PostResponseDto> readAll() {
        return boardService.readPostAll();
    }

    @GetMapping("/readOne/{id}")
    public PostResponseDto readOne(@PathVariable Long id) {
        return boardService.readPostOne(id);
    }

    @GetMapping("/searchPost/{type}/{keyword}")
    public List<PostResponseDto> search(@PathVariable String type, @PathVariable String keyword, Pageable pageable) {
        return boardService.search(type, keyword, pageable);
    }

    @GetMapping("/paging")
    public List<PostResponseDto> findAllPaging(Pageable pageable) {
        return boardService.findAllPaging(pageable);
    }

    @GetMapping("/reply/create")
    public void createReply(@RequestBody ReplyCreateRequestDto ReplyCreateRequestDto, @RequestParam int depth){
        boardService.createReply(ReplyCreateRequestDto, depth);
    }

    @DeleteMapping("/reply/delete/{replyId}")
    public void deleteReply(@PathVariable long replyId){
        boardService.deleteReply(replyId);
    }

    @PutMapping("/reply/update/{replyId}")
    public void updateReply(@PathVariable long replyId, @RequestBody ReplyUpdateRequestDto replyUpdateRequestDto){
        boardService.updateReply(replyId, replyUpdateRequestDto);
    }
    @GetMapping("/reply/readPostReply/{postId}")
    public List<ReplyResponseDto> readAllByPostId(@PathVariable long postId, Pageable pageable){
        return boardService.findAllByPostId(postId, pageable);
    }

    @GetMapping("/reply/re_reply/{parentReplyId}")
    public List<ReplyResponseDto> readAllByParentReplyId(@PathVariable long parentReplyId, Pageable pageable) {
        return boardService.findAllByParentReplyId(parentReplyId, pageable);
    }
}

