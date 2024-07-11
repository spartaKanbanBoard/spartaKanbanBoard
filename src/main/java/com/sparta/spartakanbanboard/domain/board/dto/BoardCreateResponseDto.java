package com.sparta.spartakanbanboard.domain.board.dto;

import com.sparta.spartakanbanboard.domain.board.entity.Board;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardCreateResponseDto {
    private String title;
    private String boardInfo;

    public static BoardCreateResponseDto of(Board board) {
        return BoardCreateResponseDto.builder()
            .title(board.getTitle())
            .boardInfo(board.getBoardInfo())
            .build();
    }
}
