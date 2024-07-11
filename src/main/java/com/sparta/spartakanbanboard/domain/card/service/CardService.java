package com.sparta.spartakanbanboard.domain.card.service;

import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;

public interface CardService {

    void createCard(Long progressId, CreateCardRequestDto requestDto, UserDetails userDetails);
}
