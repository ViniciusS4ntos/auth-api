package com.vinicius.auth_api.business;

import com.vinicius.auth_api.infrastructure.exception.InvalidTokenException;
import com.vinicius.auth_api.security.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final StringRedisTemplate redisTemplate;
    private final JwtUtil jwtUtil;

    @Value("${token-Expiration}")
    private Integer duration;

    public RedisService(StringRedisTemplate redisTemplate, JwtUtil jwtUtil) {
        this.redisTemplate = redisTemplate;
        this.jwtUtil = jwtUtil;
    }

    public void salvarToken(String email, String token) {
        redisTemplate.opsForValue().set(email, token, duration, TimeUnit.SECONDS);
    }

    // validar token == tokenRedis
    public void validarToken(String token){

        String email = jwtUtil.extractUsername(token.substring(7));

        String tokenRedis = redisTemplate.opsForValue().get(email);

        token = token.substring(7);

        if (!tokenRedis.equals(token)) {
            throw new InvalidTokenException("Token invalido! " + token);
        }
    }

}
