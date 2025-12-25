package com.jayant.catalog_service.service;

import com.jayant.catalog_service.dto.ScheduleShowRequest;
import com.jayant.catalog_service.dto.ShowDetailDto;
import com.jayant.catalog_service.dto.ShowResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ShowService {
    ShowResponseDto saveShow(ScheduleShowRequest request);
    List<ShowResponseDto> getAllShows(String city, Long movieId, LocalDate date);
    ShowDetailDto getShow(Long id);
}
