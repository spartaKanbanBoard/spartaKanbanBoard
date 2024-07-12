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

    // TODO: 기능구현을 위해 임시로 사용, 차후 db에 저장하든 해서 프로그램 종료에도 유지되는 전역적 number 구상해보기
    private static long createdNumber = 1L;

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
            .build();
    }

    public static void plusNumber(){
        createdNumber++;
    }

    public static Long getCreatedNumber(){
        return createdNumber;
    }

    public void addCard(Card card) {
        cardList.add(card);
    }

    @Override
    public boolean equals(Object object) {
        KanbanColumn column = (KanbanColumn) object;

        return Objects.equals(column.id, this.id);
    }
}