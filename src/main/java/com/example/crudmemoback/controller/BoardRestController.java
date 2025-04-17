package com.example.crudmemoback.controller;

import com.example.crudmemoback.dto.BoardDto;
import com.example.crudmemoback.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/board")
@RestController
public class BoardRestController {

    final BoardService boardService;
    public BoardRestController(BoardService boardService) {
        this.boardService = boardService;
    }

    // Create
    @PostMapping("")
    public BoardDto.ResBoard saveBoard(@RequestBody BoardDto.CreateReqBoard board) {
        return boardService.saveBoard(board);
    }

    // Read all
    @GetMapping("")
    public List<BoardDto.DetailResBoard>  findAllBoards() {
        return boardService.getBoardList();
    }

    // Read one
    @GetMapping("/{id}")
    public BoardDto.DetailResBoard findBoard(@PathVariable("id") Long boardID) {
        return boardService.getBoard(boardID); // string -> Long
    }

    // Update
    @PutMapping("/{id}")
    public void updateBoard(@RequestBody BoardDto.UpdateReqBoard board,
                            @PathVariable("id") Long boardID) {
        boardService.updateBoard(board,boardID); // string -> Long
    }

    // Delete
    @DeleteMapping("/{id}")
    public String deleteBoard(@PathVariable("id") Long boardID) {
        boardService.deleteBoard(boardID); // string -> Long
        return "Deleted Successfully";
    }
}
