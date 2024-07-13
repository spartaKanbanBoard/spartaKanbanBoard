//package com.sparta.spartakanbanboard.domain.user.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.TimeUnit;
//
//@Service
//@RequiredArgsConstructor
//public class TokenService {
//
//    private final RedisTemplate<String, String> redisTemplate;
//
//    public void saveRefreshToken(String username, String refreshToken) {
//        redisTemplate.opsForValue().set(username, refreshToken, 14, TimeUnit.DAYS);
//    }
//
//    public String getRefreshToken(String username) {
//        return redisTemplate.opsForValue().get(username);
//    }
//
//    public void deleteRefreshToken(String username) {
//        redisTemplate.delete(username);
//    }
//}
//
