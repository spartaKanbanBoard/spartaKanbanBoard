package com.sparta.spartakanbanboard.domain.board.dto;

import com.sparta.spartakanbanboard.domain.card.entity.Card;
import com.sparta.spartakanbanboard.domain.card.entity.State;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CardDetailsResponseDto {
    private String title;
    private String content;
    private String username;
    private State state;
    private int sequence;

    public static CardDetailsResponseDto of(Card card) {
        return CardDetailsResponseDto.builder()
            .title(card.getTitle())
            .content(card.getContent())
            .username(card.getUsername())
            .state(card.getState())
            .sequence(card.getSequence())
            .build();
    }
}
