package com.jayant.catalog_service.entity;

import com.jayant.catalog_service.dto.SeatLayout;
import com.jayant.catalog_service.util.SeatLayoutConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "halls")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hall implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "seat_layout", columnDefinition = "jsonb", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private SeatLayout seatLayout;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_id")
    @ToString.Exclude
    private Theatre theatre;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Show> shows;

}
