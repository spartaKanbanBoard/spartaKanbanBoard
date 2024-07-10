package com.sparta.spartakanbanboard.domain.board.repository;

import com.sparta.spartakanbanboard.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
