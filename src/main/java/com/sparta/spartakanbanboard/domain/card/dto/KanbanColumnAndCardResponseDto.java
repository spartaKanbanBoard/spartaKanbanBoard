package com.sparta.spartakanbanboard.domain.card.dto;

import com.sparta.spartakanbanboard.domain.column.dto.ColumnResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KanbanColumnAndCardResponseDto {

    private ColumnResponseDto column;
    private CardResponseDto card;

}
