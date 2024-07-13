package com.sparta.spartakanbanboard.domain.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    @NotNull
    private String userName;
    @NotNull
    private String password;
    private String role = "";
    private String adminToken = "";

}
