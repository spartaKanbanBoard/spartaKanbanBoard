package com.sparta.spartakanbanboard.domain.card.service;

import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.dto.DeleteCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.dto.EditCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.dto.MoveLocationRequestDto;
import com.sparta.spartakanbanboard.domain.card.entity.Card;
import com.sparta.spartakanbanboard.domain.card.entity.State;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import com.sparta.spartakanbanboard.global.security.UserDetailsImpl;

public interface CardService {

    CommonResponseDto<?> createCardAtKanbanColumn(Long kanbanColumnId,
        CreateCardRequestDto requestDto, UserDetailsImpl userDetails);

    CommonResponseDto<?> findKanbanColumnIdGetCards(Long kanbanColumnId, String username,
        State state);

    CommonResponseDto<?> editFindKanbanColumnIdAndCard(Long kanbanColumnId,
        EditCardRequestDto editCardRequestDto, UserDetailsImpl userDetails);

    CommonResponseDto<?> moveLocationCards(Long kanbanColumnId, Long cardId,
        MoveLocationRequestDto moveLocationRequestDto);

    CommonResponseDto<?> deleteFindByKanbanColumnIdAndCard(Long kanbanColumnId,
        DeleteCardRequestDto requestDto, UserDetailsImpl userDetails);

    Card findById(long cardId);
}
