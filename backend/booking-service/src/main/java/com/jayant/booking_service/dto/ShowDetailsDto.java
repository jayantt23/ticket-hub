package com.jayant.booking_service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ShowDetailsDto {
    private Long showId;
    private String movieTitle;
    private String theatreName;
    private String hallName;
    private SeatLayout seatLayout;
    private BigDecimal basePrice;
    private List<String> bookedSeats;
}
