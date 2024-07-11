package com.sparta.spartakanbanboard.domain.board.dto;

import com.sparta.spartakanbanboard.domain.board.entity.Board;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardResponseDto {
    private String title;
    private String boardInfo;

    public static BoardResponseDto of(Board board) {
        return BoardResponseDto.builder()
            .title(board.getTitle())
            .boardInfo(board.getBoardInfo())
            .build();
    }
}
