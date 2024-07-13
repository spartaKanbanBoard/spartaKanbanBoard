package com.sparta.spartakanbanboard.domain.cardcomment.service;

import com.sparta.spartakanbanboard.domain.cardcomment.dto.CreateCommentRequestDto;
import com.sparta.spartakanbanboard.domain.cardcomment.entity.CardComment;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import com.sparta.spartakanbanboard.global.security.UserDetailsImpl;

public interface CardCommentService {

	CommonResponseDto<?> createCommentByCardId(Long cardId,
		CreateCommentRequestDto commentRequestDto, UserDetailsImpl userDetails);

	CommonResponseDto<?> getAllComments(Long cardId);

	CardComment findById(Long commentId);
}
