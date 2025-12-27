package com.jayant.booking_service.controller;

import com.jayant.booking_service.dto.BookingResponseDto;
import com.jayant.booking_service.dto.CreateBookingRequest;
import com.jayant.booking_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Booking service is running");
    }

    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(
            @RequestBody CreateBookingRequest request,
            @RequestHeader("X-User-Id") String userId
    ) {
        return ResponseEntity.ok(bookingService.createBooking(request, Long.parseLong(userId)));
    }
}
