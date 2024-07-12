package com.sparta.spartakanbanboard.domain.card.entity;

import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;
import com.sparta.spartakanbanboard.domain.column.entity.KanbanColumn;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import com.sparta.spartakanbanboard.global.entity.TimeStamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "db_card")
public class Card extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String content;

    @Enumerated(EnumType.STRING)
    private State state;

    public static Card of(CreateCardRequestDto cardRequestDto, User user, KanbanColumn kanbanColumn) {
        Card card = Card.builder()
            .title(cardRequestDto.getTitle())
            .content(cardRequestDto.getContent())
            .state(State.BEFORE)
            .user(user)
            .kanbanColumn(kanbanColumn)
            .build();

        kanbanColumn.addCard(card);
        return card;
    }


    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "kanban_Column_id")
    KanbanColumn kanbanColumn;
}
