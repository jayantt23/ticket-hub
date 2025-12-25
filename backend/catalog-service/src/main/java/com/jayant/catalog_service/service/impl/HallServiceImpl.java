package com.jayant.catalog_service.service.impl;

import com.jayant.catalog_service.dto.HallDto;
import com.jayant.catalog_service.entity.Hall;
import com.jayant.catalog_service.entity.Theatre;
import com.jayant.catalog_service.mapper.HallMapper;
import com.jayant.catalog_service.repository.HallRepository;
import com.jayant.catalog_service.repository.TheatreRepository;
import com.jayant.catalog_service.service.HallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;

    private final HallMapper mapper;

    private final TheatreRepository theatreRepository;

    @Override
    @CacheEvict(value = "halls", allEntries = true)
    public HallDto saveHall(Long theatreId, HallDto hallDto) {
        Theatre theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new RuntimeException("Theatre not found with id: " + theatreId));

        Hall hall = mapper.toEntity(hallDto);
        hall.setTheatre(theatre);

        Hall savedHall = hallRepository.save(hall);
        return mapper.toDto(savedHall);
    }

    @Override
    @Cacheable(value = "halls", key = "#theatreId", unless = "#result.isEmpty()")
    public List<HallDto> getHallsByTheatreId(Long theatreId) {
        log.info("Fetching halls from Database...");
        return hallRepository.findByTheatreId(theatreId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public HallDto updateSeats(Long hallId, String seats) {
        Hall hall = hallRepository.findById(hallId)
                .orElseThrow(() -> new RuntimeException("Hall not found with id: " + hallId));

        hall.setSeatLayout(seats);

        Hall updatedHall = hallRepository.save(hall);
        return mapper.toDto(updatedHall);
    }
}
