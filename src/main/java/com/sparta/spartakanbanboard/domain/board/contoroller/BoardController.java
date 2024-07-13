package com.sparta.spartakanbanboard.domain.board.contoroller;

import com.sparta.spartakanbanboard.domain.board.dto.BoardDetailsResponseDto;
import com.sparta.spartakanbanboard.domain.board.dto.BoardInviteRequestDto;
import com.sparta.spartakanbanboard.domain.board.dto.BoardInviteResponseDto;
import com.sparta.spartakanbanboard.domain.board.dto.BoardRequestDto;
import com.sparta.spartakanbanboard.domain.board.dto.BoardResponseDto;
import com.sparta.spartakanbanboard.domain.board.dto.UserRoleResponseDto;
import com.sparta.spartakanbanboard.domain.board.entity.Board;
import com.sparta.spartakanbanboard.domain.board.service.BoardService;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import com.sparta.spartakanbanboard.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.UserDatabase;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/admins/boards")
    public ResponseEntity<?> createBoard(
        @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
        @Valid @RequestBody BoardRequestDto boardRequestDto) {

        User user = userDetailsImpl.getUser();
        BoardResponseDto boardResponseDto = boardService.createBoard(user, boardRequestDto);

        CommonResponseDto commonResponseDto = CommonResponseDto.builder()
            .data(boardResponseDto)
            .msg("보드 생성 완료 !")
            .build();

        return ResponseEntity.ok().body(commonResponseDto);
    }

    @GetMapping("/admins/boards")
    public ResponseEntity<?> getBoardList
        (
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
            @RequestParam(value = "page",defaultValue = "1") int page,
            @RequestParam("size") int size,
            @RequestParam(value = "sortBy",defaultValue = "createdAt") String sortBy
            ) {

        User user = userDetailsImpl.getUser();
        Slice<Board> boards = boardService.getBoardList(user, page, size, sortBy);

        CommonResponseDto commonResponseDto = CommonResponseDto.builder()
            .data(boards)
            .msg("보드 조회 완료 !")
            .build();

        return ResponseEntity.ok().body(commonResponseDto);
    }

    @GetMapping("/boards")
    public ResponseEntity<?> getMyBoardList
        (
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
            @RequestParam(value = "page",defaultValue = "1") int page,
            @RequestParam("size") int size,
            @RequestParam(value = "sortBy",defaultValue = "createdAt") String sortBy
        ) {
        User user = userDetailsImpl.getUser();
        Slice<Board> boards = boardService.getMyBoardList(user, page, size, sortBy);

        CommonResponseDto commonResponseDto = CommonResponseDto.builder()
            .data(boards)
            .msg(user.getUserName() + "님의 보드 조회 완료 !")
            .build();

        return ResponseEntity.ok().body(commonResponseDto);
    }

    @PutMapping("/admins/boards/{boardId}")
    public ResponseEntity<?> updateBoard(
        @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
        @PathVariable("boardId") long boardId,
        @RequestBody BoardRequestDto boardRequestDto
    ) {
        User user = userDetailsImpl.getUser();
        BoardResponseDto boardResponseDto = boardService.updateBoard(user, boardRequestDto, boardId);

        CommonResponseDto commonResponseDto = CommonResponseDto.builder()
            .data(boardResponseDto)
            .msg("보드 수정 완료 !")
            .build();

        return ResponseEntity.ok().body(commonResponseDto);
    }

    @DeleteMapping("/admins/boards/{boardId}")
    public ResponseEntity<?> deleteBoard(
        @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
        @PathVariable("boardId") long boardId
    ) {
        User user = userDetailsImpl.getUser();
        boardService.deleteBoard(user, boardId);

        CommonResponseDto commonResponseDto = CommonResponseDto.builder()
            .msg("보드 삭제 완료 !")
            .build();

        return ResponseEntity.ok().body(commonResponseDto);
    }

    @PostMapping("/admins/boards/{boardId}")
    public ResponseEntity<?> inviteUserToBoard(
        @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
        @PathVariable("boardId") long boardId,
        @RequestBody List<BoardInviteRequestDto> BoardInviteRequestDtos
    ) {
        User user = userDetailsImpl.getUser();
        BoardInviteResponseDto boardInviteResponseDto = boardService.inviteUserToBoard(user, boardId, BoardInviteRequestDtos);

        CommonResponseDto commonResponseDto = CommonResponseDto.builder()
            .msg("보드에 유저들 초대 완료 !")
            .data(boardInviteResponseDto)
            .build();

        return ResponseEntity.ok().body(commonResponseDto);
    }

    @GetMapping("/boards/{boardId}")
    public ResponseEntity<?> getMyBoard(
        @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
        @PathVariable("boardId") long boardId
    ) {
        User user = userDetailsImpl.getUser();
        BoardDetailsResponseDto boardDetailsResponseDto = boardService.getMyBoard(user, boardId);

        CommonResponseDto commonResponseDto = CommonResponseDto.builder()
            .msg("보드 조회 완료 !")
            .data(boardDetailsResponseDto)
            .build();

        return ResponseEntity.ok().body(commonResponseDto);
    }

    @GetMapping("/users/current")
    public ResponseEntity<?> getCurrentUser(
        @AuthenticationPrincipal UserDetailsImpl userDetailsImpl
    ) {
        User user = userDetailsImpl.getUser();
        UserRoleResponseDto userRoleResponseDto = boardService.getCurrentUser(user);

        CommonResponseDto commonResponseDto = CommonResponseDto.builder()
            .msg("유저 role 정보 조회 완료!")
            .data(userRoleResponseDto)
            .build();

        return ResponseEntity.ok().body(commonResponseDto);
    }
}
