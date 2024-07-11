package com.sparta.spartakanbanboard.domain.board.contoroller;

import com.sparta.spartakanbanboard.domain.board.dto.BoardCreateRequestDto;
import com.sparta.spartakanbanboard.domain.board.dto.BoardCreateResponseDto;
import com.sparta.spartakanbanboard.domain.board.dto.BoardListResponseDto;
import com.sparta.spartakanbanboard.domain.board.service.BoardService;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/admins/boards")
    public ResponseEntity<?> createBoard(
        @AuthenticationPrincipal userDetailsImpl,
        @Valid @RequestBody BoardCreateRequestDto boardRequestDto) {

        User user = userDetailsImpl.getUser();
        BoardCreateResponseDto boardResponseDto = boardService.createBoard(user, boardRequestDto);

        CommonResponseDto commonResponseDto = CommonResponseDto.builder()
            .data(boardResponseDto)
            .msg("보드 생성 완료 !")
            .build();

        return ResponseEntity.ok().body(commonResponseDto);
    }


}
