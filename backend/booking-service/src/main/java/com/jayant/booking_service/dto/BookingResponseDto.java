package com.jayant.booking_service.dto;

import com.jayant.booking_service.entity.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponseDto {
    private Long bookingId;
    private Long showId;
    private List<String> seats;
    private BigDecimal amount;
    private BookingStatus status;
    private LocalDateTime bookingTime;
}
