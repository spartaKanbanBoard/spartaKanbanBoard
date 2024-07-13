package com.sparta.spartakanbanboard.domain.cardcomment.dto;

import com.sparta.spartakanbanboard.domain.card.dto.CardResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CardAndCommentResponseDto {

	private final CardResponseDto card;
	private final CardCommentResponseDto cardComment;

	public static CardAndCommentResponseDto of(CardResponseDto card,
		CardCommentResponseDto cardComment) {

		return CardAndCommentResponseDto.builder()
			.card(card)
			.cardComment(cardComment)
			.build();
	}
}
