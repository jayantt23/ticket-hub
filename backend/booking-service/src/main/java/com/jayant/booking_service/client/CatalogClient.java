package com.jayant.booking_service.client;

import com.jayant.booking_service.dto.ShowDetailsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CATALOG-SERVICE")
public interface CatalogClient {

    @GetMapping("/shows/{showId}")
    ShowDetailsDto getShow(@PathVariable("showId") Long showId);

}
