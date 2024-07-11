package com.sparta.spartakanbanboard.domain.card.service;

import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.repository.CardRepository;
import com.sparta.spartakanbanboard.domain.user.service.global.dto.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface CardService {


    CommonResponseDto<Object> createCard(Long kanbanColumnId, CreateCardRequestDto requestDto);
}
