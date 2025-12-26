package com.jayant.booking_service.service;

import java.util.List;

public interface RedisLockService {
    boolean acquireLock(Long showId, List<String> seats, Long userId);
    void releaseLock(Long showId, List<String> seats);
}
