package com.sparta.spartakanbanboard.domain.card.dto;

import com.sparta.spartakanbanboard.domain.card.entity.Card;
import com.sparta.spartakanbanboard.domain.card.entity.State;
import lombok.Getter;

@Getter
public class CardResponseDto {

    private String title;
    private String content;
    private State state;

    public CardResponseDto(Card card) {
        this.title = card.getTitle();
        this.content = card.getContent();
        this.state = card.getState();
    }
}
