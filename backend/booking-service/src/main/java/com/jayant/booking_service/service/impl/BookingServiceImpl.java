package com.jayant.booking_service.service.impl;

import com.jayant.booking_service.client.CatalogClient;
import com.jayant.booking_service.dto.BookingResponseDto;
import com.jayant.booking_service.dto.CreateBookingRequest;
import com.jayant.booking_service.dto.ShowDetailsDto;
import com.jayant.booking_service.entity.Booking;
import com.jayant.booking_service.entity.BookingStatus;
import com.jayant.booking_service.entity.ShowSeat;
import com.jayant.booking_service.repository.BookingRepository;
import com.jayant.booking_service.repository.ShowSeatRepository;
import com.jayant.booking_service.service.BookingService;
import com.jayant.booking_service.service.RedisLockService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final ShowSeatRepository showSeatRepository;

    private final RedisLockService redisLockService;

    private final CatalogClient catalogClient;

    @Override
    @Transactional
    public BookingResponseDto createBooking(CreateBookingRequest request, Long userId) {

        boolean locked = redisLockService.acquireLock(
                request.getShowId(),
                request.getSeats(),
                userId
        );

        if(!locked) {
            throw new RuntimeException("Some seats are already selected by another user.");
        }

        try {
            for (String seatNum : request.getSeats()) {
                if (showSeatRepository.existsByShowIdAndSeatNumber(request.getShowId(), seatNum)) {
                    throw new RuntimeException("Seat " + seatNum + " is already booked!");
                }
            }

            ShowDetailsDto show = catalogClient.getShow(request.getShowId());
            BigDecimal totalAmount = show.getBasePrice().multiply(new BigDecimal(request.getSeats().size()));

            Booking booking = new Booking();
            booking.setUserId(userId);
            booking.setShowId(request.getShowId());
            booking.setStatus(BookingStatus.PENDING);
            booking.setAmount(totalAmount);
            booking.setBookingTime(LocalDateTime.now());
            booking.setSeats(request.getSeats());

            Booking savedBooking = bookingRepository.save(booking);

            List<ShowSeat> showSeats = request.getSeats().stream().map(seatNum -> {
                ShowSeat seat = new ShowSeat();
                seat.setShowId(request.getShowId());
                seat.setSeatNumber(seatNum);
                seat.setBooking(savedBooking); // Link to the booking
                seat.setLocked(true); // Mark as locked/booked
                return seat;
            }).toList();

            showSeatRepository.saveAll(showSeats);

            return mapToDto(savedBooking);
        } catch (Exception e) {
            redisLockService.releaseLock(request.getShowId(), request.getSeats());
            throw new RuntimeException("Booking failed", e);
        }
    }

    private BookingResponseDto mapToDto(Booking booking) {
        return BookingResponseDto.builder()
                .bookingId(booking.getId())
                .showId(booking.getShowId())
                .seats(booking.getSeats())
                .amount(booking.getAmount())
                .status(booking.getStatus())
                .bookingTime(booking.getBookingTime())
                .build();
    }
}