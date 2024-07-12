package com.sparta.spartakanbanboard.domain.card.service;import com.sparta.spartakanbanboard.domain.card.dto.CardResponseDto;import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;import com.sparta.spartakanbanboard.domain.card.dto.KanbanColumnAndCardListResponseDto;import com.sparta.spartakanbanboard.domain.card.dto.KanbanColumnAndCardResponseDto;import com.sparta.spartakanbanboard.domain.card.entity.Card;import com.sparta.spartakanbanboard.domain.card.entity.State;import com.sparta.spartakanbanboard.domain.card.repository.CardRepository;import com.sparta.spartakanbanboard.domain.column.dto.ColumnResponseDto;import com.sparta.spartakanbanboard.domain.column.entity.KanbanColumn;import com.sparta.spartakanbanboard.domain.column.service.ColumnService;import com.sparta.spartakanbanboard.domain.user.entity.User;import com.sparta.spartakanbanboard.domain.user.service.UserService;import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;import java.util.List;import java.util.NoSuchElementException;import lombok.RequiredArgsConstructor;import org.springframework.stereotype.Service;@Service@RequiredArgsConstructorpublic class CardServiceImpl implements CardService {    private final CardRepository cardRepository;    private final UserService userService;    private final ColumnService columnService;    @Override    public CommonResponseDto<?> createCardAtKanbanColumn(Long kanbanColumnId,        CreateCardRequestDto requestDto) {        KanbanColumn kanbanColumn = columnService.findById(kanbanColumnId);        ColumnResponseDto columnResponseDto = KanbanColumn.of(kanbanColumn);        User user = null;        if (requestDto.getWriterId() != null) {            user = userService.findByUserId(requestDto.getWriterId());        }        Card card = Card.of(requestDto, user, kanbanColumn);        cardRepository.save(card);        CardResponseDto cardResponseDto = CardResponseDto.of(card);        KanbanColumnAndCardResponseDto kanbanColumnAndCardResponseDto = KanbanColumnAndCardResponseDto.builder()            .column(columnResponseDto)            .card(cardResponseDto)            .build();        return CommonResponseDto.builder()            .msg("카드 생성이 완료되었습니다.")            .data(kanbanColumnAndCardResponseDto)            .build();    }    @Override    public CommonResponseDto<?> findKanbanColumnIdGetCards(Long kanbanColumnId, Long writerId,        State state) {        KanbanColumn kanbanColumn = columnService.findById(kanbanColumnId);        ColumnResponseDto columnResponseDto = KanbanColumn.of(kanbanColumn);        List<Card> cardList;        if (writerId != null) {            cardList = cardRepository.findAllByKanbanColumnIdAndWriterId(kanbanColumnId, writerId);        } else if (state != null) {            cardList = cardRepository.findAllByKanbanColumnIdAndState(kanbanColumnId, state);        } else {            cardList = cardRepository.findAllByKanbanColumnId(kanbanColumnId);        }        List<CardResponseDto> cardResponseDtoList = cardList.stream()            .map(CardResponseDto::new)            .toList();        KanbanColumnAndCardListResponseDto kanbanColumnAndCardListResponseDto = KanbanColumnAndCardListResponseDto.builder()            .column(columnResponseDto)            .cardList(cardResponseDtoList)            .build();        return CommonResponseDto.builder()            .msg("해당 컬럼의 카드를 조회합니다.")            .data(kanbanColumnAndCardListResponseDto)            .build();    }    @Override    public Card findById(long cardId) {        return cardRepository.findById(cardId).orElseThrow(() ->            new NoSuchElementException("일치하는 카드가 없습니다.")        );    }}