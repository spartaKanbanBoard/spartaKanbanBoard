package com.sparta.spartakanbanboard.domain.column.entity;

import com.sparta.spartakanbanboard.domain.board.entity.Board;
import com.sparta.spartakanbanboard.domain.card.entity.Card;
import com.sparta.spartakanbanboard.domain.column.dto.ColumnResponseDto;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class KanbanColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String columnTitle;

    @ManyToOne
    private Board board;

    @OneToMany(mappedBy = "kanbanColumn", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Card> cardList = new LinkedHashSet<>();

    public static ColumnResponseDto of (KanbanColumn kanbanColumn) {
        return ColumnResponseDto.builder()
            .columnTitle(kanbanColumn.getColumnTitle())
            .build();
    }

    public void addCard(Card card) {
        cardList.add(card);
    }
}
