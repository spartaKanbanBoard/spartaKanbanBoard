package com.sparta.spartakanbanboard.domain.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;

    @GetMapping("/redis/test")
    public ResponseEntity<String> testRedis() {
        redisService.testRedis();
        return ResponseEntity.ok().body("Redis test 완료");
    }

}
