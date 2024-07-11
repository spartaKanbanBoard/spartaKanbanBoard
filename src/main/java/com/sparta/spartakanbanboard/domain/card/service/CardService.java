package com.sparta.spartakanbanboard.domain.card.service;

import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;
import com.sparta.spartakanbanboard.domain.user.service.global.dto.CommonResponseDto;

public interface CardService {

    CommonResponseDto<?> createCardAtKanbanColumn(Long kanbanColumnId, CreateCardRequestDto requestDto);

    CommonResponseDto<?> findKanbanColumnIdAllCards(Long kanbanColumnId);
}
