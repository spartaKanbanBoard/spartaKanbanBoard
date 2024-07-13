package com.sparta.spartakanbanboard.domain.board.dto;

import com.sparta.spartakanbanboard.domain.column.entity.KanbanColumn;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardDetailsResponseDto {
    private List<KanbanDetailsResponseDto> kanbanDetailsResponseDtoList;

    public static BoardDetailsResponseDto of(List<KanbanDetailsResponseDto> kanbanDetailsResponseDtoList) {
        return BoardDetailsResponseDto.builder()
            .kanbanDetailsResponseDtoList(kanbanDetailsResponseDtoList)
            .build();
    }
}
