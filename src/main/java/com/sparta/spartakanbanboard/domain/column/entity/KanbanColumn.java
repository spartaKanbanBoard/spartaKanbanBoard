package com.sparta.spartakanbanboard.domain.column.entity;

import com.sparta.spartakanbanboard.domain.board.entity.Board;
import com.sparta.spartakanbanboard.domain.card.entity.Card;
import com.sparta.spartakanbanboard.domain.column.dto.ColumnResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "db_kanban_column")
public class KanbanColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String columnTitle;

    @Column
    @Setter
    private Long orderNumber;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "kanbanColumn", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Card> cardList = new LinkedHashSet<>();

    public static ColumnResponseDto of(KanbanColumn kanbanColumn) {
        return ColumnResponseDto.builder()
            .columnTitle(kanbanColumn.getColumnTitle())
            .orderNumber(kanbanColumn.getOrderNumber())
            .id(kanbanColumn.id)
            .build();
    }

    public void setCard(Card card) {
        cardList.add(card);
    }

    public void updateTitle(String title){
        this.columnTitle = title;
    }

    @Override
    public boolean equals(Object object) {
        KanbanColumn column = (KanbanColumn) object;

        return Objects.equals(column.id, this.id);
    }
}
