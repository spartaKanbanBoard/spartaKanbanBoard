package com.sparta.spartakanbanboard.domain.board.service;

import com.sparta.spartakanbanboard.domain.board.dto.BoardRequestDto;
import com.sparta.spartakanbanboard.domain.board.dto.BoardResponseDto;
import com.sparta.spartakanbanboard.domain.board.entity.Board;
import com.sparta.spartakanbanboard.domain.board.repository.BoardRepository;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import com.sparta.spartakanbanboard.domain.user.repository.UserRepository;
import com.sparta.spartakanbanboard.domain.user.service.UserService;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserService userService;

    public BoardResponseDto createBoard(User user, BoardRequestDto boardRequestDto) {
        User curUser = userService.findById(user.getId());

        if(!UserRole.ADMIN.equals(curUser.getUserRole())) {
            throw new BusinessException("해당 기능은 ADMIN만 사용할 수 있습니다.");
        }

        Board board = Board.of(boardRequestDto);
        boardRepository.save(board);

        return BoardResponseDto.of(board);
    }

    public Board findById(long id) {
        Board board= boardRepository.findById(id).orElseThrow(
            () -> new NoSuchElementException("일치하는 보드가 없습니다.")
        );

        return board;
    }
}
