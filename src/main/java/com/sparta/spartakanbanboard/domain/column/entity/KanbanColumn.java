package com.sparta.spartakanbanboard.domain.column.entity;

import com.sparta.spartakanbanboard.domain.board.entity.Board;
import com.sparta.spartakanbanboard.domain.column.dto.ColumnResponseDto;
import jakarta.persistence.*;
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

//    @OneToMany(mappedBy = "kanban_column")
//    private final List<Card> cardList = new ArrayList<>();

    public static ColumnResponseDto of (KanbanColumn kanbanColumn) {
        return ColumnResponseDto.builder()
            .columnTitle(kanbanColumn.getColumnTitle())
            .build();
    }

}
