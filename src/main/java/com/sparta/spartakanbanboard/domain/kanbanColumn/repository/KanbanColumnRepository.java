package com.sparta.spartakanbanboard.domain.kanbanColumn.repository;

import com.sparta.spartakanbanboard.domain.kanbanColumn.entity.KanbanColumn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KanbanColumnRepository extends JpaRepository<KanbanColumn, Long> {
}
