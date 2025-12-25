package com.jayant.catalog_service.service;

import com.jayant.catalog_service.dto.TheatreDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TheatreService {
    TheatreDto saveTheatre(TheatreDto theatreDto);
    List<TheatreDto> getTheatresByCity(String city);
    Page<TheatreDto> getAllTheatresRaw(Pageable pageable);
    TheatreDto getTheatreById(Long id);
}
