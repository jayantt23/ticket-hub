package com.jayant.catalog_service.mapper.impl;

import com.jayant.catalog_service.dto.ShowDetailDto;
import com.jayant.catalog_service.dto.ShowDto;
import com.jayant.catalog_service.dto.ShowResponseDto;
import com.jayant.catalog_service.entity.Show;
import com.jayant.catalog_service.mapper.ShowMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ShowMapperImpl implements ShowMapper {

    @Override
    public ShowDto toDto(Show show) {
        return ShowDto.builder()
                .id(show.getId())
                .startTime(show.getStartTime())
                .basePrice(show.getBasePrice())
                .build();
    }

    @Override
    public ShowDetailDto toDetailsDto(Show show) {
        return ShowDetailDto.builder()
                .showId(show.getId())
                .movieTitle(show.getMovie().getTitle())
                .theatreName(show.getHall().getTheatre().getName())
                .hallName("Hall " + show.getHall().getId())
                .seatLayout(show.getHall().getSeatLayout())
                .bookedSeats(new ArrayList<>())
                .build();
    }

    @Override
    public ShowResponseDto toResponseDto(Show show) {
        return ShowResponseDto.builder()
                .showId(show.getId())
                .movieTitle(show.getMovie().getTitle())
                .theatreName(show.getHall().getTheatre().getName())
                .startTime(show.getStartTime())
                .build();
    }
}
