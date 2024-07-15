package com.sparta.spartakanbanboard.domain.card.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.spartakanbanboard.domain.card.entity.State;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Builder
@AllArgsConstructor
public class EditCardRequestDto {

    private String username;

    @NotBlank
    private String title;

    private String content;

    private State state;
    //프론트에서 -> 서버로할때
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    //서버에서 -> 프론트로 할때
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime endTime;
}
