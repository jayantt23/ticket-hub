package com.jayant.booking_service.service.impl;

import com.jayant.booking_service.entity.Booking;
import com.jayant.booking_service.entity.BookingStatus;
import com.jayant.booking_service.repository.BookingRepository;
import com.jayant.booking_service.repository.ShowSeatRepository;
import com.jayant.booking_service.service.BookingSweeperService;
import com.jayant.booking_service.service.RedisLockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingSweeperServiceImpl implements BookingSweeperService {

    private final BookingRepository bookingRepository;
    private final ShowSeatRepository showSeatRepository;
    private final RedisLockService redisLockService;

    @Scheduled(cron = "0 */5 * * * *")
    @SchedulerLock(
            name = "sweepOrphanedBookings_lock",
            lockAtLeastFor = "2m", // Keep lock for at least 2 mins so others don't run it
            lockAtMostFor = "4m"   // Auto-release after 4 mins in case the node crashes
    )
    @Transactional
    @Override
    public void sweepOrphanedBookings() {
        log.info("Starting background sweep for orphaned bookings...");

        LocalDateTime threshold = LocalDateTime.now().minusMinutes(10);

        List<Booking> orphanedBookings = bookingRepository
                .findByStatusAndBookingTimeBefore(BookingStatus.PENDING, threshold);

        if (orphanedBookings.isEmpty()) {
            log.info("No orphaned bookings found.");
            return;
        }

        for (Booking booking : orphanedBookings) {
            log.warn("Cleaning up orphaned PENDING booking ID: {}", booking.getId());

            booking.setStatus(BookingStatus.CANCELLED);

            showSeatRepository.deleteByBookingId(booking.getId());

            redisLockService.releaseLock(booking.getShowId(), booking.getSeats());

            bookingRepository.save(booking);
        }

        log.info("Sweep complete. Cleaned up {} bookings.", orphanedBookings.size());
    }
}
