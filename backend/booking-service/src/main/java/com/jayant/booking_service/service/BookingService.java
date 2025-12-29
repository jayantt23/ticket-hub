package com.jayant.booking_service.service;

import com.jayant.booking_service.dto.BookingResponseDto;
import com.jayant.booking_service.dto.BookingStatusResponse;
import com.jayant.booking_service.dto.CreateBookingRequest;
import com.jayant.booking_service.dto.ShowDetailsDto;

public interface BookingService {
    BookingResponseDto createBooking(CreateBookingRequest request);
    ShowDetailsDto getShowDetails(Long showId);
    BookingStatusResponse getBookingStatus(Long bookingId, Long currentUserId);
}