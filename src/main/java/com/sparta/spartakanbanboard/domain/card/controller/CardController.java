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
@RequestMapping
@RequiredArgsConstructor
public class CardController {

    private final CardServiceImpl cardService;


    @PostMapping
    public ResponseEntity<CommonResponseDto> createCard(
        @PathVariable @Valid Long column_id,
        @RequestBody @Valid CreateCardRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        CommonResponseDto commonResponseDto = cardService.createCard(column_id, requestDto, userDetails);

        return ResponseEntity.ok().body(commonResponseDto);
    }



}
