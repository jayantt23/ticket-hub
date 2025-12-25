package com.jayant.catalog_service.mapper;

import com.jayant.catalog_service.dto.ShowDetailDto;
import com.jayant.catalog_service.dto.ShowDto;
import com.jayant.catalog_service.dto.ShowResponseDto;
import com.jayant.catalog_service.entity.Show;

public interface ShowMapper {
    ShowDto toDto(Show show);
    ShowDetailDto toDetailsDto(Show show);
    ShowResponseDto toResponseDto(Show show);
}
