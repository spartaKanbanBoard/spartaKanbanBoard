package com.sparta.spartakanbanboard.domain.card.dto;

import com.sparta.spartakanbanboard.domain.card.entity.State;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateCardRequestDto {

    private Long userId;

    @NotBlank
    private String title;

    private String content;
}
