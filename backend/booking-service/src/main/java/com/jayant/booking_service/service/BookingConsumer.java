package com.jayant.booking_service.service;

import com.jayant.booking_service.dto.PaymentProcessedEvent;

public interface BookingConsumer {
    void handlePaymentResult(PaymentProcessedEvent event);
}
