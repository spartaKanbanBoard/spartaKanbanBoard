package com.sparta.spartakanbanboard.domain.cardcomment.dto;

import com.sparta.spartakanbanboard.domain.card.dto.CardResponseDto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CardAndCommentListResponseDto {

    private final String userName;
    private final CardResponseDto card;
    private final List<CardCommentResponseDto> commentList;


    public static CardAndCommentListResponseDto of(String userName, CardResponseDto card,
                                                   List<CardCommentResponseDto> commentList) {

        return CardAndCommentListResponseDto.builder()
                .userName(userName)
                .card(card)
                .commentList(commentList)
                .build();
    }

}
