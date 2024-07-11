package com.sparta.spartakanbanboard.domain.user.service;

import com.sparta.spartakanbanboard.domain.user.dto.SignupRequestDto;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import com.sparta.spartakanbanboard.domain.user.entity.UserRole;
import com.sparta.spartakanbanboard.domain.user.entity.UserStatus;
import com.sparta.spartakanbanboard.domain.user.repository.UserRepository;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import com.sparta.spartakanbanboard.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    @Value("${ADMIN_TOKEN}")
    String adminToken;

    public CommonResponseDto signup(SignupRequestDto signupRequestDto) {
        String userName = signupRequestDto.getUserName();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        UserRole userRole = UserRole.USER;

        if (userRepository.findByUserName(userName).isPresent()) {
            throw new RuntimeException("중복된 사용자 ID가 존재합니다.");
        }

        if (signupRequestDto.getRole().equals("ADMIN")) {
            if (signupRequestDto.getAdminToken() != null && signupRequestDto.getAdminToken().equals(adminToken)) {
                userRole = UserRole.ADMIN;
            } else {
                throw new IllegalArgumentException("adminToken이 올바르지 않습니다.");
            }
        }

        User user = User.builder()
                .userName(userName)
                .password(password)
                .userStatus(UserStatus.ACTIVE)
                .userRole(userRole)
                .build();

        userRepository.save(user);

        return CommonResponseDto.builder()
                .msg("회원가입이 완료되었습니다.")
                .build();
    }

    public User findByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("해당 유저를 찾을 수 없습니다"));
    }

}
