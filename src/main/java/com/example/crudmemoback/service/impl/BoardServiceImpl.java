package com.example.crudmemoback.service.impl;

import com.example.crudmemoback.dto.BoardDto;
import com.example.crudmemoback.entity.Board;
import com.example.crudmemoback.mapper.BoardMapper;
import com.example.crudmemoback.repository.BoardRepository;
import com.example.crudmemoback.service.BoardService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BoardServiceImpl implements BoardService {

    final BoardRepository boardRepository;
    final BoardMapper boardMapper;

    public BoardServiceImpl(BoardRepository boardRepository, BoardMapper boardMapper) {
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
    }

    // create
    @Override
    public BoardDto.ResBoard saveBoard(BoardDto.CreateReqBoard board){
        return boardRepository.save(board.toEntity()).toCreateResBoard();
    };

    // Read All
    @Override
    public List<BoardDto.DetailResBoard> getBoardList(){
        //mapper에서 받아오는 값 타입이랑 list의 타입이랑 달라서 에러가 생겼던 것!!!
        List<BoardDto.DetailResBoard> list = boardMapper.getBoardList(); //id만 반환
        List<BoardDto.DetailResBoard> newList = new ArrayList<>();

        for(BoardDto.DetailResBoard board : list){
            newList.add(getBoard(Long.parseLong(board.getBoardID())));
        }
        return newList;
    };

    // Read one
    @Override
    public BoardDto.DetailResBoard getBoard(Long boardID){
        BoardDto.DetailResBoard boardDB = boardMapper.getBoard(boardID);
        return boardDB;
    };

    // update
    @Override
    public void updateBoard(BoardDto.UpdateReqBoard board, Long boardID){
        Board boardDB = boardRepository.findById(boardID)
                .orElseThrow(() -> new RuntimeException("Board not found"));

        if (Objects.nonNull(board.getUserName()) &&
                !"".equalsIgnoreCase(
                        board.getUserName())){

            boardDB.setUserName(board.getUserName());
        }

        if (Objects.nonNull(board.getTitle()) &&
                !"".equalsIgnoreCase(
                        board.getTitle())){

            boardDB.setTitle(board.getTitle());
        }

        if (Objects.nonNull(board.getContents()) &&
                !"".equalsIgnoreCase(
                        board.getContents())){

            boardDB.setContents(board.getContents());
        }

        boardRepository.save(boardDB);
    };

    // Delete
    @Override
    public void deleteBoard(Long boardID){
        boardRepository.deleteById(boardID);
    };
}
