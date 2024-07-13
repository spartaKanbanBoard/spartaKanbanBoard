package com.sparta.spartakanbanboard.domain.cardcomment.service;

import com.sparta.spartakanbanboard.domain.card.dto.CardResponseDto;
import com.sparta.spartakanbanboard.domain.card.entity.Card;
import com.sparta.spartakanbanboard.domain.card.service.CardService;
import com.sparta.spartakanbanboard.domain.cardcomment.dto.CardAndCommentListResponseDto;
import com.sparta.spartakanbanboard.domain.cardcomment.dto.CardAndCommentResponseDto;
import com.sparta.spartakanbanboard.domain.cardcomment.dto.CardCommentResponseDto;
import com.sparta.spartakanbanboard.domain.cardcomment.dto.CreateCommentRequestDto;
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

@Service
@RequiredArgsConstructor
public class CardCommentServiceImpl implements CardCommentService {

	private final UserService userService;
	private final CardService cardService;
	private final CardCommentRepository cardCommentRepository;

	@Override
	public CommonResponseDto<?> createCommentByCardId(Long cardId,
		CreateCommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {

		Card card = cardService.findById(cardId);
		User user = userService.findByUserId(userDetails.getUser().getId());

		CardComment cardComment = CardComment.of(commentRequestDto, user, card);
		cardCommentRepository.save(cardComment);

		CardResponseDto cardResponseDto = CardResponseDto.of(card);
		CardCommentResponseDto cardCommentResponseDto = CardCommentResponseDto.of(cardComment);
		CardAndCommentResponseDto cardAndCommentResponseDto = CardAndCommentResponseDto.of(
			cardResponseDto, cardCommentResponseDto);

		return CommonResponseDto.builder()
			.msg("댓글 생성이 완료되었습니다.")
			.data(cardAndCommentResponseDto)
			.build();
	}

	@Override
	public CommonResponseDto<?> getAllComments(Long cardId) {
		Card card = cardService.findById(cardId);
		CardResponseDto cardResponseDto = CardResponseDto.of(card);

		List<CardComment> cardCommentList = cardCommentRepository.findAllByCardIdOrderByCreatedAtDesc(
			cardId);

		List<CardCommentResponseDto> cardCommentResponseDtoList = cardCommentList.stream()
			.map(CardCommentResponseDto::new)
			.toList();

		CardAndCommentListResponseDto cardAndCommentListResponseDto = CardAndCommentListResponseDto.builder()
			.card(cardResponseDto)
			.commentList(cardCommentResponseDtoList)
			.build();

		return CommonResponseDto.builder()
			.msg("해당 카드의 댓글을 모두 조회합니다.")
			.data(cardAndCommentListResponseDto)
			.build();
	}

	@Override
	public CardComment findById(Long commentId) {
		return cardCommentRepository.findById(commentId).orElseThrow(() ->
			new NoSuchElementException("No Such User"));
	}

}
