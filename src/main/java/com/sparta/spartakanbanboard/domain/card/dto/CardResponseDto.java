package com.sparta.spartakanbanboard.domain.card.dto;

import com.sparta.spartakanbanboard.domain.card.entity.Card;
import com.sparta.spartakanbanboard.domain.card.entity.State;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CardResponseDto {

    private String username;
    private String title;
    private String content;
    private State state;
    private LocalDateTime endTime;
    private int sequence;


    public CardResponseDto(Card card) {
        this.username = card.getUsername();
        this.title = card.getTitle();
        this.content = card.getContent();
        this.state = card.getState();
        this.endTime = card.getEndTime();
        this.sequence = card.getSequence();
    }

    public static CardResponseDto of(Card card) {

        return CardResponseDto.builder()
            .username(card.getUsername())
            .title(card.getTitle())
            .content(card.getContent())
            .state(card.getState())
            .endTime(card.getEndTime())
            .sequence(card.getSequence())
            .build();
    }

}
