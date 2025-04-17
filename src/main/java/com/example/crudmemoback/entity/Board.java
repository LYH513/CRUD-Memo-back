package com.example.crudmemoback.entity;

import com.example.crudmemoback.dto.BoardDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@Data
@EntityListeners(AuditingEntityListener.class) // 로컬 데이터를 보여주기 위함!
@Entity
@Table(name= "Board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardID;
    private String userName;
    private String title;
    private String contents;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    LocalDateTime createdAt;

    protected Board() {}

    private Board(Long boardID, String userName, String title, String contents, LocalDateTime createdAt) {
        this.boardID = boardID;
        this.userName = userName;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
    }

    public static Board of(Long boardID, String userName, String title, String contents, LocalDateTime createdAt) {
        return new Board(boardID, userName, title, contents, createdAt);
    }

    public BoardDto.ResBoard toCreateResBoard() {
        return BoardDto.ResBoard.builder()
                .boardID(String.valueOf(getBoardID())) // Long -> String
                .build();
    }
}
