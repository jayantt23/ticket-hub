package com.jayant.booking_service.repository;

import com.jayant.booking_service.entity.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    boolean existsByShowIdAndSeatNumber(Long showId, String seatNumber);

    void deleteByBookingId(Long id);
}
