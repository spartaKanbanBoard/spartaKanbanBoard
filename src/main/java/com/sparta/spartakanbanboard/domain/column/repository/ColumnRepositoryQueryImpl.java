package com.sparta.spartakanbanboard.domain.column.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.spartakanbanboard.domain.column.entity.KanbanColumn;
import com.sparta.spartakanbanboard.domain.column.entity.QKanbanColumn;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

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
    public Slice<KanbanColumn> findAllColumn(Pageable pageable) {
        return null;
    }
}
