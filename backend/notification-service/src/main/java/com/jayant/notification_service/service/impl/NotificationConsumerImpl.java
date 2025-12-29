package com.jayant.notification_service.service.impl;

import com.jayant.notification_service.dto.PaymentProcessedEvent;
import com.jayant.notification_service.service.EmailService;
import com.jayant.notification_service.service.NotificationConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationConsumerImpl implements NotificationConsumer {

    private final EmailService emailService;

    @Override
    @KafkaListener(topics = "payment-events", groupId = "notification-group")
    public void handlePaymentEvent(PaymentProcessedEvent event) {
        log.info("Received Payment Event for Booking: {}", event.getBookingId());

        if("SUCCESS".equals(event.getStatus())) {
            emailService.sendBookingConfirmation(
                    event.getEmail(),
                    event.getBookingId(),
                    event.getTransactionId()
            );
        } else {
            log.info("Payment failed. Sending cancellation email.");
            emailService.sendBookingCancellation(
                    event.getEmail(),
                    event.getBookingId(),
                    event.getTransactionId()
            );
        }
    }
}
