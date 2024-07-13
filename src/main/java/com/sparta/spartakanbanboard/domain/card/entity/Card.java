package com.sparta.spartakanbanboard.domain.card.entity;

import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;
import com.sparta.spartakanbanboard.domain.card.dto.EditCardRequestDto;
import com.sparta.spartakanbanboard.domain.cardcomment.entity.CardComment;
import com.sparta.spartakanbanboard.domain.column.entity.KanbanColumn;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import com.sparta.spartakanbanboard.global.entity.TimeStamped;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
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
    private String username;

    @Enumerated(EnumType.STRING)
    private State state;

    @Column
    private LocalDateTime endTime;

    private int sequence = 0;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "kanban_column_id")
    private KanbanColumn kanbanColumn;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CardComment> cardCommentList = new LinkedHashSet<>();

    public static Card of(CreateCardRequestDto cardRequestDto, User user, KanbanColumn kanbanColumn) {
        Card card = Card.builder()
            .title(cardRequestDto.getTitle())
            .content(cardRequestDto.getContent())
            .username(cardRequestDto.getUsername())
            .state(State.BEFORE)
            .endTime(cardRequestDto.getEndTime())
            .user(user)
            .kanbanColumn(kanbanColumn)
            .build();

        kanbanColumn.setCard(card);

        return card;
    }

    public void editCard(EditCardRequestDto editCardRequestDto, User user) {
        this.title = editCardRequestDto.getTitle();
        this.content = editCardRequestDto.getContent();
        this.username = editCardRequestDto.getUsername();
        this.state = editCardRequestDto.getState();
        this.endTime = editCardRequestDto.getEndTime();
        this.user = user;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public void setComment(CardComment cardComment) {
        cardCommentList.add(cardComment);
    }
}
