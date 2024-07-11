package com.sparta.spartakanbanboard.domain.board.contoroller;

import com.sparta.spartakanbanboard.domain.board.dto.BoardCreateRequestDto;
import com.sparta.spartakanbanboard.domain.board.dto.BoardCreateResponseDto;
import com.sparta.spartakanbanboard.domain.board.dto.BoardListResponseDto;
import com.sparta.spartakanbanboard.domain.board.entity.Board;
import com.sparta.spartakanbanboard.domain.board.service.BoardService;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import com.sparta.spartakanbanboard.global.security.UserDetailsImpl;
import com.sparta.spartakanbanboard.global.security.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<CommonResponseDto<BoardCreateResponseDto>> createBoard(
        @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
        @Valid @RequestBody BoardCreateRequestDto boardRequestDto) {

        User user = userDetailsImpl.getUser();
        BoardCreateResponseDto boardResponseDto = boardService.createBoard(user, boardRequestDto);

        CommonResponseDto commonResponseDto = CommonResponseDto.builder()
            .data(boardResponseDto)
            .msg("보드 생성 완료 !")
            .build();

        return ResponseEntity.ok().body(commonResponseDto);
    }

    @GetMapping("/admins/boards")
    public ResponseEntity<CommonResponseDto<BoardCreateResponseDto>> getBoardList
        (
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
            @RequestParam(value = "page",defaultValue = "0") int page,
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
}
