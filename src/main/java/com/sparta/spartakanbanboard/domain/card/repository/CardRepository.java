package com.sparta.spartakanbanboard.domain.card.repository;

import com.sparta.spartakanbanboard.domain.card.entity.Card;
import com.sparta.spartakanbanboard.domain.card.entity.State;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findAllByKanbanColumnIdAndUsernameAndState(Long kanbanColumnId, String username, State state);

    List<Card> findAllByKanbanColumnIdAndUsername(Long kanbanColumnId, String username);

    List<Card> findAllByKanbanColumnIdAndState(Long kanbanColumnId, State state);

    List<Card> findAllByKanbanColumnIdOrderBySequenceAsc(Long kanbanColumnId);

    Card findTopByKanbanColumnIdOrderBySequenceDesc(Long kanbanColumnId);


    Optional<Card> findByIdAndKanbanColumnId(Long cardId, Long kanbanColumnId);

    List<Card> findAllByKanbanColumnId(Long kanbanColumnId);
}
