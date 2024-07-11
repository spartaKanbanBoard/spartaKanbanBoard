package com.sparta.spartakanbanboard.domain.card.service;

import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.entity.Card;
import com.sparta.spartakanbanboard.domain.card.repository.CardRepository;
import com.sparta.spartakanbanboard.domain.kanbanColumn.entity.KanbanColumn;
import com.sparta.spartakanbanboard.domain.kanbanColumn.repository.KanbanColumnRepository;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import com.sparta.spartakanbanboard.domain.user.repository.UserRepository;
import com.sparta.spartakanbanboard.global.exception.NofFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j(topic = "card 생성")
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final KanbanColumnRepository kanbanColumnRepository;

    @Override
    public void createCard(Long progressId, CreateCardRequestDto requestDto,
        UserDetails userDetails) {
//        KanbanColumn kanbanColumn = kanbanColumnRepository.findById(progressId).orElseThrow(() ->
//            new NofFoundException("Not Found progress"));
//
//        User user = userRepository.findById(userDetails.getUserId()).orElseThrow(() ->
//            new NofFoundException("Not Found User"));
//
//        Card card = Card.of(requestDto);
//
//
//        kanbanColumn.addCard(user);
//        cardRepository.save(card);
    }

}
