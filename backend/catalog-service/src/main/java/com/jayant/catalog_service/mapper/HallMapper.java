package com.jayant.catalog_service.mapper;

import com.jayant.catalog_service.dto.HallDto;
import com.jayant.catalog_service.entity.Hall;

public interface HallMapper {
    Hall toEntity(HallDto hallDto);
    HallDto toDto(Hall hall);
}
