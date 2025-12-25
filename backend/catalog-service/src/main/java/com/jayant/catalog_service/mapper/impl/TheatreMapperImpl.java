package com.jayant.catalog_service.mapper.impl;

import com.jayant.catalog_service.dto.TheatreDto;
import com.jayant.catalog_service.entity.Theatre;
import com.jayant.catalog_service.mapper.HallMapper;
import com.jayant.catalog_service.mapper.TheatreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor

public class TheatreMapperImpl implements TheatreMapper {

    private final HallMapper hallMapper;

    @Override
    public Theatre toEntity(TheatreDto theatreDto) {
        Theatre theatre = new Theatre();
        theatre.setName(theatreDto.getName());
        theatre.setCity(theatreDto.getCity());
        theatre.setAddress(theatreDto.getAddress());
        theatre.setHalls(new ArrayList<>());

        return theatre;
    }

    @Override
    public TheatreDto toDto(Theatre theatre) {
        return TheatreDto.builder()
                .id(theatre.getId())
                .name(theatre.getName())
                .city(theatre.getCity())
                .address(theatre.getAddress())
                .halls(theatre.getHalls() != null ?
                        theatre.getHalls().stream()
                                .map(hallMapper::toDto)
                                .collect(Collectors.toList())
                        : Collections.emptyList())
                .build();
    }
}
