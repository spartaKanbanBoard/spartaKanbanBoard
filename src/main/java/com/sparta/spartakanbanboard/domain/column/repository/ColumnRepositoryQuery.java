package com.sparta.spartakanbanboard.domain.column.repository;

import com.sparta.spartakanbanboard.domain.column.entity.KanbanColumn;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ColumnRepositoryQuery {

    KanbanColumn findByTitle(String columnTitle);
    List<KanbanColumn> findAllByBoardId(long boardId ,Pageable pageable);
}
