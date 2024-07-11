package com.sparta.spartakanbanboard.domain.card.controller;

import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.service.CardServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/progress/{progress_id}/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardServiceImpl cardService;

    @PostMapping
    public ResponseEntity<?> createCard(
        @PathVariable @Valid Long progress_id,
        @RequestBody @Valid CreateCardRequestDto requestDto,
        @AuthenticationPrincipal UserDetails userDetails) {

        cardService.createCard(progress_id, requestDto, userDetails);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
