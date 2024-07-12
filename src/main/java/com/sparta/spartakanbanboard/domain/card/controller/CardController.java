package com.sparta.spartakanbanboard.domain.card.controller;

import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.entity.State;
import com.sparta.spartakanbanboard.domain.card.service.CardServiceImpl;
import com.sparta.spartakanbanboard.domain.user.service.global.dto.CommonResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/columns/{kanbanColumnId}/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardServiceImpl cardService;

    @PostMapping
    public ResponseEntity<CommonResponseDto<?>> createCardAtKanbanColumn(
        @PathVariable Long kanbanColumnId,
        @RequestBody @Valid CreateCardRequestDto requestDto
    ) {

        CommonResponseDto<?> commonResponseDto = cardService.createCardAtKanbanColumn(
            kanbanColumnId, requestDto);
        return ResponseEntity.ok().body(commonResponseDto);
    }

    //전체조회
    //required 기본은 true
    @GetMapping
    public ResponseEntity<CommonResponseDto<?>> findKanbanColumnIdAllCards(
        @PathVariable Long kanbanColumnId,
        @RequestParam(required = false) Long writerId,
        @RequestParam(required = false) State state
    ) {
        CommonResponseDto<?> cardResponseDtoList = cardService.findKanbanColumnIdAllCards(kanbanColumnId, writerId, state) ;
        return ResponseEntity.ok().body(cardResponseDtoList);
    }

//    @GetMapping("/{writerId}")
//    public ResponseEntity<CommonResponseDto<?>> findAllByKanbanColumnIdAndWriterId(
//        @PathVariable Long kanbanColumnId,
//        @PathVariable @Valid Long writerId) {
//
//        CommonResponseDto<?> cardResponseDtoList = cardService.findAllByKanbanColumnIdAndWriterId(kanbanColumnId, writerId);
//        return ResponseEntity.ok().body(cardResponseDtoList);
//    }
//
//    @GetMapping("/{cardState}")
//    public ResponseEntity<CommonResponseDto<?>> findAllByKanbanColumnIdAndState(
//        @PathVariable @Valid Long kanbanColumnId,
//        @PathVariable @Valid State cardState) {
//
//        CommonResponseDto<?> cardResponseDtoList = cardService.findAllByKanbanColumnIdAndState(
//            kanbanColumnId, cardState);
//        return ResponseEntity.ok().body(cardResponseDtoList);
//    }
//

}
