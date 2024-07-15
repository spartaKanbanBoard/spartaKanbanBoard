package com.sparta.spartakanbanboard.domain.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final StringRedisTemplate stringRedisTemplate;

    public void testRedis() {
        ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();
        ops.set("counter","100");
    }
}
