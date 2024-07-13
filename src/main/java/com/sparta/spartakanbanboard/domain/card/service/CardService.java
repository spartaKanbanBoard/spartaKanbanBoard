package com.sparta.spartakanbanboard.domain.card.service;

import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.dto.DeleteCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.dto.EditCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.dto.MoveLocationRequestDto;
import com.sparta.spartakanbanboard.domain.card.entity.Card;
import com.sparta.spartakanbanboard.domain.card.entity.State;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import com.sparta.spartakanbanboard.global.security.UserDetailsImpl;
import org.springframework.transaction.annotation.Transactional;

public interface CardService {


    @Transactional
    CommonResponseDto<?> createCardAtKanbanColumn(Long kanbanColumnId,
        CreateCardRequestDto requestDto, UserDetailsImpl userDetails
    );

    @Transactional(readOnly = true)
    CommonResponseDto<?> findKanbanColumnIdGetCards(Long kanbanColumnId, String username,
        State state
    );

    @Transactional
    CommonResponseDto<?> editFindKanbanColumnIdAndCard(Long kanbanColumnId,
        EditCardRequestDto editCardRequestDto, UserDetailsImpl userDetails
    );

    @Transactional
    CommonResponseDto<?> moveLocationCards(Long kanbanColumnId, Long cardId,
        MoveLocationRequestDto moveLocationRequestDto
    );

    @Transactional
    CommonResponseDto<?> deleteFindByKanbanColumnIdAndCard(Long kanbanColumnId,
        DeleteCardRequestDto requestDto, UserDetailsImpl userDetails
    );

    Card findById(long cardId);
}
