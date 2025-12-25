package com.jayant.booking_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MOVIE-CATALOG")
public interface CatalogClient {

    @GetMapping("/shows/{id}/exists")
    boolean doesShowExist(@PathVariable("id") Long showId);
}
