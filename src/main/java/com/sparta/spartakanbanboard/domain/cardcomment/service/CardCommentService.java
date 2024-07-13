package com.sparta.spartakanbanboard.domain.cardcomment.service;

import com.sparta.spartakanbanboard.domain.cardcomment.dto.CreateCommentRequestDto;
import com.sparta.spartakanbanboard.domain.cardcomment.dto.DeleteCommentRequestDto;
import com.sparta.spartakanbanboard.domain.cardcomment.dto.EditCommentRequestDto;
import com.sparta.spartakanbanboard.domain.cardcomment.entity.CardComment;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import com.sparta.spartakanbanboard.global.security.UserDetailsImpl;
import org.springframework.transaction.annotation.Transactional;

public interface CardCommentService {

	@Transactional
	CommonResponseDto<?> createCommentByCardId(Long cardId,
		CreateCommentRequestDto commentRequestDto, UserDetailsImpl userDetails);

	@Transactional(readOnly = true)
	CommonResponseDto<?> getAllComments(Long cardId, UserDetailsImpl userDetails);

	@Transactional
	CommonResponseDto<?> editComment(Long cardId,
		EditCommentRequestDto editCommentRequestDto, UserDetailsImpl userDetails
	);

	@Transactional
	CommonResponseDto<?> deleteComment(Long cardId, DeleteCommentRequestDto deleteCommentRequestDto,
		UserDetailsImpl userDetails);

	CardComment findById(Long commentId);
}
