package com.sparta.spartakanbanboard.domain.cardcomment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EditCommentRequestDto {

	private Long commentId;
	private String content;

}
