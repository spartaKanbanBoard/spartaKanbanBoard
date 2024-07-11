package com.sparta.spartakanbanboard.domain.board.repository;

import com.sparta.spartakanbanboard.domain.board.entity.Board;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import com.sparta.spartakanbanboard.global.dto.PageDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface BoardRepositoryQuery {
    Slice<Board> searchAllBoard(Pageable pageable);
    Slice<Board> searchMyBoard(User user, Pageable pageable);

}
