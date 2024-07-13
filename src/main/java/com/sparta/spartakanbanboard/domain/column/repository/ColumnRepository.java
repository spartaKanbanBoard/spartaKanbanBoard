package com.sparta.spartakanbanboard.domain.column.repository;

import com.sparta.spartakanbanboard.domain.column.entity.KanbanColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ColumnRepository extends JpaRepository<KanbanColumn, Long>, ColumnRepositoryQuery {

    @Query("select distinct kc from KanbanColumn kc left join fetch kc.cardList c where kc.board.id = :boardId")
    List<KanbanColumn> findKanbanColumnsByBoard(@Param("boardId") Long boardId);

    @Query("SELECT MAX(k.orderNumber) FROM KanbanColumn k")
    Long findMaxOrderNumber();
}
