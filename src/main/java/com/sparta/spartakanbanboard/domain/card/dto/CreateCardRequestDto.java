package com.sparta.spartakanbanboard.domain.card.dto;

import com.sparta.spartakanbanboard.domain.card.entity.State;
import lombok.Getter;

@Getter
public class CreateCardRequestDto {

    private String title;

    private String content;

    private State state;

    private String writer;
}
