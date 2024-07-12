package com.sparta.spartakanbanboard.domain.card.controller;

import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.service.CardServiceImpl;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/columns/{kanbanColumnId}/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardServiceImpl cardService;

    @PostMapping
    public ResponseEntity<CommonResponseDto<?>> createCardAtKanbanColumn(@PathVariable @Valid Long kanbanColumnId,
        @RequestBody CreateCardRequestDto requestDto) {

        CommonResponseDto<?> commonResponseDto = cardService.createCardAtKanbanColumn(kanbanColumnId, requestDto);
        return ResponseEntity.ok().body(commonResponseDto);
    }

    //전체조회
    @GetMapping
    public ResponseEntity<CommonResponseDto<?>> findKanbanColumnIdAllCards(@PathVariable @Valid Long kanbanColumnId) {
        CommonResponseDto<?> cardResponseDtoList = cardService.findKanbanColumnIdAllCards(kanbanColumnId);
        return ResponseEntity.ok().body(cardResponseDtoList);
    }
}
