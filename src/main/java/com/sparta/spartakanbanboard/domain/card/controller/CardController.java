package com.sparta.spartakanbanboard.domain.card.controller;

import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.dto.EditCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.dto.MoveLocationRequestDto;
import com.sparta.spartakanbanboard.domain.card.entity.State;
import com.sparta.spartakanbanboard.domain.card.service.CardServiceImpl;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import com.sparta.spartakanbanboard.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/columns/{kanbanColumnId}/cards")
@RequiredArgsConstructor
public class CardController {

	private final CardServiceImpl cardService;

	@PostMapping
	public ResponseEntity<?> createCardAtKanbanColumn(
		@PathVariable long kanbanColumnId,
		@RequestBody @Valid CreateCardRequestDto requestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		CommonResponseDto<?> commonResponseDto = cardService.createCardAtKanbanColumn(
			kanbanColumnId, requestDto, userDetails);
		return ResponseEntity.ok().body(commonResponseDto);
	}

	@GetMapping
	public ResponseEntity<?> findKanbanColumnIdAllCards(
		@PathVariable long kanbanColumnId,
		@RequestParam(required = false) String username,
		@RequestParam(required = false) State state
	) {
		CommonResponseDto<?> cardResponseDtoList = cardService.findKanbanColumnIdGetCards(
			kanbanColumnId, username, state);
		return ResponseEntity.ok().body(cardResponseDtoList);
	}

	@PutMapping("/{cardId}")
	public ResponseEntity<?> editFindKanbanColumnIdAndCard(
		@PathVariable("kanbanColumnId") long kanbanColumnId,
		@PathVariable("cardId") long cardId,
		@RequestBody @Valid EditCardRequestDto requestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		CommonResponseDto<?> commonResponseDto = cardService.editFindKanbanColumnIdAndCard(
			kanbanColumnId, cardId, requestDto, userDetails);
		return ResponseEntity.ok().body(commonResponseDto);
	}

	@PatchMapping
	public ResponseEntity<?> moveLocationCards(
		@PathVariable long kanbanColumnId,
		@RequestParam long cardId,
		@RequestBody @Valid MoveLocationRequestDto moveLocationRequestDto
	) {
		CommonResponseDto<?> commonResponseDto = cardService.moveLocationCards(kanbanColumnId,
			cardId, moveLocationRequestDto);

		return ResponseEntity.ok().body(commonResponseDto);

	}

	@DeleteMapping("/{cardId}")
	public ResponseEntity<?> deleteFindByKanbanColumnIdAndCard(
		@PathVariable("kanbanColumnId") long kanbanColumnId,
		@PathVariable("cardId") long cardId,
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		CommonResponseDto<?> commonResponseDto = cardService.deleteFindByKanbanColumnIdAndCard(
			kanbanColumnId, cardId, userDetails);

		return ResponseEntity.ok().body(commonResponseDto);
	}

}
