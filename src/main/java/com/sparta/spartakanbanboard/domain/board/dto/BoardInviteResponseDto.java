package com.sparta.spartakanbanboard.domain.board.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardInviteResponseDto {
    private List<String> usernames;

    public static BoardInviteResponseDto of(List<BoardInviteRequestDto> boardInviteRequestDto) {

        List<String> users = new ArrayList<>();

        boardInviteRequestDto.stream()
            .forEach(dto -> users.add(dto.getUsername()));

        return BoardInviteResponseDto.builder()
            .usernames(users)
            .build();
    }
}
