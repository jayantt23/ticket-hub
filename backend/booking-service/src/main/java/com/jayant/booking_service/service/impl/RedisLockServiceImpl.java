package com.jayant.booking_service.service.impl;

import com.jayant.booking_service.service.RedisLockService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisLockServiceImpl implements RedisLockService {

    private final StringRedisTemplate redisTemplate;

    @Override
    public boolean acquireLock(Long showId, List<String> seats, Long userId) {

        for(String seat : seats) {
            String key = getLockKey(showId, seat);

            Boolean success = redisTemplate.opsForValue()
                    .setIfAbsent(key, userId.toString(), Duration.ofMinutes(10));

            if(Boolean.FALSE.equals(success)) {
                releaseLock(showId, seats);
                return false;
            }
        }

        return true;
    }

    @Override
    public void releaseLock(Long showId, List<String> seats) {
        for(String seat : seats) {
            redisTemplate.delete(getLockKey(showId, seat));
        }
    }

    private String getLockKey(Long showId, String seat) {
        return "lock:show:" + showId + ":seat:" + seat;
    }
}
