package com.jayant.catalog_service.repository;

import com.jayant.catalog_service.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {

    @Query("SELECT s FROM Show s " +
            "JOIN FETCH s.movie " +
            "JOIN FETCH s.hall h " +
            "JOIN FETCH h.theatre t " +
            "WHERE t.city = :city " +
            "AND (:movieId IS NULL OR s.movie.id = :movieId) " +
            "AND s.startTime BETWEEN :startOfDay AND :endOfDay")
    List<Show> findShowsWithDetails(
            @Param("city") String city,
            @Param("movieId") Long movieId,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay
    );

    boolean existsByHallIdAndStartTimeBetween(Long hallId, LocalDateTime localDateTime, LocalDateTime localDateTime1);
}
