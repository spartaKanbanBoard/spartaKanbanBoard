package com.sparta.spartakanbanboard.domain.card.controller;

import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.dto.DeleteCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.dto.EditCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.dto.MoveLocationRequestDto;
import com.sparta.spartakanbanboard.domain.card.entity.State;
import com.sparta.spartakanbanboard.domain.card.service.CardServiceImpl;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<?> createCardAtKanbanColumn(
        @PathVariable Long kanbanColumnId,
        @RequestBody @Valid CreateCardRequestDto requestDto
    ) {
        CommonResponseDto<?> commonResponseDto = cardService.createCardAtKanbanColumn(
            kanbanColumnId, requestDto);
        return ResponseEntity.ok().body(commonResponseDto);
    }

    @GetMapping
    public ResponseEntity<?> findKanbanColumnIdAllCards(
        @PathVariable Long kanbanColumnId,
        @RequestParam(required = false) String username,
        @RequestParam(required = false) State state
    ) {
        CommonResponseDto<?> cardResponseDtoList = cardService.findKanbanColumnIdGetCards(
            kanbanColumnId, username, state);
        return ResponseEntity.ok().body(cardResponseDtoList);
    }

    @PutMapping
    public ResponseEntity<?> editFindKanbanColumnIdAndCard(
        @PathVariable Long kanbanColumnId,
        @RequestBody @Valid EditCardRequestDto requestDto
    ) {
        CommonResponseDto<?> commonResponseDto = cardService.editFindKanbanColumnIdAndCard(
            kanbanColumnId, requestDto);
        return ResponseEntity.ok().body(commonResponseDto);
    }

    @PatchMapping("/move")
    public ResponseEntity<?> moveLocationCards(
        @PathVariable Long kanbanColumnId,
        @RequestParam Long cardId,
        @RequestBody @Valid MoveLocationRequestDto moveLocationRequestDto) {

        CommonResponseDto<?> commonResponseDto = cardService.moveLocationCards(kanbanColumnId,
            cardId, moveLocationRequestDto);

        return ResponseEntity.ok().body(commonResponseDto);

    }
    @DeleteMapping
    public ResponseEntity<?> deleteFindByKanbanColumnIdAndCard(
        @PathVariable Long kanbanColumnId,
        @RequestBody @Valid DeleteCardRequestDto requestDto
    ) {
        CommonResponseDto<?> commonResponseDto = cardService.deleteFindByKanbanColumnIdAndCard(kanbanColumnId, requestDto);

        return ResponseEntity.ok().body(commonResponseDto);
    }

}
