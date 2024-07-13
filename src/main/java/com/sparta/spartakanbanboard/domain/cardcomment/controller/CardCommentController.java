package com.sparta.spartakanbanboard.domain.cardcomment.controller;

import com.sparta.spartakanbanboard.domain.cardcomment.dto.CreateCommentRequestDto;
import com.sparta.spartakanbanboard.domain.cardcomment.dto.DeleteCommentRequestDto;
import com.sparta.spartakanbanboard.domain.cardcomment.dto.EditCommentRequestDto;
import com.sparta.spartakanbanboard.domain.cardcomment.service.CardCommentServiceImpl;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import com.sparta.spartakanbanboard.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cards/{cardId}/comments")
public class CardCommentController {

	private final CardCommentServiceImpl cardCommentService;

	@PostMapping
	public ResponseEntity<?> createCommentByCardId(
		@PathVariable Long cardId,
		@RequestBody @Valid CreateCommentRequestDto commentRequestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		CommonResponseDto<?> commonResponseDto = cardCommentService.createCommentByCardId(cardId,
			commentRequestDto, userDetails);

		return ResponseEntity.ok().body(commonResponseDto);
	}

	@GetMapping
	public ResponseEntity<?> getAllComments(
		@PathVariable Long cardId,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		CommonResponseDto<?> commonResponseDto = cardCommentService.getAllComments(cardId,
			userDetails);

		return ResponseEntity.ok().body(commonResponseDto);
	}

	@PutMapping
	public ResponseEntity<?> editComment(
		@PathVariable Long cardId,
		@RequestBody @Valid EditCommentRequestDto editCommentRequestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		CommonResponseDto<?> commonResponseDto = cardCommentService.editComment(cardId,
			editCommentRequestDto, userDetails);

		return ResponseEntity.ok().body(commonResponseDto);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteComment(
		@PathVariable Long cardId,
		@RequestBody @Valid DeleteCommentRequestDto deleteCommentRequestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		CommonResponseDto<?> commonResponseDto = cardCommentService.deleteComment(cardId,
			deleteCommentRequestDto, userDetails);

		return ResponseEntity.ok().body(commonResponseDto);
	}
}
