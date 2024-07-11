package com.sparta.spartakanbanboard.domain.card.service;

import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;
import com.sparta.spartakanbanboard.domain.user.service.global.dto.CommonResponseDto;
import com.sparta.spartakanbanboard.domain.user.service.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServiceImpl {



    public CommonResponseDto createCard(Long columnId, CreateCardRequestDto requestDto, UserDetailsImpl userDetails) {
    }
}
