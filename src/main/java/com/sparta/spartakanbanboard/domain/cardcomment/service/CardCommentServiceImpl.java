package com.sparta.spartakanbanboard.domain.cardcomment.service;

import com.sparta.spartakanbanboard.domain.card.dto.CardResponseDto;
import com.sparta.spartakanbanboard.domain.card.entity.Card;
import com.sparta.spartakanbanboard.domain.card.service.CardService;
import com.sparta.spartakanbanboard.domain.cardcomment.dto.CardAndCommentListResponseDto;
import com.sparta.spartakanbanboard.domain.cardcomment.dto.CardAndCommentResponseDto;
import com.sparta.spartakanbanboard.domain.cardcomment.dto.CardCommentResponseDto;
import com.sparta.spartakanbanboard.domain.cardcomment.dto.CreateCommentRequestDto;
import com.sparta.spartakanbanboard.domain.cardcomment.dto.EditCommentRequestDto;
import com.sparta.spartakanbanboard.domain.cardcomment.entity.CardComment;
import com.sparta.spartakanbanboard.domain.cardcomment.repositroy.CardCommentRepository;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import com.sparta.spartakanbanboard.domain.user.service.UserService;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import com.sparta.spartakanbanboard.global.security.UserDetailsImpl;
import java.util.List;
import java.util.NoSuchElementException;
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
			.msg("댓글 생성이 완료되었습니다.")
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
			.msg("해당 카드의 댓글을 모두 조회합니다.")
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

		if (!cardComment.getUser().getId().equals(editingUser.getId())) {
			throw new IllegalArgumentException("본인 댓글만 수정할 수 있습니다");
		}

		cardComment.editComment(editCommentRequestDto);
		cardCommentRepository.save(cardComment);

		CardResponseDto cardResponseDto = CardResponseDto.of(card);
		CardCommentResponseDto cardCommentResponseDto = CardCommentResponseDto.of(cardComment);

		return CommonResponseDto.builder()
			.msg("해당 댓글을 수정하였습니다")
			.data(CardAndCommentResponseDto.builder()
				.card(cardResponseDto)
				.cardComment(cardCommentResponseDto)
				.build())
			.build();
	}

	@Override
	public CardComment findById(Long commentId) {
		return cardCommentRepository.findById(commentId).orElseThrow(() ->
			new NoSuchElementException("No Such User"));
	}


}
