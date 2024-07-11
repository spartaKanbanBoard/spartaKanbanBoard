package com.sparta.spartakanbanboard.domain.user.dto;

import com.sparta.spartakanbanboard.domain.user.entity.UserRole;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String userName;
    private String password;
    private UserRole userRole;
}
