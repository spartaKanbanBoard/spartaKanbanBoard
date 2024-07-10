package com.sparta.spartakanbanboard.domain.column.repository;

import com.sparta.spartakanbanboard.domain.column.entity.Column;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnRepository extends JpaRepository<Column, Long> {
}
