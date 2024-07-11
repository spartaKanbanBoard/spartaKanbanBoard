package com.sparta.spartakanbanboard.domain.card.dto;

import lombok.Getter;

@Getter
public class CreateCardRequestDto {

    private String title;
    private String content;
    private String writer;
}
