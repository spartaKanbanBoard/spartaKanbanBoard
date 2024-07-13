package com.sparta.spartakanbanboard.domain.cardcomment.service;

import com.sparta.spartakanbanboard.domain.card.dto.CardResponseDto;
import com.sparta.spartakanbanboard.domain.card.entity.Card;
import com.sparta.spartakanbanboard.domain.card.service.CardService;
import com.sparta.spartakanbanboard.domain.cardcomment.dto.CardAndCommentListResponseDto;
import com.sparta.spartakanbanboard.domain.cardcomment.dto.CardAndCommentResponseDto;
import com.sparta.spartakanbanboard.domain.cardcomment.dto.CardCommentResponseDto;
import com.sparta.spartakanbanboard.domain.cardcomment.dto.CreateCommentRequestDto;
import com.sparta.spartakanbanboard.domain.cardcomment.dto.DeleteCommentRequestDto;
import com.sparta.spartakanbanboard.domain.cardcomment.dto.EditCommentRequestDto;
import com.sparta.spartakanbanboard.domain.cardcomment.entity.CardComment;
import com.sparta.spartakanbanboard.domain.cardcomment.repositroy.CardCommentRepository;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import com.sparta.spartakanbanboard.domain.user.service.UserService;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import com.sparta.spartakanbanboard.global.security.UserDetailsImpl;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardCommentServiceImpl implements CardCommentService {

	private final UserService userService;
	private final CardService cardService;
	private final CardCommentRepository cardCommentRepository;

	@Override
	@Transactional
	public CommonResponseDto<?> createCommentByCardId(Long cardId,
		CreateCommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {

		Card card = cardService.findById(cardId);
		User user = userService.findByUserId(userDetails.getUser().getId());

		CardComment cardComment = CardComment.of(commentRequestDto, user, card);
		cardCommentRepository.save(cardComment);

		CardAndCommentResponseDto cardAndCommentResponseDto = CardAndCommentResponseDto.builder()
			.card(CardResponseDto.of(card))
			.cardComment(CardCommentResponseDto.of(cardComment))
			.build();

		return CommonResponseDto.builder()
			.msg("Comment creation is complete.")
			.data(cardAndCommentResponseDto)
			.build();
	}

	@Override
	@Transactional(readOnly = true)
	public CommonResponseDto<?> getAllComments(Long cardId, UserDetailsImpl userDetails) {
		userService.findByUserName(userDetails.getUsername());

		Card card = cardService.findById(cardId);
		CardResponseDto cardResponseDto = CardResponseDto.of(card);

		List<CardCommentResponseDto> cardCommentResponseDtoList = cardCommentRepository
			.findAllByCardIdOrderByCreatedAtDesc(cardId)
			.stream()
			.map(CardCommentResponseDto::new)
			.toList();

		return CommonResponseDto.builder()
			.msg("Retrieving all comments for that card.")
			.data(CardAndCommentListResponseDto.builder()
				.card(cardResponseDto)
				.commentList(cardCommentResponseDtoList)
				.build())
			.build();
	}

	@Override
	@Transactional
	public CommonResponseDto<?> editComment(Long cardId,
		EditCommentRequestDto editCommentRequestDto, UserDetailsImpl userDetails
	) {
		Card card = cardService.findById(cardId);
		CardComment cardComment = findById(editCommentRequestDto.getCommentId());

		User editingUser = userService.findByUserName(userDetails.getUsername());

		if (!Objects.equals(cardComment.getUser().getId(), editingUser.getId())) {
			throw new IllegalArgumentException("You are not authorized to edit this comment");
		}

		cardComment.editComment(editCommentRequestDto);
		cardCommentRepository.save(cardComment);

		CardResponseDto cardResponseDto = CardResponseDto.of(card);
		CardCommentResponseDto cardCommentResponseDto = CardCommentResponseDto.of(cardComment);

		return CommonResponseDto.builder()
			.msg("The comment has been edited successfully")
			.data(CardAndCommentResponseDto.builder()
				.card(cardResponseDto)
				.cardComment(cardCommentResponseDto)
				.build())
			.build();
	}

	@Override
	@Transactional
	public CommonResponseDto<?> deleteComment(Long cardId,
		DeleteCommentRequestDto deleteCommentRequestDto, UserDetailsImpl userDetails
	) {
		cardService.findById(cardId);
		CardComment commentToDelete = findById(deleteCommentRequestDto.getCommentId());

		User deleteUser = userService.findByUserName(userDetails.getUsername());

		if (!Objects.equals(commentToDelete.getUser().getId(), deleteUser.getId())) {
			throw new IllegalArgumentException("You are not authorized to delete this comment");
		}
		cardCommentRepository.delete(commentToDelete);

		return CommonResponseDto.builder()
			.msg("The comment has been deleted successfully")
			.data("Deleted Comment ID: " + commentToDelete.getId())
			.build();
	}

	@Override
	public CardComment findById(Long commentId) {
		return cardCommentRepository.findById(commentId).orElseThrow(() ->
			new NoSuchElementException("Comment Not Found"));
	}

}
