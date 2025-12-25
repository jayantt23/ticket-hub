package com.jayant.catalog_service.service.impl;

import com.jayant.catalog_service.dto.HallDto;
import com.jayant.catalog_service.dto.TheatreDto;
import com.jayant.catalog_service.entity.Theatre;
import com.jayant.catalog_service.mapper.TheatreMapper;
import com.jayant.catalog_service.repository.TheatreRepository;
import com.jayant.catalog_service.service.TheatreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TheatreServiceImpl implements TheatreService {

    private final TheatreRepository repository;

    private final TheatreMapper mapper;

    @Override
    public TheatreDto saveTheatre(TheatreDto theatreDto) {
        Theatre theatre = mapper.toEntity(theatreDto);
        Theatre savedTheatre = repository.save(theatre);
        return mapper.toDto(savedTheatre);
    }

    @Override
    public List<TheatreDto> getAllTheatres() {
        log.info("Fetching theatres from Database...");
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TheatreDto> getTheatresByCity(String city) {
        log.info("Fetching theatres of " + city + " from Database...");
        return repository.findByCityIgnoreCase(city)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TheatreDto getTheatreById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Theatre not found with id: " + id));
    }

}
