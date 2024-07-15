package com.sparta.spartakanbanboard.domain.cardcomment.dto;

import com.sparta.spartakanbanboard.domain.card.dto.CardResponseDto;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CardAndCommentResponseDto {

    private final String userName;
    private final CardResponseDto card;
    private final CardCommentResponseDto cardComment;

    public static CardAndCommentResponseDto of(String userName, CardResponseDto card,
                                               CardCommentResponseDto cardComment) {

        return CardAndCommentResponseDto.builder()
                .userName(userName)
                .card(card)
                .cardComment(cardComment)
                .build();
    }
}
