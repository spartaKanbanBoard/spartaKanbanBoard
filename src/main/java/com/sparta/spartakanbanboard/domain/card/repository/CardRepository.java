package com.sparta.spartakanbanboard.domain.card.repository;

import com.sparta.spartakanbanboard.domain.card.entity.Card;
import com.sparta.spartakanbanboard.domain.card.entity.State;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface CardRepository extends JpaRepository<Card, Long> {


    List<Card> findAllByKanbanColumnIdAndWriterId(Long kanbanColumnId, Long writerId);

    List<Card> findAllByKanbanColumnIdAndState(Long kanbanColumnId, State state);

    List<Card> findAllByKanbanColumnId(Long kanbanColumnId);

    Card findByIdAndKanbanColumnId(Long kanbanColumnId, Long cardId);
}
