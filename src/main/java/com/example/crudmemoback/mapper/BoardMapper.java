package com.example.crudmemoback.mapper;

import com.example.crudmemoback.dto.BoardDto;
import java.util.List;

public interface BoardMapper {
    // Read All
    List<BoardDto.DetailResBoard> getBoardList();
    // Read one
    BoardDto.DetailResBoard getBoard(Long boardID);
}
