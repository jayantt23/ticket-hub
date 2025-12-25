package com.jayant.catalog_service.repository;

import com.jayant.catalog_service.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HallRepository extends JpaRepository<Hall, Long> {
    List<Hall> findByTheatreId(Long theatreId);
}
