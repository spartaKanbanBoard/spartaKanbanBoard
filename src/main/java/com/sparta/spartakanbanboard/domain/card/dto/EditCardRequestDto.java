package com.sparta.spartakanbanboard.domain.card.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.spartakanbanboard.domain.card.entity.State;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class EditCardRequestDto {

    private Long writerId;

    @NotBlank
    private String title;

    private String content;

    private State state;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime endTime;
}
