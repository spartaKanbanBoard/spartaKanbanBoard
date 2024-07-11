package com.sparta.spartakanbanboard.domain.board.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class BoardCreateRequestDto {
    @NotNull(message = "보드 제목은 필수 값 입니다.")
    private String title;
    @NotNull(message = "보드 한 줄 소개는 필수 값 입니다.")
    private String boardInfo;
}
