package com.sparta.spartakanbanboard.domain.user.controller;

import com.sparta.spartakanbanboard.domain.user.dto.LoginRequestDto;
import com.sparta.spartakanbanboard.domain.user.dto.SignupRequestDto;
import com.sparta.spartakanbanboard.domain.user.service.UserService;
import com.sparta.spartakanbanboard.domain.user.service.global.dto.CommonResponseDto;
import com.sparta.spartakanbanboard.domain.user.service.global.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
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
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto,
                                                   HttpServletResponse response) {
        CommonResponseDto responseDto = userService.login(loginRequestDto, response);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<CommonResponseDto> logout(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommonResponseDto responseDto = userService.logout(userDetails.getUser());
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<CommonResponseDto> refresh(HttpServletRequest request,
                                                     HttpServletResponse response) {
        CommonResponseDto responseDto = userService.refresh(request,response);
        return ResponseEntity.ok().body(responseDto);
    }

}
