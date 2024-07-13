package com.sparta.spartakanbanboard.domain.board.dto;

import com.sparta.spartakanbanboard.domain.board.entity.Board;
import com.sparta.spartakanbanboard.domain.column.entity.KanbanColumn;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardDetailsResponseDto {
    private String boardTitle;
    private String boardInfo;
    private List<KanbanDetailsResponseDto> kanbanDetailsResponseDtoList;

    public static BoardDetailsResponseDto of(Board board,List<KanbanDetailsResponseDto> kanbanDetailsResponseDtoList) {
        return BoardDetailsResponseDto.builder()
            .boardTitle(board.getTitle())
            .boardInfo(board.getBoardInfo())
            .kanbanDetailsResponseDtoList(kanbanDetailsResponseDtoList)
            .build();
    }
}
