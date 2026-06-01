package com.jayant.booking_service.client;

import com.jayant.booking_service.dto.ShowDetailsDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CatalogClientFallback implements CatalogClient {
    @Override
    public ShowDetailsDto getShow(Long showId) {
        ShowDetailsDto fallbackDto = new ShowDetailsDto();
        fallbackDto.setMovieTitle("Service Currently Unavailable");
        fallbackDto.setTheatreName("Please try again in a few moments");
        fallbackDto.setBookedSeats(new ArrayList<>());
        return fallbackDto;
    }
}