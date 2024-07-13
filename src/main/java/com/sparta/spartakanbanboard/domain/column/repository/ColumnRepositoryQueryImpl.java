package com.sparta.spartakanbanboard.domain.column.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.spartakanbanboard.domain.column.entity.KanbanColumn;
import com.sparta.spartakanbanboard.domain.column.entity.QKanbanColumn;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class ColumnRepositoryQueryImpl implements ColumnRepositoryQuery{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public KanbanColumn findByTitle(String columnTitle) {
        QKanbanColumn column = QKanbanColumn.kanbanColumn;

        return jpaQueryFactory.select(column)
            .from(column)
            .where(column.columnTitle.eq(columnTitle))
            .fetchOne();
    }

    @Override
    public List<KanbanColumn> findAllByBoardId(long boardId ,Pageable pageable) {
        QKanbanColumn column = QKanbanColumn.kanbanColumn;

        return jpaQueryFactory.select(column)
            .from(column)
            .where(column.board.id.eq(boardId))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
    }
}
