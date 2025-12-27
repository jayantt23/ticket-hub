package com.jayant.booking_service.service;

import com.jayant.booking_service.dto.BookingPlacedEvent;

public interface BookingProducer {
    void sendBookingEvent(BookingPlacedEvent event);
}
