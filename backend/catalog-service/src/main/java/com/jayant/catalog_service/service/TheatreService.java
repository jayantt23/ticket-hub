package com.jayant.catalog_service.service;

import com.jayant.catalog_service.dto.TheatreDto;

import java.util.List;

public interface TheatreService {
    TheatreDto saveTheatre(TheatreDto theatreDto);
    List<TheatreDto> getAllTheatres();
    List<TheatreDto> getTheatresByCity(String city);
    TheatreDto getTheatreById(Long theatreId);
}
