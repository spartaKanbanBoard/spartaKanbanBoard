package com.sparta.spartakanbanboard.domain.column.repository;

import com.sparta.spartakanbanboard.domain.column.entity.KanbanColumn;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ColumnRepositoryQuery {

    KanbanColumn findByTitle(String columnTitle);
    Slice<KanbanColumn> findAllColumn(Pageable pageable);

}
