package com.jayant.payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingPlacedEvent {
    private Long bookingId;
    private Long userId;
    private Double amount;
    private String email;
}