package com.sparta.spartakanbanboard.domain.board.dto;

import com.sparta.spartakanbanboard.domain.card.entity.Card;
import com.sparta.spartakanbanboard.domain.column.entity.KanbanColumn;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KanbanDetailsResponseDto {

    private String columnTitle;
    private Long id;
    private List<CardDetailsResponseDto> cardResponseList;

    public static KanbanDetailsResponseDto of(KanbanColumn kanbanColumn) {

        List<CardDetailsResponseDto> cardDetailsResponseDtoList = kanbanColumn.getCardList().stream()
            .map(CardDetailsResponseDto::of)
            .toList();

        return KanbanDetailsResponseDto.builder()
            .columnTitle(kanbanColumn.getColumnTitle())
            .id(kanbanColumn.getId())
            .cardResponseList(cardDetailsResponseDtoList)
            .build();
    }
}
