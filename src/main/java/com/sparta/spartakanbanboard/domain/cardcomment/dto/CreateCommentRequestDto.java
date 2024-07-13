package com.sparta.spartakanbanboard.domain.cardcomment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateCommentRequestDto {

    @NotBlank
    private String content;
}
