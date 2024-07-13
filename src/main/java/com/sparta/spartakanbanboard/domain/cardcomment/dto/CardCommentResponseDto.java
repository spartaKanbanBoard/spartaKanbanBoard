package com.sparta.spartakanbanboard.domain.cardcomment.dto;

import com.sparta.spartakanbanboard.domain.cardcomment.entity.CardComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CardCommentResponseDto {

	private final String content;

	public static CardCommentResponseDto of(CardComment cardComment) {

		return CardCommentResponseDto.builder()
			.content(cardComment.getContent())
			.build();
	}
}
