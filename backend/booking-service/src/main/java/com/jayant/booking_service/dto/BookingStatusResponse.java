package com.jayant.booking_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingStatusResponse {
    private Long bookingId;
    private String status; // PENDING, CONFIRMED, REJECTED
}