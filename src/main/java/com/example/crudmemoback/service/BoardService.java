package com.example.crudmemoback.service;

import com.example.crudmemoback.dto.BoardDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
    // create
    BoardDto.ResBoard saveBoard(BoardDto.CreateReqBoard board);
    // Read All
    List<BoardDto.DetailResBoard> getBoardList();
    // Read one
    BoardDto.DetailResBoard getBoard(Long boardID);
    // update
    void updateBoard(BoardDto.UpdateReqBoard board, Long boardID);
    // Delete
    void deleteBoard(Long boardID);
}
