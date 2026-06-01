package com.jayant.booking_service.repository;

import com.jayant.booking_service.entity.Booking;
import com.jayant.booking_service.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByStatusAndBookingTimeBefore(BookingStatus status, LocalDateTime timeThreshold);
}
