package com.example.crudmemoback.dto;

import com.example.crudmemoback.entity.Board;
import lombok.*;

public class BoardDto {

    // Create Request
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqBoard {
        String userName;
        String title;
        String contents;

        public Board toEntity(){return Board.of(
                null,
                getUserName(),
                getTitle(),
                getContents(),
                null
        );}
    }

    // Read / Delete request
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ReqBoard {
        String boardID;
    }

    // Create/ Read response
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ResBoard {
        String boardID;
    }

    // Detail Response
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResBoard {
        String boardID;
        String userName;
        String title;
        String contents;
        String createdAt;
    }

    // update request
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqBoard {
        String boardID;
        String userName;
        String title;
        String contents;
    }

}
