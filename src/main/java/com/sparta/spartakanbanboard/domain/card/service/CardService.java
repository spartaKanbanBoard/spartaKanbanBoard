package com.sparta.spartakanbanboard.domain.card.service;

import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.dto.EditCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.entity.Card;
import com.sparta.spartakanbanboard.domain.card.entity.State;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import com.sparta.spartakanbanboard.global.security.UserDetailsImpl;
import org.springframework.transaction.annotation.Transactional;

public interface CardService {


    @Transactional
    CommonResponseDto<?> createCardAtKanbanColumn(long kanbanColumnId,
        CreateCardRequestDto requestDto, UserDetailsImpl userDetails
    );

    @Transactional(readOnly = true)
    CommonResponseDto<?> findKanbanColumnIdGetCards(long kanbanColumnId, String username,
        State state
    );

    @Transactional
    CommonResponseDto<?> editFindKanbanColumnIdAndCard(long kanbanColumnId,
        long cardId, EditCardRequestDto editCardRequestDto, UserDetailsImpl userDetails
    );

    @Transactional
    CommonResponseDto<?> moveLocationByColumnId(long kanbanColumnId, long cardId,
        long targetColumnId, int moveSequence);

    @Transactional
    CommonResponseDto<?> moveCardByColumnId(long kanbanColumnId, long cardId,
        int moveSequence
    );

    @Transactional
    CommonResponseDto<?> deleteFindByKanbanColumnIdAndCard(long kanbanColumnId,
        long cardId, UserDetailsImpl userDetails
    );

    Card findById(long cardId);
}
