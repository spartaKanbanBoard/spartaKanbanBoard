package com.sparta.spartakanbanboard.domain.board.dto;

import com.sparta.spartakanbanboard.domain.board.entity.Board;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardListResponseDto {
    private List<Board> boardList;

    public static BoardListResponseDto of(List<Board> boardList) {
        return BoardListResponseDto.builder()
            .boardList(boardList)
            .build();
    }
}
