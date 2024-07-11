package com.sparta.spartakanbanboard.domain.board.service;

import com.sparta.spartakanbanboard.domain.board.dto.BoardCreateRequestDto;
import com.sparta.spartakanbanboard.domain.board.dto.BoardCreateResponseDto;
import com.sparta.spartakanbanboard.domain.board.dto.BoardListResponseDto;
import com.sparta.spartakanbanboard.domain.board.entity.Board;
import com.sparta.spartakanbanboard.domain.board.repository.BoardRepository;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import com.sparta.spartakanbanboard.domain.user.service.UserService;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserService userService;

    public BoardCreateResponseDto createBoard(User user, BoardCreateRequestDto boardRequestDto) {
        User curUser = userService.findById(user.getId());

        checkADMINUser(curUser);

        Board board = Board.of(boardRequestDto);
        boardRepository.save(board);

        return BoardCreateResponseDto.of(board);
    }

    public Board findById(long id) {
        Board board= boardRepository.findById(id).orElseThrow(
            () -> new NoSuchElementException("일치하는 보드가 없습니다.")
        );

        return board;
    }

    public void checkADMINUser(User user) {
        if(!UserRole.ADMIN.equals(user.getUserRole())) {
            throw new BusinessException("해당 기능은 ADMIN만 사용할 수 있습니다.");
        }
    }
}
