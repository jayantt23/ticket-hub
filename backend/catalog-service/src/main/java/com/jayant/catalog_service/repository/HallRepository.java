package com.jayant.catalog_service.repository;

import com.jayant.catalog_service.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<Hall, Long> {
}
