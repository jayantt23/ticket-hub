package com.jayant.booking_service.service.impl;

import com.jayant.booking_service.client.CatalogClient;
import com.jayant.booking_service.dto.*;
import com.jayant.booking_service.entity.Booking;
import com.jayant.booking_service.entity.BookingStatus;
import com.jayant.booking_service.entity.ShowSeat;
import com.jayant.booking_service.repository.BookingRepository;
import com.jayant.booking_service.repository.ShowSeatRepository;
import com.jayant.booking_service.service.BookingProducer;
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

    private final BookingProducer bookingProducer;

    @Override
    @Transactional
    public BookingResponseDto createBooking(CreateBookingRequest request) {

        boolean locked = redisLockService.acquireLock(
                request.getShowId(),
                request.getSeats(),
                request.getUserId()
        );

        if(!locked) {
            throw new RuntimeException("Some seats are already selected by another user.");
        }

        try {
            ShowDetailsDto show = catalogClient.getShow(request.getShowId());

            validateSeatsExistInLayout(request.getSeats(), show.getSeatLayout());

            for (String seatNum : request.getSeats()) {
                if (showSeatRepository.existsByShowIdAndSeatNumber(request.getShowId(), seatNum)) {
                    throw new RuntimeException("Seat " + seatNum + " is already booked!");
                }
            }

            BigDecimal totalAmount = show.getBasePrice().multiply(new BigDecimal(request.getSeats().size()));

            Booking booking = new Booking();
            booking.setUserId(request.getUserId());
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
                seat.setBooking(savedBooking);
                seat.setLocked(true);
                return seat;
            }).toList();

            showSeatRepository.saveAll(showSeats);

            BookingPlacedEvent event = new BookingPlacedEvent();
            event.setBookingId(savedBooking.getId());
            event.setUserId(savedBooking.getUserId());
            event.setAmount(savedBooking.getAmount().doubleValue());
            event.setEmail(request.getEmail());

            bookingProducer.sendBookingEvent(event);

            return mapToDto(savedBooking);
        } catch (Exception e) {
            redisLockService.releaseLock(request.getShowId(), request.getSeats());
            throw new RuntimeException("Booking failed", e);
        }
    }

    @Override
    public ShowDetailsDto getShowDetails(Long showId) {
        ShowDetailsDto showDto = catalogClient.getShow(showId);

        List<ShowSeat> occupiedSeats = showSeatRepository.findByShowId(showId);

        List<String> bookedSeatNumbers = occupiedSeats.stream()
                .map(ShowSeat::getSeatNumber)
                .toList();

        showDto.setBookedSeats(bookedSeatNumbers);

        return showDto;
    }

    @Override
    public BookingStatusResponse getBookingStatus(Long bookingId, Long currentUserId) {
        return bookingRepository.findById(bookingId)
                .map(booking -> {
                    if (!booking.getUserId().equals(currentUserId)) {
                        throw new RuntimeException("Unauthorized access to booking: " + bookingId);
                    }

                    return new BookingStatusResponse(
                            booking.getId(),
                            booking.getStatus().toString()
                    );
                })
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
    }

    private void validateSeatsExistInLayout(List<String> requestedSeats, SeatLayout layout) {
        if (layout == null || layout.getRows() == null) {
            throw new RuntimeException("Invalid Hall Layout configuration.");
        }

        for (String reqSeat : requestedSeats) {
            boolean found = false;

            String rowLabel = reqSeat.replaceAll("\\d", "");
            int seatNumber = Integer.parseInt(reqSeat.replaceAll("\\D", ""));

            for (SeatRow row : layout.getRows()) {
                if (row.getRowLabel().equalsIgnoreCase(rowLabel)) {
                    for (Seat seat : row.getSeats()) {
                        if (seat.getNumber() == seatNumber) {
                            found = true;
                            break;
                        }
                    }
                }
                if (found) break;
            }

            if (!found) {
                throw new RuntimeException("Invalid Seat: " + reqSeat + " does not exist in this hall!");
            }
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