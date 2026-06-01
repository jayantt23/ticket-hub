package com.jayant.booking_service.service;

public interface DeadLetterConsumer {
    void listenToDLQ(String payload);
}
