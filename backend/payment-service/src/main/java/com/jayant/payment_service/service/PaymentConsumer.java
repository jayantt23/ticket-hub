package com.jayant.payment_service.service;

import com.jayant.payment_service.dto.BookingPlacedEvent;

public interface PaymentConsumer {
    void processPayment(BookingPlacedEvent event);
}
