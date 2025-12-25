package com.jayant.catalog_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowDetailDto {
    private Long showId;
    private String movieTitle;
    private String theatreName;
    private String hallName;
    private String seatLayout;
    private List<String> bookedSeats;
}
