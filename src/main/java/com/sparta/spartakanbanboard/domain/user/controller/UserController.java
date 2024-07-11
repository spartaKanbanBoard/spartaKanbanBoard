package com.sparta.spartakanbanboard.domain.user.controller;

import com.sparta.spartakanbanboard.domain.user.dto.SignupRequestDto;
import com.sparta.spartakanbanboard.domain.user.service.UserService;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {

        CommonResponseDto responseDto = userService.signup(signupRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
