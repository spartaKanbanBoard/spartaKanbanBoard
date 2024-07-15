package com.sparta.spartakanbanboard.domain.cardcomment.dto;

import com.sparta.spartakanbanboard.domain.cardcomment.entity.CardComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CardCommentResponseDto {

    private final String userName;
    private final String content;

    public CardCommentResponseDto(CardComment cardComment) {
        this.userName = cardComment.getUser().getUserName();
        this.content = cardComment.getContent();
    }

    public static CardCommentResponseDto of(CardComment cardComment) {

        return CardCommentResponseDto.builder()
                .userName(cardComment.getUser().getUserName())
                .content(cardComment.getContent())
                .build();
    }
}
