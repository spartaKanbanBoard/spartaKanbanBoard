package com.sparta.spartakanbanboard.domain.board.dto;

import com.sparta.spartakanbanboard.domain.card.entity.Card;
import com.sparta.spartakanbanboard.domain.card.entity.State;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CardDetailsResponseDto {
    private Long id;
    private String username;
    private String title;
    private String content;
    private State state;
    private LocalDateTime endTime;
    private int sequence;

    public static CardDetailsResponseDto of(Card card) {
        return CardDetailsResponseDto.builder()
            .id(card.getId())
            .title(card.getTitle())
            .content(card.getContent())
            .username(card.getUsername())
            .state(card.getState())
            .sequence(card.getSequence())
            .endTime(card.getEndTime())
            .build();
    }
}
