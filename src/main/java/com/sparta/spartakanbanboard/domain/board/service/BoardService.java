package com.sparta.spartakanbanboard.domain.board.service;

import com.sparta.spartakanbanboard.domain.board.dto.BoardDetailsResponseDto;
import com.sparta.spartakanbanboard.domain.board.dto.BoardInviteRequestDto;
import com.sparta.spartakanbanboard.domain.board.dto.BoardInviteResponseDto;
import com.sparta.spartakanbanboard.domain.board.dto.BoardRequestDto;
import com.sparta.spartakanbanboard.domain.board.dto.BoardResponseDto;
import com.sparta.spartakanbanboard.domain.board.dto.KanbanDetailsResponseDto;
import com.sparta.spartakanbanboard.domain.board.entity.Board;
import com.sparta.spartakanbanboard.domain.board.entity.UserBoardMatcher;
import com.sparta.spartakanbanboard.domain.board.repository.BoardRepository;
import com.sparta.spartakanbanboard.domain.board.repository.UserBoardMatcherRepository;
import com.sparta.spartakanbanboard.domain.column.entity.KanbanColumn;
import com.sparta.spartakanbanboard.domain.column.repository.ColumnRepository;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import com.sparta.spartakanbanboard.domain.user.entity.UserRole;
import com.sparta.spartakanbanboard.domain.user.service.UserService;
import com.sparta.spartakanbanboard.global.BusinessLogicException;
import com.sparta.spartakanbanboard.global.dto.PageDto;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserService userService;
    private final UserBoardMatcherRepository userBoardMatcherRepository;
    private final ColumnRepository columnRepository;

    public BoardResponseDto createBoard(User user, BoardRequestDto boardRequestDto) {
        User curUser = userService.findByUserId(user.getId());

        checkADMINUser(curUser);

        Board board = Board.of(boardRequestDto);
        UserBoardMatcher userBoardMatcher = UserBoardMatcher.builder()
            .user(curUser)
            .board(board)
            .build();

        Board saveBoard = boardRepository.save(board);
        userBoardMatcherRepository.save(userBoardMatcher);

        return BoardResponseDto.of(saveBoard);
    }

    public Slice<Board> getBoardList(User user, int page, int size, String sortBy) {
        User curUser = userService.findByUserId(user.getId());

        checkADMINUser(curUser);

        PageDto pageDto = PageDto.builder()
            .currentPage(page)
            .size(size)
            .sortBy(sortBy)
            .build();

        return boardRepository.searchAllBoard(pageDto.toPageable());

    }

    public Slice<Board> getMyBoardList(User user, int page, int size, String sortBy) {
        User curUser = userService.findByUserId(user.getId());

        PageDto pageDto = PageDto.builder()
            .currentPage(page)
            .size(size)
            .sortBy(sortBy)
            .build();

        return boardRepository.searchMyBoard(curUser, pageDto.toPageable());
    }

    @Transactional
    public BoardResponseDto updateBoard(User user, BoardRequestDto boardRequestDto, long boardId) {
        User curUser = userService.findByUserId(user.getId());

        checkADMINUser(curUser);

        Board board = findById(boardId);
        board.update(boardRequestDto);

        return BoardResponseDto.of(board);
    }

    public void deleteBoard(User user, long boardId) {
        User curUser = userService.findByUserId(user.getId());

        checkADMINUser(curUser);
        Board board = findById(boardId);

        boardRepository.delete(board);
    }

    @Transactional
    public BoardInviteResponseDto inviteUserToBoard(User user, long boardId, List<BoardInviteRequestDto> boardInviteRequestDtos) {
        User curUser = userService.findByUserId(user.getId());

        checkADMINUser(curUser);
        Board board = findById(boardId);

        for(BoardInviteRequestDto requestDto : boardInviteRequestDtos) {
            User invitedUser = userService.findByUserName(requestDto.getUsername());

            if(!isInvitedUser(invitedUser,board)) {
                UserBoardMatcher userBoardMatcher = UserBoardMatcher.builder()
                    .board(board)
                    .user(invitedUser)
                    .build();

                userBoardMatcherRepository.save(userBoardMatcher);
            }
        }

        return BoardInviteResponseDto.of(boardInviteRequestDtos);
    }

    public BoardDetailsResponseDto getMyBoard(User user, long boardId) {
        User curUser = userService.findByUserId(user.getId());
        Board board = findById(boardId);

        if(!isInvitedUser(curUser,board)) {
            throw new BusinessLogicException("보드에 초대된 유저가 아닙니다.");
        }

        List<KanbanColumn> kanbanColumnList = columnRepository.findKanbanColumnsByBoard(board.getId());
        List<KanbanDetailsResponseDto> responseDtoList = kanbanColumnList.stream()
            .map(KanbanDetailsResponseDto::of).toList();

        return BoardDetailsResponseDto.of(responseDtoList);
    }

    public Board findById(long id) {
        Board board= boardRepository.findById(id).orElseThrow(
            () -> new NoSuchElementException("일치하는 보드가 없습니다.")
        );

        return board;
    }

    private void checkADMINUser(User user) {
        if(!UserRole.ADMIN.equals(user.getUserRole())) {
            throw new BusinessLogicException("해당 기능은 ADMIN만 사용할 수 있습니다.");
        }
    }

    private boolean isInvitedUser(User user,Board board) {
        List<UserBoardMatcher> matcher = userBoardMatcherRepository.findUserBoardMatcherByUser(user);

        return matcher.stream()
            .anyMatch(ub -> ub.getBoard().equals(board));
    }
}
