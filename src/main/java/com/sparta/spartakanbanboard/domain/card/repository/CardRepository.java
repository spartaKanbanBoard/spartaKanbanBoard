package com.sparta.spartakanbanboard.domain.card.repository;

import com.sparta.spartakanbanboard.domain.card.entity.Card;
import com.sparta.spartakanbanboard.domain.card.entity.State;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findAllByKanbanColumnIdAndUsernameAndStateOrderBySequenceAsc(Long kanbanColumnId, String username, State state);

    List<Card> findAllByKanbanColumnIdAndUsernameOrderBySequenceAsc(Long kanbanColumnId, String username);

    List<Card> findAllByKanbanColumnIdAndStateOrderBySequenceAsc(Long kanbanColumnId, State state);

    List<Card> findAllByKanbanColumnIdOrderBySequenceAsc(Long kanbanColumnId);

    Card findTopByKanbanColumnIdOrderBySequenceDesc(Long kanbanColumnId);

    Optional<Card> findByIdAndKanbanColumnId(Long cardId, Long kanbanColumnId);

    List<Card> findAllByKanbanColumnId(Long kanbanColumnId);

    List<Card> findAllByKanbanColumnIdAndSequenceGreaterThan(Long kanbanColumnId, int deletedCardSequence);
}
