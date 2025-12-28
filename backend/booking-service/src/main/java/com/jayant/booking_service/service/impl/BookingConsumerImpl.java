package com.jayant.booking_service.service.impl;

import com.jayant.booking_service.dto.PaymentProcessedEvent;
import com.jayant.booking_service.entity.Booking;
import com.jayant.booking_service.entity.BookingStatus;
import com.jayant.booking_service.repository.BookingRepository;
import com.jayant.booking_service.repository.ShowSeatRepository;
import com.jayant.booking_service.service.BookingConsumer;
import com.jayant.booking_service.service.RedisLockService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingConsumerImpl implements BookingConsumer {

    private final BookingRepository bookingRepository;

    private final ShowSeatRepository showSeatRepository;

    private final RedisLockService redisLockService;

    @Override
    @KafkaListener(topics = "payment-events", groupId = "booking-group")
    @Transactional
    public void handlePaymentResult(PaymentProcessedEvent event) {
        log.info("Received Payment Result for Booking ID: {} | Status: {}", event.getBookingId(), event.getStatus());

        Booking booking = bookingRepository.findById(event.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found for ID: " + event.getBookingId()));

        if("SUCCESS".equals(event.getStatus())) {
            booking.setStatus(BookingStatus.CONFIRMED);
            log.info("Booking {} CONFIRMED!", event.getBookingId());
        } else {
            booking.setStatus(BookingStatus.CANCELLED);

            redisLockService.releaseLock(booking.getShowId(), booking.getSeats());
            showSeatRepository.deleteByBookingId(booking.getId());

            log.info("Booking {} CANCELLED due to Payment Failure.", event.getBookingId());
        }

        bookingRepository.save(booking);
    }
}
