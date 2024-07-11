package com.sparta.spartakanbanboard.domain.card.dto;

import lombok.Getter;

@Getter
public class CardCreateRequestDto {

    private String title;
    private String content;
    private String writer;
}
