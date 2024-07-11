package com.sparta.spartakanbanboard.domain.board.contoroller;

import com.sparta.spartakanbanboard.domain.board.dto.BoardRequestDto;
import com.sparta.spartakanbanboard.domain.board.dto.BoardResponseDto;
import com.sparta.spartakanbanboard.domain.board.service.BoardService;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/boards")
    public ResponseEntity<CommonResponseDto<BoardResponseDto>> createBoard
        (
        @AuthenticationPrincipal userDetailsImpl,
        @Valid @RequestBody BoardRequestDto boardRequestDto
            ) {

        User user = userDetailsImpl.getUser();
        BoardResponseDto boardResponseDto = boardService.createBoard(user,boardRequestDto);

        CommonResponseDto commonResponseDto = CommonResponseDto.builder()
            .data(responseDto)
            .msg("보드 생성 완료 !");

        return ResponseEntity.ok().body(commonResponseDto);
    }


}
