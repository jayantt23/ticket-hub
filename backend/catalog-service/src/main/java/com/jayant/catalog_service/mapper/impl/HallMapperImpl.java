package com.jayant.catalog_service.mapper.impl;

import com.jayant.catalog_service.dto.HallDto;
import com.jayant.catalog_service.entity.Hall;
import com.jayant.catalog_service.mapper.HallMapper;
import com.jayant.catalog_service.mapper.ShowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HallMapperImpl implements HallMapper {

    public final ShowMapper showMapper;

    @Override
    public HallDto toDto(Hall hall) {
        return HallDto.builder()
                .id(hall.getId())
                .seatLayout(hall.getSeatLayout())
                .shows(hall.getShows() != null ?
                        hall.getShows().stream()
                                .map(showMapper::toDto)
                                .collect(Collectors.toList())
                        : Collections.emptyList())
                .build();
    }
}
