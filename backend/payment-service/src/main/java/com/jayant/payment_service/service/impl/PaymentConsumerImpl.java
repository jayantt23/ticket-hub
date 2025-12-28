package com.jayant.payment_service.service.impl;

import com.jayant.payment_service.dto.BookingPlacedEvent;
import com.jayant.payment_service.dto.PaymentProcessedEvent;
import com.jayant.payment_service.entity.Payment;
import com.jayant.payment_service.entity.PaymentStatus;
import com.jayant.payment_service.gateway.PaymentGateway;
import com.jayant.payment_service.repository.PaymentRepository;
import com.jayant.payment_service.service.PaymentConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentConsumerImpl implements PaymentConsumer {

    private final PaymentRepository paymentRepository;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final PaymentGateway paymentGateway;

    @Override
    @KafkaListener(topics = "booking-events", groupId = "payment-group")
    public void processPayment(BookingPlacedEvent event) {
        log.info("Received Booking Event for Id: {}", event.getBookingId());

        boolean status = paymentGateway.processPayment(event.getAmount(), event.getUserId().toString());
        String txnId = UUID.randomUUID().toString();

        Payment payment = new Payment();
        payment.setBookingId(event.getBookingId());
        payment.setUserId(event.getUserId());
        payment.setAmount(event.getAmount());
        payment.setTransactionId(txnId);
        payment.setStatus(status ? PaymentStatus.SUCCESS : PaymentStatus.FAILED);
        payment.setPaymentTime(LocalDateTime.now());

        Payment savedPayment = paymentRepository.save(payment);

        PaymentProcessedEvent outEvent = new PaymentProcessedEvent();
        outEvent.setBookingId(event.getBookingId());
        outEvent.setUserId(event.getUserId());
        outEvent.setEmail(event.getEmail());
        outEvent.setStatus(status ? "SUCCESS" : "FAILED");
        outEvent.setTransactionId(txnId);

        kafkaTemplate.send("payment-events", outEvent);

        log.info("Payment processed: {} | Sent to 'payment-events", status);
    }
}
