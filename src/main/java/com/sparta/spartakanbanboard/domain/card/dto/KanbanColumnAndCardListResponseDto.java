package com.sparta.spartakanbanboard.domain.card.dto;

import com.sparta.spartakanbanboard.domain.column.dto.ColumnResponseDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KanbanColumnAndCardListResponseDto {

    private ColumnResponseDto column;
    private List<CardResponseDto> cardList;
}
