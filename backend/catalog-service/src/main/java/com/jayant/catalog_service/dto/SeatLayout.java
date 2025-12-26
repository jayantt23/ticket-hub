package com.jayant.catalog_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class SeatLayout {
    private int totalSeats;
    private int columns;
    private List<SeatRow> rows;
}
