package com.jayant.booking_service.service.impl;

import com.jayant.booking_service.dto.BookingPlacedEvent;
import com.jayant.booking_service.service.BookingProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingProducerImpl implements BookingProducer {

    private final KafkaTemplate<String, BookingPlacedEvent> kafkaTemplate;

    @Override
    public void sendBookingEvent(BookingPlacedEvent event) {
        log.info("Event => {}", event.toString());

        Message<BookingPlacedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "booking-events")
                .build();

        kafkaTemplate.send(message);
    }

}
