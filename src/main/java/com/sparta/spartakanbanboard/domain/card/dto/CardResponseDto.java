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

    private Long writerId;
    private String title;
    private String content;
    private State state;
    private LocalDateTime endTime;


    public CardResponseDto(Card card) {
        this.writerId = card.getWriterId();
        this.title = card.getTitle();
        this.content = card.getContent();
        this.state = card.getState();
        this.endTime = card.getEndTime();
    }

    public static CardResponseDto of(Card card) {

        return CardResponseDto.builder()
            .writerId(card.getWriterId())
            .title(card.getTitle())
            .content(card.getContent())
            .state(card.getState())
            .endTime(card.getEndTime())
            .build();
    }

}
