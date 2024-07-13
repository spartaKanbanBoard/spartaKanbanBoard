package com.sparta.spartakanbanboard.domain.card.dto;

import com.sparta.spartakanbanboard.domain.column.dto.ColumnResponseDto;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KanbanColumnAndCardListAndUserResponseDto {

    private ColumnResponseDto column;
    private List<CardResponseDto> cardList;
}
