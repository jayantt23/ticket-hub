package com.jayant.booking_service.service.impl;

import com.jayant.booking_service.service.DeadLetterConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeadLetterConsumerImpl implements DeadLetterConsumer {

    @Override
    @KafkaListener(topics = "payment-events.DLT", groupId = "booking-dlq-group")
    public void listenToDLQ(String payload) {
        log.error("POISON MESSAGE DETECTED! Moved to DLQ.");
        log.error("Payload: {}", payload);
    }
}
