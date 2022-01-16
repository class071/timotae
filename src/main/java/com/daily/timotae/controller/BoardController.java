package com.daily.timotae.controller;

import com.daily.timotae.domain.Post;
import com.daily.timotae.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController { // 삭제 수정 등록 조회

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/{id}")
    public void create(@PathVariable Post post) {
        boardService.createPost(post);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Post post) {
        boardService.updatePost(id, post);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        boardService.deletePost(id);
    }

    @GetMapping("/readall/")
    public void readAll(){
        boardService.readPostAll();
    }

    @GetMapping("/readone/{id}")
    public Post readOne(@PathVariable Long id){
        return boardService.readPostOneDetail(id);
    }

}