package com.jayant.notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProcessedEvent {
    private Long bookingId;
    private Long userId;
    private String email;
    private String status;
    private String transactionId;
}