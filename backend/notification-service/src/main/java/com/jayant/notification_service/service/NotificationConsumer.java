package com.jayant.notification_service.service;

import com.jayant.notification_service.dto.PaymentProcessedEvent;

public interface NotificationConsumer {
    void handlePaymentEvent(PaymentProcessedEvent event);
}
