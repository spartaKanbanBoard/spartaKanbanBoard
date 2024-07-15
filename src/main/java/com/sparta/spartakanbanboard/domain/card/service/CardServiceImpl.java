package com.sparta.spartakanbanboard.domain.card.service;import com.sparta.spartakanbanboard.domain.card.dto.CardResponseDto;import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;import com.sparta.spartakanbanboard.domain.card.dto.EditCardRequestDto;import com.sparta.spartakanbanboard.domain.card.dto.KanbanColumnAndCardAndUserResponseDto;import com.sparta.spartakanbanboard.domain.card.dto.KanbanColumnAndCardListAndUserResponseDto;import com.sparta.spartakanbanboard.domain.card.dto.MoveLocationRequestDto;import com.sparta.spartakanbanboard.domain.card.entity.Card;import com.sparta.spartakanbanboard.domain.card.entity.State;import com.sparta.spartakanbanboard.domain.card.repository.CardRepository;import com.sparta.spartakanbanboard.domain.column.dto.ColumnResponseDto;import com.sparta.spartakanbanboard.domain.column.entity.KanbanColumn;import com.sparta.spartakanbanboard.domain.column.service.ColumnService;import com.sparta.spartakanbanboard.domain.user.entity.User;import com.sparta.spartakanbanboard.domain.user.service.UserService;import com.sparta.spartakanbanboard.global.BusinessLogicException;import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;import com.sparta.spartakanbanboard.global.security.UserDetailsImpl;import java.util.List;import java.util.NoSuchElementException;import java.util.Objects;import java.util.Optional;import lombok.RequiredArgsConstructor;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;@Service@RequiredArgsConstructorpublic class CardServiceImpl implements CardService {	private final CardRepository cardRepository;	private final UserService userService;	private final ColumnService columnService;	@Override	@Transactional	public CommonResponseDto<?> createCardAtKanbanColumn(Long kanbanColumnId,		CreateCardRequestDto requestDto, UserDetailsImpl userDetails	) {		KanbanColumn kanbanColumn = columnService.findById(kanbanColumnId);		ColumnResponseDto columnResponseDto = KanbanColumn.of(kanbanColumn);		User user;		if (Objects.equals(requestDto.getUsername(), userDetails.getUsername())) {			user = userService.findByUserName(userDetails.getUsername());		} else {			throw new IllegalArgumentException("사용자 아이디가 일치하지 않습니다.");		}		Card card = Card.of(requestDto, user, kanbanColumn);		int newSequence = 1;		Card maxSequenceCard = cardRepository.findTopByKanbanColumnIdOrderBySequenceDesc(			kanbanColumnId);		if (maxSequenceCard != null) {			int currentSequence = maxSequenceCard.getSequence();			newSequence = currentSequence + 1;		}		card.setSequence(newSequence);		cardRepository.save(card);		CardResponseDto cardResponseDto = CardResponseDto.of(card);		KanbanColumnAndCardAndUserResponseDto kanbanColumnAndCardAndUserResponseDto = KanbanColumnAndCardAndUserResponseDto.builder()			.column(columnResponseDto)			.card(cardResponseDto)			.build();		return CommonResponseDto.builder()			.msg("카드 생성이 완료되었습니다.")			.data(kanbanColumnAndCardAndUserResponseDto)			.build();	}	@Override	@Transactional(readOnly = true)	public CommonResponseDto<?> findKanbanColumnIdGetCards(Long kanbanColumnId, String username,		State state	) {		KanbanColumn kanbanColumn = columnService.findById(kanbanColumnId);		ColumnResponseDto columnResponseDto = KanbanColumn.of(kanbanColumn);		List<Card> cardList;		if (username != null && state != null) {			cardList = cardRepository.findAllByKanbanColumnIdAndUsernameAndStateOrderBySequenceAsc(kanbanColumnId,				username, state);		} else if (username != null) {			cardList = cardRepository.findAllByKanbanColumnIdAndUsernameOrderBySequenceAsc(kanbanColumnId, username);		} else if (state != null) {			cardList = cardRepository.findAllByKanbanColumnIdAndStateOrderBySequenceAsc(kanbanColumnId, state);		} else {			cardList = cardRepository.findAllByKanbanColumnIdOrderBySequenceAsc(kanbanColumnId);		}		List<CardResponseDto> cardResponseDtoList = cardList.stream()			.map(CardResponseDto::new)			.toList();		KanbanColumnAndCardListAndUserResponseDto responseDto = KanbanColumnAndCardListAndUserResponseDto.builder()			.column(columnResponseDto)			.cardList(cardResponseDtoList)			.build();		return CommonResponseDto.builder()			.msg("해당 컬럼의 카드를 조회합니다.")			.data(responseDto)			.build();	}	@Override	@Transactional	public CommonResponseDto<?> editFindKanbanColumnIdAndCard(Long kanbanColumnId,		Long cardId, EditCardRequestDto editCardRequestDto, UserDetailsImpl userDetails	) {		User user;		if (Objects.equals(editCardRequestDto.getUsername(), userDetails.getUsername())) {			user = userService.findByUserName(userDetails.getUsername());		} else {			throw new IllegalArgumentException("사용자 아이디가 일치하지 않습니다.");		}		Optional<Card> optionalCard = cardRepository.findByIdAndKanbanColumnId(			cardId, kanbanColumnId);		Card card = optionalCard.orElseThrow(() ->			new BusinessLogicException("Nof Found Card"));		card.editCard(editCardRequestDto, user);		cardRepository.save(card);		CardResponseDto cardResponseDto = CardResponseDto.of(card);		return CommonResponseDto.builder()			.msg("해당 컬럼의 카드를 수정합니다.")			.data(cardResponseDto)			.build();	}	@Override	@Transactional	public CommonResponseDto<?> moveLocationCards(Long kanbanColumnId, Long cardId,		MoveLocationRequestDto moveLocationRequestDto	) {		KanbanColumn kanbanColumn = columnService.findById(kanbanColumnId);		ColumnResponseDto columnResponseDto = KanbanColumn.of(kanbanColumn);		Card card = findById(cardId);		List<Card> cardList = cardRepository.findAllByKanbanColumnId(kanbanColumnId);		int targetSequence = moveLocationRequestDto.getSequence();		int currentSequence = card.getSequence();		int difference = targetSequence - currentSequence;		if (difference != 0) {			for (Card c : cardList) {				int cSequence = c.getSequence();				if (!c.getId().equals(cardId) &&					(difference > 0 && cSequence > currentSequence && cSequence <= targetSequence)					||					(difference < 0 && cSequence < currentSequence && cSequence >= targetSequence)				) {					c.setSequence(cSequence + (difference > 0 ? -1 : 1));				}			}			card.setSequence(targetSequence);			cardRepository.saveAll(cardList);		}		CardResponseDto cardResponseDto = CardResponseDto.of(card);		KanbanColumnAndCardAndUserResponseDto kanbanColumnAndCardAndUserResponseDto = KanbanColumnAndCardAndUserResponseDto.builder()			.column(columnResponseDto)			.card(cardResponseDto)			.build();		return CommonResponseDto.builder()			.msg("카드 순서 변경이 완료되었습니다.")			.data(kanbanColumnAndCardAndUserResponseDto)			.build();	}	@Override	@Transactional	public CommonResponseDto<?> deleteFindByKanbanColumnIdAndCard(Long kanbanColumnId,		Long cardId, UserDetailsImpl userDetails	) {		Card cardToDelete = findById(cardId);		if(!Objects.equals(cardToDelete.getUsername(), userDetails.getUsername())) {			throw new IllegalArgumentException("작성자가 일치하지 않습니다.");		}		int deletedCardSequence = cardToDelete.getSequence();		List<Card> cardsToUpdateSequence = cardRepository.findAllByKanbanColumnIdAndSequenceGreaterThan(			kanbanColumnId, deletedCardSequence);		for (Card card : cardsToUpdateSequence) {			int currentSequence = card.getSequence();			card.setSequence(currentSequence - 1);		}		cardRepository.saveAll(cardsToUpdateSequence);		cardRepository.delete(cardToDelete);		return CommonResponseDto.builder()			.msg("카드 삭제가 완료되었습니다.")			.data("Deleted Card ID: " + cardToDelete.getId())			.build();	}	@Override	public Card findById(long cardId) {		return cardRepository.findById(cardId).orElseThrow(() ->			new NoSuchElementException("일치하는 카드가 없습니다.")		);	}}