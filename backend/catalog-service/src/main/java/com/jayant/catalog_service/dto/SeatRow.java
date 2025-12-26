package com.jayant.catalog_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class SeatRow {
    private String rowLabel;
    private boolean premium;
    private List<Seat> seats;
}
