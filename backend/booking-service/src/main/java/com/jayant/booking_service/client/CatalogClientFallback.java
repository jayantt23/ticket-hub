package com.jayant.booking_service.client;

import com.jayant.booking_service.dto.ShowDetailsDto;
import org.springframework.stereotype.Component;

@Component
public class CatalogClientFallback implements CatalogClient {

    @Override
    public ShowDetailsDto getShowDetails(Long showId) {
        // Return a graceful default object, or throw a specific "Service Unavailable"
        // exception that tells the user to try again later.
        ShowDetailsDto fallbackDto = new ShowDetailsDto();
        fallbackDto.setMovieTitle("Service Currently Unavailable");
        fallbackDto.setTheatreName("Please try again in a few moments");
        // Leave lists empty so the UI doesn't crash
        fallbackDto.setBookedSeats(new ArrayList<>());
        return fallbackDto;
    }

    @Override
    public ShowDetailsDto getShow(Long showId) {
        return null;
    }
}