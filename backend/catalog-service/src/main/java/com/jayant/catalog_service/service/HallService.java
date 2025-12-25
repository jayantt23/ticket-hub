package com.jayant.catalog_service.service;

import com.jayant.catalog_service.dto.HallDto;

import java.util.List;

public interface HallService {
    HallDto saveHall(Long theatreId, HallDto hallDto);
    List<HallDto> getHallsByTheatreId(Long theatreId);
    HallDto updateSeats(Long hallId, String seats);
}
