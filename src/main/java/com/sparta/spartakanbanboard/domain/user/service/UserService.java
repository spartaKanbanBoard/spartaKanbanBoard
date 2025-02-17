package com.sparta.spartakanbanboard.domain.user.service;

import com.sparta.spartakanbanboard.domain.user.dto.LoginRequestDto;
import com.sparta.spartakanbanboard.domain.user.dto.SignupRequestDto;
import com.sparta.spartakanbanboard.domain.user.dto.TokenResponseDto;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import com.sparta.spartakanbanboard.domain.user.entity.UserRole;
import com.sparta.spartakanbanboard.domain.user.entity.UserStatus;
import com.sparta.spartakanbanboard.domain.user.repository.UserRepository;
import com.sparta.spartakanbanboard.global.BusinessLogicException;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import com.sparta.spartakanbanboard.global.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
//    private final TokenService tokenService;
    private final JwtUtil jwtUtil;
    @Value("${ADMIN_TOKEN}")
    String adminToken;

    //회원가입
    public CommonResponseDto<?> signup(SignupRequestDto signupRequestDto) {
        String userName = signupRequestDto.getUserName();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        UserRole userRole = UserRole.USER;

        if (userRepository.findByUserName(userName).isPresent()) {
            throw new BusinessLogicException("중복된 사용자 ID가 존재합니다.");
        }

        if (signupRequestDto.getRole().equals("ADMIN")) {
            if (signupRequestDto.getAdminToken() != null && signupRequestDto.getAdminToken().equals(adminToken)) {
                userRole = UserRole.ADMIN;
            } else {
                throw new BusinessLogicException("adminToken이 올바르지 않습니다.");
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

    //로그인
    public CommonResponseDto<?> login(LoginRequestDto requestDto, HttpServletResponse response) {
        User user = findByUserName(requestDto.getUserName());

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new BusinessLogicException("잘못된 비밀번호입니다.");
        }
        String accessToken = jwtUtil.createAccessToken(user.getUserName(),user.getUserRole());
        String refreshToken = jwtUtil.createRefreshToken(user.getUserName());

        // redis 에 토큰 저장 및 유저 상태 변경
        String substringRefreshToken = refreshToken.substring(JwtUtil.BEARER_PREFIX.length());
//        tokenService.saveRefreshToken(user.getUserName(), substringRefreshToken);

        //유저 상태 변경
        user.updateRefreshToken(substringRefreshToken);
        user.login();
        userRepository.save(user);

        // 헤더에 추가
        jwtUtil.addJwtToHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken, response);
        jwtUtil.addJwtToHeader(JwtUtil.REFRESH_HEADER, refreshToken, response);

        TokenResponseDto responseDto = TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return CommonResponseDto.builder()
                .msg("로그인 되었습니다.")
                .data(responseDto)
                .build();
    }

    //로그아웃
    public CommonResponseDto<?> logout(User user) {
        user.logout();
        user.updateRefreshToken(null);
//        tokenService.deleteRefreshToken(user.getUserName());
        userRepository.save(user);

        return CommonResponseDto.builder()
                .msg("로그아웃 되었습니다.")
                .build();
    }

    public CommonResponseDto<?> refresh(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = jwtUtil.getTokenFromHeader(JwtUtil.REFRESH_HEADER, request);

        if (!jwtUtil.validateToken(refreshToken)) {
            throw new BusinessLogicException("리프레시 토큰도 만료되었습니다. 다시 로그인 해주세요.");
        }

        Claims info = jwtUtil.getUserInfoFromToken(refreshToken);
        String userName = info.getSubject();

        User user = findByUserName(userName);

        if (!refreshToken.equals(user.getRefreshToken())) {
            throw new BusinessLogicException("해당 유저의 리프레시 토큰이 일치하지 않습니다.");
        }

        String newAccessToken = jwtUtil.createAccessToken(user.getUserName(),user.getUserRole());
        String newRefreshToken = jwtUtil.createRefreshToken(user.getUserName());

        jwtUtil.addJwtToHeader(JwtUtil.AUTHORIZATION_HEADER, newAccessToken, response);
        jwtUtil.addJwtToHeader(JwtUtil.REFRESH_HEADER, newRefreshToken, response);

        String substringRefreshToken = newRefreshToken.substring(JwtUtil.BEARER_PREFIX.length());
        user.updateRefreshToken(substringRefreshToken);
        userRepository.save(user);

        TokenResponseDto responseDto = TokenResponseDto.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();

        return CommonResponseDto.builder()
                .msg("토큰이 재발급되었습니다.")
                .data(responseDto)
                .build();

    }

    public User findByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new BusinessLogicException("해당 유저를 찾을 수 없습니다"));
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() ->
                new BusinessLogicException("해당 유저를 찾을 수 없습니다."));
    }

}
