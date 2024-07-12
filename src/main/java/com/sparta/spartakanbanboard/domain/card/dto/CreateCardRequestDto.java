package com.sparta.spartakanbanboard.domain.card.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateCardRequestDto {

    private Long writerId;

    @NotBlank
    private String title;

    private String content;
}
