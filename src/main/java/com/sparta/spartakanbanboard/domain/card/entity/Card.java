package com.sparta.spartakanbanboard.domain.card.entity;

import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.dto.EditCardRequestDto;
import com.sparta.spartakanbanboard.domain.column.entity.KanbanColumn;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import com.sparta.spartakanbanboard.global.entity.TimeStamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Column
    private Long writerId;

    @Enumerated(EnumType.STRING)
    private State state;

    //마감일자
    private LocalDateTime endTime;

    //순서이동
    private Long sequence;


    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "kanban_Column_id")
    KanbanColumn kanbanColumn;

    public static Card of(CreateCardRequestDto cardRequestDto, User user, KanbanColumn kanbanColumn) {
        Card card = Card.builder()
            .title(cardRequestDto.getTitle())
            .content(cardRequestDto.getContent())
            .writerId(cardRequestDto.getWriterId())
            .state(State.BEFORE)
            .endTime(cardRequestDto.getEndTime())
            .user(user)
            .kanbanColumn(kanbanColumn)
            .build();

        kanbanColumn.addCard(card);
        return card;
    }

    public void editCard(EditCardRequestDto editCardRequestDto, User user) {
        this.title = editCardRequestDto.getTitle();
        this.content = editCardRequestDto.getContent();
        this.writerId = editCardRequestDto.getWriterId();
        this.state = editCardRequestDto.getState();
        this.endTime = editCardRequestDto.getEndTime();
        this.user = user;
    }
}
