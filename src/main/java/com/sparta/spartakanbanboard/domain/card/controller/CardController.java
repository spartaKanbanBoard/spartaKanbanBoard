package com.sparta.spartakanbanboard.domain.card.controller;

import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.service.CardServiceImpl;
import com.sparta.spartakanbanboard.domain.user.service.global.dto.CommonResponseDto;
import com.sparta.spartakanbanboard.domain.user.service.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<CommonResponseDto<Object>> createCard(
        @PathVariable @Valid Long kanbanColumnId,
        @RequestBody CreateCardRequestDto requestDto) {

        CommonResponseDto<Object> commonResponseDto = cardService.createCard(kanbanColumnId, requestDto);
        return ResponseEntity.ok().body(commonResponseDto);
    }

//    //전체조회
//    @GetMapping
//    public ResponseEntity<CommonResponseDto> getAllCard(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//
//        CommonResponseDto<List<CardResponseDto>> cardResponseDtoList = cardService.getAllCard(userDetails);
//
//        return ResponseEntity.ok().body(cardResponseDtoList);
//    }



}
