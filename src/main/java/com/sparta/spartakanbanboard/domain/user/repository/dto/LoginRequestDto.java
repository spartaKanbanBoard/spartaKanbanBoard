package com.sparta.spartakanbanboard.domain.user.repository.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String userName;
    private String password;
}
