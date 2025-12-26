package com.jayant.booking_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "show_seats", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"show_id", "seat_number"})
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "show_id", nullable = false)
    private Long showId;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private boolean isLocked;
}
