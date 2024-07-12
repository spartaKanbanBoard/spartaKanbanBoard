package com.sparta.spartakanbanboard.domain.card.service;

import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.dto.EditCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.entity.Card;
import com.sparta.spartakanbanboard.domain.card.entity.State;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;

public interface CardService {


    CommonResponseDto<?> createCardAtKanbanColumn(Long kanbanColumnId,
        CreateCardRequestDto requestDto);

    CommonResponseDto<?> findKanbanColumnIdGetCards(Long kanbanColumnId, Long writerId,
        State state);

    CommonResponseDto<?> editFindKanbanColumnIdAndCard(Long kanbanColumnId, Long cardId,
        EditCardRequestDto editCardRequestDto);

    Card findById(long cardId);
}
