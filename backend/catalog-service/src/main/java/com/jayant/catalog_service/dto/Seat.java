package com.jayant.catalog_service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Seat implements Serializable {
    private int number;
    private String type;
}
