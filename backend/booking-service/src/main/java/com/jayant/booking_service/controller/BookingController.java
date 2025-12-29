package com.jayant.booking_service.controller;

import com.jayant.booking_service.dto.BookingResponseDto;
import com.jayant.booking_service.dto.BookingStatusResponse;
import com.jayant.booking_service.dto.CreateBookingRequest;
import com.jayant.booking_service.dto.ShowDetailsDto;
import com.jayant.booking_service.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/bookings")
@Tag(name = "Booking API", description = "Endpoints for managing ticket bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    @Operation(summary = "Health Check")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Booking service is running");
    }

    @PostMapping
    @Operation(summary = "Create a Booking", description = "Reserves seats and initiates payment saga")
    public ResponseEntity<BookingResponseDto> createBooking(
            @RequestBody CreateBookingRequest request,
            @RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Email") String email
    ) {
        request.setUserId(Long.parseLong(userId));
        request.setEmail(email);

        return ResponseEntity.ok(bookingService.createBooking(request));
    }

    @GetMapping("/show/{showId}")
    @Operation(summary = "Get Show Details", description = "Returns current show details with a list of locked tickets.")
    public ResponseEntity<ShowDetailsDto> getShowDetails(@PathVariable Long showId) {
        return ResponseEntity.ok(bookingService.getShowDetails(showId));
    }

    @GetMapping("/{bookingId}")
    @Operation(summary = "Get Booking Status")
    public ResponseEntity<BookingStatusResponse> getBookingStatus(
            @PathVariable Long bookingId,
            @RequestHeader("X-User-Id") Long userId
    ) {
        return ResponseEntity.ok(bookingService.getBookingStatus(bookingId, userId));
    }
}
