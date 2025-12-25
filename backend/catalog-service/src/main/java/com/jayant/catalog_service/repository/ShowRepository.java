package com.jayant.catalog_service.repository;

import com.jayant.catalog_service.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Long> {
}
