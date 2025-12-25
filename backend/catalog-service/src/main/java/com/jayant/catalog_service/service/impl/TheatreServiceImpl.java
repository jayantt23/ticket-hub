package com.jayant.catalog_service.service.impl;

import com.jayant.catalog_service.dto.HallDto;
import com.jayant.catalog_service.dto.TheatreDto;
import com.jayant.catalog_service.entity.Theatre;
import com.jayant.catalog_service.repository.TheatreRepository;
import com.jayant.catalog_service.service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheatreServiceImpl implements TheatreService {

    private final TheatreRepository theatreRepository;

    @Override
    public TheatreDto saveTheatre(TheatreDto theatreDto) {
        return null;
    }

    @Override
    public List<TheatreDto> getAllTheatres() {
        return List.of();
    }

    @Override
    public List<TheatreDto> getTheatresByCity(String city) {
        return List.of();
    }

    @Override
    public TheatreDto getTheatreById(Long theatreId) {
        return null;
    }

}
