package com.sparta.spartakanbanboard.domain.card.service;import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;import com.sparta.spartakanbanboard.domain.card.entity.Card;import com.sparta.spartakanbanboard.domain.card.repository.CardRepository;import com.sparta.spartakanbanboard.domain.column.dto.ColumnResponseDto;import com.sparta.spartakanbanboard.domain.column.repository.ColumnRepository;import com.sparta.spartakanbanboard.domain.user.repository.UserRepository;import com.sparta.spartakanbanboard.domain.user.service.UserService;import com.sparta.spartakanbanboard.domain.user.service.global.dto.CommonResponseDto;import com.sparta.spartakanbanboard.domain.user.service.global.security.UserDetailsImpl;import lombok.RequiredArgsConstructor;import org.springframework.stereotype.Service;@Service@RequiredArgsConstructorpublic class CardServiceImpl {    private final UserRepository userRepository;    private final CardRepository cardRepository;    private final ColumnRepository columnRepository;    private final UserService userService;    public CommonResponseDto createCard(Long columnId, CreateCardRequestDto requestDto, UserDetailsImpl userDetails) {        userService.findByUserId(userDetails.getUser().getId());        columnRepository.findById(columnId);        Card card = Card.of(requestDto);//        column.addCard(card);        cardRepository.save(card);        return CommonResponseDto.builder()            .msg("카드 생성이 완료되었습니다.")            .data(card)            .build();    }}