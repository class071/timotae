package com.daily.timotae.controller;

import com.daily.timotae.domain.Post;
import com.daily.timotae.dto.PostCreateRequestDto;
import com.daily.timotae.dto.PostResponseDto;
import com.daily.timotae.dto.PostUpdateRequestDto;
import com.daily.timotae.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/board")
public class BoardController { // 삭제 수정 등록 조회

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

    @GetMapping("/readall/")
    public List<PostResponseDto> readAll(){
        return boardService.readPostAll();
    }

    @GetMapping("/readone/{id}")
    public PostResponseDto readOne(@PathVariable Long id){
        return boardService.readPostOne(id);
    }

    @GetMapping("/searchPost/{type}/{keyword}")
    public List<PostResponseDto> search(@PathVariable String type, @PathVariable String keyword){
        return boardService.search(type, keyword);
    }

}